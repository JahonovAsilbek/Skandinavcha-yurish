package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.databinding.FragmentPreparationPartBinding
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "trining"
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
            endPart = it.getSerializable(ARG_PARAM4) as Training?
            mainPart = it.getSerializable(ARG_PARAM2) as MainPart?
            nordWalking = it.getSerializable(ARG_PARAM3) as MainPart?
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
        }

        if (mainPart != null) {
            binding.title.text = "Harakatli\no'yinlar"
            binding.preparationPart.text = mainPart!!.actionGame
        }

        if (nordWalking != null) {
            binding.title.text = "Skandinavcha\nyurish"
            binding.preparationPart.text = mainPart!!.nordWalking
        }

        if (endPart != null) {
            binding.title.text = "Yakuniy\nqism"
            binding.preparationPart.text = endPart!!.endPart
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}