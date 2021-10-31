package uz.phoenix.skandinav.ui.tournament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTournamentBinding
import uz.phoenix.skandinav.database.AppDatabase

class TournamentFragment : Fragment() {

    private var _binding: FragmentTournamentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTournamentBinding.inflate(inflater, container, false)

        val data = AppDatabase.GET.getTrainingDatabase().getTrainingDao().getTournament()
        val bundle = Bundle()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tournament1.setOnClickListener {
            bundle.putSerializable("tournament", data[0])
            findNavController().navigate(R.id.preparationPartFragment, bundle)
        }

        binding.tournament2.setOnClickListener {
            bundle.putSerializable("tournament", data[1])
            findNavController().navigate(R.id.preparationPartFragment, bundle)
        }

        binding.tournament3.setOnClickListener {
            bundle.putSerializable("tournament", data[2])
            findNavController().navigate(R.id.preparationPartFragment, bundle)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}