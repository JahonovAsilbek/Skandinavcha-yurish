package uz.phoenix.skandinav.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentSignUpBinding
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.User

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(layoutInflater)

        binding.continueBtn.setOnClickListener {
            // insertUserData user data to database
            val name = binding.name.text.toString().trim()
            val surname = binding.surname.text.toString().trim()

            if (name.isNotEmpty() && surname.isNotEmpty()) {
                val user = User(name, surname, null, null, null, null)
                UserDatabase.Get.getUserDatabase().getDao().insertUser(user)
                findNavController().navigate(R.id.inputDataFragment)
            } else {
                Toast.makeText(binding.root.context, "Ma'lumotlarni kiriting", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }
}