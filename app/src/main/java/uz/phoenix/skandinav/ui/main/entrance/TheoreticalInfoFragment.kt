package uz.phoenix.skandinav.ui.main.entrance

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTheoreticalInfoBinding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"

class TheoreticalInfoFragment : Fragment() {

    private var training: Training? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
        }
    }

    lateinit var binding: FragmentTheoreticalInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTheoreticalInfoBinding.inflate(layoutInflater)

        binding.tv.text = training?.theoriticalInfo

        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppDatabase.GET.getTrainingDatabase().getTrainingDao().updateTraining(
                    Training(
                        training!!.id,
                        training!!.monthId,
                        training!!.name,
                        training!!.theoriticalInfo,
                        training!!.video,
                        training!!.preparationPart,
                        training!!.task1Text,
                        training!!.task1Video,
                        training!!.task2Text,
                        training!!.task2Video,
                        training!!.task3Text,
                        training!!.task3Video,
                        true,
                        training!!.endPart,
                        training!!.isFinished
                    )
                )
                binding.continueBtn.setBackgroundColor(resources.getColor(R.color.main_blue))
            } else {
                binding.continueBtn.setBackgroundColor(Color.parseColor("#C4C4C4"))
                AppDatabase.GET.getTrainingDatabase().getTrainingDao().updateTraining(
                    Training(
                        training!!.id,
                        training!!.monthId,
                        training!!.name,
                        training!!.theoriticalInfo,
                        training!!.video,
                        training!!.preparationPart,
                        training!!.task1Text,
                        training!!.task1Video,
                        training!!.task2Text,
                        training!!.task2Video,
                        training!!.task3Text,
                        training!!.task3Video,
                        false,
                        training!!.endPart,
                        training!!.isFinished
                    )
                )
            }
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.continueBtn.setOnClickListener {
            if (binding.checkbox.isChecked) {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}