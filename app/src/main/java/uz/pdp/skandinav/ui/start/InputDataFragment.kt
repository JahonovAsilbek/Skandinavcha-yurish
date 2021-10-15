package uz.pdp.skandinav.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.pdp.skandinav.databinding.FragmentInputDataBinding

class InputDataFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var binding: FragmentInputDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputDataBinding.inflate(layoutInflater)

        setSeekbar()

        return binding.root
    }

    private fun setSeekbar() {
        val max = 170
        val min = 70
        val total = max - min

        val slider = binding.seekbar
        slider.positionListener = { pos ->
            val height = min + (total * pos).toInt()
            slider.bubbleText = height.toString()
            binding.height.text = "Bo'yingiz\n$height"
            binding.stickHeight.text = "Tayoq\nuzunligi\n${(height * 0.68).toInt()}"
        }
        slider.position = 0.5f
        slider.startText = "$min"
        slider.endText = "$max"

    }
}