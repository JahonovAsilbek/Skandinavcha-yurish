package uz.phoenix.skandinav.ui.main.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentMainPartBinding
import uz.phoenix.skandinav.database.entities.MainPart

private const val ARG_PARAM1 = "main_part"

class MainPartFragment : Fragment() {

    private var mainPart: MainPart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainPart = it.getSerializable(ARG_PARAM1) as MainPart?
        }
    }

    lateinit var binding: FragmentMainPartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPartBinding.inflate(layoutInflater)

        val bundle = Bundle()
        bundle.putSerializable("main_part", mainPart)

        binding.tasks.setOnClickListener {
            findNavController().navigate(R.id.tasksListFragment, bundle)
        }

        binding.games.setOnClickListener {
            findNavController().navigate(R.id.preparationPartFragment, bundle)
        }

        binding.walking.setOnClickListener {
            bundle.putSerializable("nord_walking", mainPart)
            findNavController().navigate(R.id.preparationPartFragment, bundle)
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}