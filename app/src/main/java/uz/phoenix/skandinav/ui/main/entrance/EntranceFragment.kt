package uz.phoenix.skandinav.ui.main.entrance

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentEntranceBinding
import phoenix.skandinav.databinding.InfoDialog2Binding
import phoenix.skandinav.databinding.InfoDialogBinding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"

class EntranceFragment : Fragment() {

    private var training: Training? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
        }
    }

    lateinit var binding: FragmentEntranceBinding
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntranceBinding.inflate(layoutInflater)

        setNavigation()
        loadDataToView()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.theoreticalInfo.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.theoreticalInfoFragment, bundle, navOptions.build())
        }

        binding.video.setOnClickListener {

            if (training!!.isVideoOpened == true) {
                binding.progress.visibility = View.VISIBLE
                val bundle = Bundle()
                bundle.putSerializable("training", training)
                findNavController().navigate(R.id.videoFragment, bundle, navOptions.build())
            } else {
                val dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                val alertDialog = dialog.create()
                val view = InfoDialog2Binding.inflate(layoutInflater, null, false)
                view.root.setBackgroundColor(resources.getColor(R.color.white))
                view.ok.setOnClickListener {
                    alertDialog.cancel()
                }
                view.title.text = "Musobaqa nizomi"
                view.text.text = "Avval Nazariy ma'lumotlarni o'qib chiqing"
                view.text.gravity = Gravity.CENTER
                view.text.textSize = 22f
                alertDialog.setView(view.root)
                alertDialog.show()
            }

        }

        binding.tasks.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.urmTasksFragment, bundle, navOptions.build())
        }

        return binding.root
    }

    private fun loadDataToView() {
        if (training != null) {
            if (training!!.isVideoOpened == false) {
                binding.video.setBackgroundResource(R.drawable.training_locked_back)
                binding.lock.visibility = View.VISIBLE
            } else {
                binding.video.setBackgroundResource(R.drawable.first_enter_btn_back)
                binding.lock.visibility = View.INVISIBLE
            }
        }
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    override fun onResume() {
        super.onResume()
        training =
            AppDatabase.GET.getTrainingDatabase().getTrainingDao().getTrainingById(training?.id!!)
        loadDataToView()
        binding.progress.visibility = View.INVISIBLE
    }

}