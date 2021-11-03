package uz.phoenix.skandinav.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.ExitDialogBinding
import phoenix.skandinav.databinding.FragmentHomeBinding
import phoenix.skandinav.databinding.InfoDialog2Binding
import uz.phoenix.skandinav.MainActivity
import uz.phoenix.skandinav.database.UserDatabase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setNavigation()
        openDrawer()
        popBackStack()
        userDataClick()
        tournamentClick()
        dailyClick()
        trainingClick()

        return binding.root
    }

    private fun tournamentClick() {
        binding.tournament.setOnClickListener {
            findNavController().navigate(R.id.tournamentFragment, Bundle(), navOptions.build())
        }
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    private fun dailyClick() {
        binding.daily.setOnClickListener {
            findNavController().navigate(R.id.dailyFragment, Bundle(), navOptions.build())
        }
    }

    private fun trainingClick() {
        binding.training.setOnClickListener {
            findNavController().navigate(R.id.monthFragment, Bundle(), navOptions.build())
        }
    }

    private fun userDataClick() {
        binding.myData.setOnClickListener {
            findNavController().navigate(R.id.showUserDataFragment, Bundle(), navOptions.build())
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
                    val finishedTraining =
                        UserDatabase.Get.getUserDatabase().getDao().getAllFinishedTraining()
                    if (finishedTraining.isEmpty()) {
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
                    } else {
                        val dialog = androidx.appcompat.app.AlertDialog.Builder(
                            binding.root.context,
                            R.style.RoundedCornersDialog
                        )
                        val alertDialog = dialog.create()
                        val view = InfoDialog2Binding.inflate(layoutInflater, null, false)
                        view.root.setBackgroundColor(resources.getColor(R.color.white))
                        view.ok.setOnClickListener {
                            alertDialog.cancel()
                            findNavController().navigate(
                                R.id.dailyFragment,
                                Bundle(), navOptions.build
                                    ()
                            )
                        }
                        view.title.text = "Musobaqa nizomi"
                        view.text.text =
                            "Avval bugun bajarilgan mashg'ulotlar uchun kundalikni to'ldiring"
                        view.text.gravity = Gravity.CENTER_HORIZONTAL
                        view.text.textSize = 22f
                        alertDialog.setView(view.root)
                        alertDialog.show()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}