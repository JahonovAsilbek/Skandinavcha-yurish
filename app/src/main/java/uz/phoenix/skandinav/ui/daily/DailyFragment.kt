package uz.phoenix.skandinav.ui.daily

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.DailyDailogBinding
import phoenix.skandinav.databinding.FragmentDailyBinding
import phoenix.skandinav.databinding.InfoDialog2Binding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.daos.TrainingDao
import uz.phoenix.skandinav.ui.daily.adapters.DailyAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DailyFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var trainingDao: TrainingDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        trainingDao = AppDatabase.GET.getTrainingDatabase().getTrainingDao()
    }

    lateinit var binding: FragmentDailyBinding
    lateinit var dialog: AlertDialog.Builder
    lateinit var view: DailyDailogBinding
    lateinit var adapter: DailyAdapter
    lateinit var alertDialog: AlertDialog
    private var monthId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(layoutInflater)

        loadDialog()
        backClick()
        monthClick()
        trainingClick()

        popBackStack()

        return binding.root
    }

    private fun trainingClick() {
        binding.training.setOnClickListener {
            if (monthId != 0) {
                val trainingData =
                    UserDatabase.Get.getUserDatabase().getDao().getFinishedTrainingByMonthId(monthId)
                adapter.setAdapter(trainingData)
                view.rv.adapter = adapter
                adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                    override fun onClick(string: String, position: Int) {
                        binding.training.text = string
                        alertDialog.cancel()
                    }
                }
                alertDialog.setView(view.root)
                alertDialog.show()
            } else {
                val dialog = androidx.appcompat.app.AlertDialog.Builder(
                    binding.root.context,
                    R.style.RoundedCornersDialog
                )
                val alertDialog = dialog.create()
                val view = InfoDialog2Binding.inflate(layoutInflater, null, false)
                view.root.setBackgroundColor(resources.getColor(R.color.white))
                view.ok.setOnClickListener {
                    alertDialog.cancel()
                }
                view.title.text = "Diqqat!"
                view.text.text = "Avval oyni tanlang!"
                view.text.gravity = Gravity.CENTER
                view.text.textSize = 22f
                alertDialog.setView(view.root)
                alertDialog.show()
            }
        }
    }

    private fun monthClick() {
        binding.month.setOnClickListener {
            adapter.setAdapter(trainingDao.getAllMonthName())
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.month.text = string
                    alertDialog.cancel()
                    monthId = position + 1
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun loadDialog() {
        dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
        view = DailyDailogBinding.inflate(LayoutInflater.from(binding.root.context), null, false)
        adapter = DailyAdapter()
        alertDialog = dialog.create()

    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun popBackStack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}