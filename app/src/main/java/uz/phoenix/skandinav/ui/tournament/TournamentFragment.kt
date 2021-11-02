package uz.phoenix.skandinav.ui.tournament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTournamentBinding
import uz.phoenix.skandinav.database.AppDatabase

class TournamentFragment : Fragment() {

    private var _binding: FragmentTournamentBinding? = null
    private val binding get() = _binding!!
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTournamentBinding.inflate(inflater, container, false)

        val data = AppDatabase.GET.getTrainingDatabase().getTrainingDao().getTournament()
        val bundle = Bundle()

        popBackStack()
        setNavigation()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tournament1.setOnClickListener {
            bundle.putSerializable("tournament", data[0])
            findNavController().navigate(R.id.preparationPartFragment, bundle, navOptions.build())
        }

        binding.tournament2.setOnClickListener {
            bundle.putSerializable("tournament", data[1])
            findNavController().navigate(R.id.preparationPartFragment, bundle, navOptions.build())
        }

        binding.tournament3.setOnClickListener {
            bundle.putSerializable("tournament", data[2])
            findNavController().navigate(R.id.preparationPartFragment, bundle, navOptions.build())
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun popBackStack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }
}