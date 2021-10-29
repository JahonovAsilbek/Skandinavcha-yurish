package uz.phoenix.skandinav.ui.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentStartBinding
import uz.phoenix.skandinav.MainActivity
import uz.phoenix.skandinav.database.UserDatabase

class StartFragment : Fragment() {
    lateinit var navOptions: NavOptions.Builder
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(layoutInflater)

        setNavigation()

        if (UserDatabase.Get.getUserDatabase().getDao().getUser() == null) {
            binding.layout2.visibility = View.VISIBLE
            binding.enter.setOnClickListener {
                findNavController().navigate(R.id.signUpFragment, Bundle(), navOptions.build())
            }
        } else {
            val handler = Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }, 1500)
        }
        return binding.root
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }
}