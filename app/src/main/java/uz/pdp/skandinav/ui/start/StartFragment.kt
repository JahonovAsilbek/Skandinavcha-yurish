package uz.pdp.skandinav.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.pdp.skandinav.R
import uz.pdp.skandinav.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(layoutInflater)
        binding.enter.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
        return binding.root
    }
}