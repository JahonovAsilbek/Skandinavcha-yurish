package uz.phoenix.skandinav.ui.main.month

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentMonthBinding
import phoenix.skandinav.databinding.InfoDialog2Binding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.ui.main.month.adapters.MonthAdapter

private const val ARG_PARAM1 = "history"
private const val ARG_PARAM2 = "param2"

class MonthFragment : Fragment() {

    private var history: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            history = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentMonthBinding
    lateinit var monthList: ArrayList<Month>
    lateinit var adapter: MonthAdapter
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonthBinding.inflate(layoutInflater, container, false)

        setNavigation()
        popBackStack()
        loadAdapter()
        monthClick()
        backClick()

        return binding.root
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun monthClick() {
        adapter.onMonthClick = object : MonthAdapter.OnMonthClick {
            override fun onClick(month: Month) {
                val bundle = Bundle()
                bundle.putSerializable("month", month)
                if (history == null) {
                    findNavController().navigate(
                        R.id.trainingListFragment,
                        bundle,
                        navOptions.build()
                    )
                } else {
                    val finishedTrainings =
                        month.id?.let {
                            UserDatabase.Get.getUserDatabase().getDao()
                                .getFinishedTrainingByMonthId(
                                    it
                                )
                        }

                    if (finishedTrainings != null) {
                        if (finishedTrainings.isEmpty()) {
                            val dialog =
                                AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                            val alertDialog = dialog.create()
                            val view = InfoDialog2Binding.inflate(layoutInflater, null, false)
                            view.root.setBackgroundColor(resources.getColor(R.color.white))
                            view.ok.setOnClickListener {
                                alertDialog.cancel()
                            }
                            view.title.text = "Diqqat!"
                            view.text.text = "Ushbu oy uchun yakunlangan mashg'ulot mavjud emas"
                            view.text.gravity = Gravity.CENTER_HORIZONTAL
                            view.text.textSize = 22f
                            alertDialog.setView(view.root)
                            alertDialog.show()
                        } else {
                            findNavController().navigate(
                                R.id.historyFragment,
                                bundle,
                                navOptions.build()
                            )
                        }
                    }

                }
            }
        }
    }

    private fun loadAdapter() {
        adapter = MonthAdapter()
        adapter.setAdapter(AppDatabase.GET.getTrainingDatabase().getTrainingDao().getAllMonth())
        binding.rv.adapter = adapter
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    companion object {

        @JvmStatic
        fun newInstance(history: String, param2: String) =
            MonthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, history)
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