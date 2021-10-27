package uz.phoenix.skandinav.ui.main.month

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentMonthBinding
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.ui.main.month.adapters.MonthAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MonthFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentMonthBinding
    lateinit var monthList: ArrayList<Month>
    lateinit var adapter: MonthAdapter
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonthBinding.inflate(layoutInflater, container, false)

        setNavigation()
        popBackStack()
        loadData()
        loadAdapter()
        monthClick()
        backClick()

        return binding.root
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun monthClick() {
        adapter.onMonthClick = object : MonthAdapter.OnMonthClick {
            override fun onClick(month: Month) {
                Toast.makeText(binding.root.context, "${month.name}", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putSerializable("month", month)
                findNavController().navigate(R.id.trainingListFragment, bundle, navOptions.build())
            }
        }
    }

    private fun loadAdapter() {
        adapter = MonthAdapter()
        adapter.setAdapter(monthList)
        binding.rv.adapter = adapter
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    private fun loadData() {
        monthList = ArrayList()
        monthList.add(Month(0, "Sentyabr"))
        monthList.add(Month(1, "Oktyabr"))
        monthList.add(Month(2, "Noyabr"))
        monthList.add(Month(3, "Dekabr"))
        monthList.add(Month(4, "Yanvar"))
        monthList.add(Month(5, "Fevral"))
        monthList.add(Month(6, "Mart"))
        monthList.add(Month(7, "Aprel"))
        monthList.add(Month(8, "May"))
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MonthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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
}