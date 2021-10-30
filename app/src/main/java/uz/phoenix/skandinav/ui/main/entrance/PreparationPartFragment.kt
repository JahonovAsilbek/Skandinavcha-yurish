package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentPreparationPartBinding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"
private const val ARG_PARAM2 = "main_part"
private const val ARG_PARAM3 = "nord_walking"
private const val ARG_PARAM4 = "end_part"

class PreparationPartFragment : Fragment() {

    private var training: Training? = null
    private var endPart: Training? = null
    private var mainPart: MainPart? = null
    private var nordWalking: MainPart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
            mainPart = it.getSerializable(ARG_PARAM2) as MainPart?
            nordWalking = it.getSerializable(ARG_PARAM3) as MainPart?
            endPart = it.getSerializable(ARG_PARAM4) as Training?
        }
    }

    lateinit var binding: FragmentPreparationPartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreparationPartBinding.inflate(layoutInflater)

        if (training != null) {
            binding.title.text = "Nazariy\nMa'lumotlar"
            binding.preparationPart.text = training!!.preparationPart
            binding.image.visibility = View.GONE
        }

        if (mainPart != null) {
            binding.title.text = "Harakatli\no'yinlar"
            binding.preparationPart.text = mainPart!!.actionGame
            binding.image.visibility = View.VISIBLE
            Picasso.get().load(mainPart!!.actionGameImage).error(R.drawable.walking)
                .into(binding.image)
        }

        if (nordWalking != null) {
            binding.title.text = "Skandinavcha\nyurish"
            binding.preparationPart.text = mainPart!!.nordWalking
            binding.image.visibility = View.GONE
        }

        if (endPart != null) {
            binding.title.text = "Yakuniy\nqism"
            binding.preparationPart.text = endPart!!.endPart
            binding.image.visibility = View.GONE
            AppDatabase.GET.getTrainingDatabase().getTrainingDao().finishTraining(endPart?.id!! + 1)
            popBackStack()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack(R.id.trainingListFragment, false)
        }

        return binding.root
    }

    private fun popBackStack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack(R.id.trainingListFragment, false)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}