package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import phoenix.skandinav.databinding.FragmentTheoreticalInfoBinding
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

        binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(binding.root.context, "Updated", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}