package uz.phoenix.skandinav.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.ExitDialogBinding
import phoenix.skandinav.databinding.FragmentHomeBinding
import uz.phoenix.skandinav.MainActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        openDrawer()
        popBackStack()
        userDataClick()
        dailyClick()

        return binding.root
    }

    private fun dailyClick() {
        binding.daily.setOnClickListener {
            findNavController().navigate(R.id.monthFragment)
        }
    }

    private fun userDataClick() {
        binding.myData.setOnClickListener {
            findNavController().navigate(R.id.showUserDataFragment)
        }
    }

    private fun openDrawer() {
        binding.drawerMenu.setOnClickListener {
            (activity as MainActivity).openDrawer()
            (activity as MainActivity).setDataToDrawer()
        }
    }

    private fun popBackStack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    val dialog =
                        AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                    val alertDialog = dialog.create()
                    val view = ExitDialogBinding.inflate(layoutInflater)
                    view.cancel.setOnClickListener {
                        alertDialog.cancel()
                    }
                    view.exit.setOnClickListener {
                        requireActivity().finish()
                    }
                    alertDialog.setView(view.root)
                    alertDialog.show()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}