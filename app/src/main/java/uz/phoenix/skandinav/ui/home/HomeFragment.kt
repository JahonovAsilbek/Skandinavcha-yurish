package uz.phoenix.skandinav.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import phoenix.skandinav.R
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

        return binding.root
    }

    private fun openDrawer() {
        binding.drawerMenu.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}