package uz.phoenix.skandinav.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentSignUpBinding
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.User

class SignUpFragment : Fragment() {

    lateinit var navOptions: NavOptions.Builder
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(layoutInflater)

        setNavigation()

        binding.continueBtn.setOnClickListener {
            // insertUserData user data to database
            val name = binding.name.text.toString().trim()
            val surname = binding.surname.text.toString().trim()

            if (name.isNotEmpty() && surname.isNotEmpty()) {
                val user = User(getRandomString(16), name, surname, null, null, null, null, 0)
                UserDatabase.Get.getUserDatabase().getDao().insertUser(user)
                findNavController().navigate(R.id.inputDataFragment, Bundle(), navOptions.build())
            } else {
                Toast.makeText(binding.root.context, "Ma'lumotlarni kiriting", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }
}