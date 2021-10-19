package uz.phoenix.skandinav.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentStartBinding
import uz.phoenix.skandinav.MainActivity
import uz.phoenix.skandinav.database.UserDatabase

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(layoutInflater)
        binding.enter.setOnClickListener {
            if (UserDatabase.Get.getUserDatabase().getDao().getUser() == null) {
                findNavController().navigate(R.id.signUpFragment)
            } else {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
        return binding.root
    }
}