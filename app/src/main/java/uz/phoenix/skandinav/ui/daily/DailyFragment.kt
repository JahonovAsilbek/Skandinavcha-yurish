package uz.phoenix.skandinav.ui.daily

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.*
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.daos.TrainingDao
import uz.phoenix.skandinav.ui.daily.adapters.DailyAdapter
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DailyFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var trainingDao: TrainingDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        trainingDao = AppDatabase.GET.getTrainingDatabase().getTrainingDao()
    }

    lateinit var binding: FragmentDailyBinding
    lateinit var dialog: AlertDialog.Builder
    lateinit var view: DailyDailogBinding
    lateinit var adapter: DailyAdapter
    lateinit var alertDialog: AlertDialog
    private var monthId = 0

    private var month = ""
    private var training = ""
    private var health = ""
    private var mood = ""
    private var appetite = ""
    private var desire = ""
    private var sleep = ""
    private var trainingStartTime = ""
    private var trainingEndTime = ""
    private var nordWalkingLength = ""
    private var nordWalkingTime = ""
    private var trainingEntrancePeriod = ""
    private var trainingMainPartPeriod = ""
    private var trainingEndPartPeriod = ""
    private var fatigue = ""
    private var disorder = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyBinding.inflate(layoutInflater)

        loadDialog()
        backClick()
        monthClick()
        trainingClick()
        healthClick()
        appetiteClick()
        moodClick()
        desireClick()
        sleepClick()
        trainingStartTime()
        trainingEndTime()
        nordWalkingLength()
        nordWalkingTime()
        fatigueClick()
        disorderClick()
        trainingPeriodClick()
        inputClick()

        popBackStack()

        return binding.root
    }

    private fun inputClick() {
        binding.click.setOnClickListener {
            if (
                month.isNotEmpty() &&
                training.isNotEmpty() &&
                health.isNotEmpty() &&
                mood.isNotEmpty() &&
                appetite.isNotEmpty() &&
                desire.isNotEmpty() &&
                sleep.isNotEmpty() &&
                trainingStartTime.isNotEmpty() &&
                trainingEndTime.isNotEmpty() &&
                nordWalkingLength.isNotEmpty() &&
                nordWalkingTime.isNotEmpty() &&
                trainingEntrancePeriod.isNotEmpty() &&
                trainingMainPartPeriod.isNotEmpty() &&
                trainingEndPartPeriod.isNotEmpty() &&
                fatigue.isNotEmpty() &&
                disorder.isNotEmpty()
            ) {
                UserDatabase.Get.getUserDatabase().getDao().deleteFinishedTraining(
                    monthId, training
                )
                findNavController().popBackStack()
            } else Toast.makeText(
                binding.root.context,
                "Barcha maydonlarni to'ldiring",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun trainingPeriodClick() {
        binding.trainingPeriod.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
            val view = NordWalkingPeriodDialogBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val alertDialog = dialog.create()
            view.title.text = "Mashg'ulot davomiyligi (min)"
            view.ok.setOnClickListener {
                val entrance = view.entrance.text.toString()
                val mainPart = view.mainPart.text.toString()
                val endPart = view.endPart.text.toString()
                if (entrance.isNotEmpty() && mainPart.isNotEmpty() && endPart.isNotEmpty()) {
                    binding.trainingPeriod.text = "$entrance     $mainPart     $endPart"
                    trainingEntrancePeriod = entrance
                    trainingMainPartPeriod = mainPart
                    trainingEndPartPeriod = endPart
                    alertDialog.cancel()
                }
                binding.trainingPeriod.setBackgroundResource(R.drawable.tv_back)
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun disorderClick() {
        binding.disorder.setOnClickListener {
            val array = arrayListOf("Yo'q", "Ha")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.disorder.text = string
                    alertDialog.cancel()
                    binding.disorder.setBackgroundResource(R.drawable.tv_back)
                    disorder = string
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun fatigueClick() {
        binding.fatigue.setOnClickListener {
            val array = arrayListOf("Yuqori", "O'rta", "Past")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.fatigue.text = string
                    alertDialog.cancel()
                    binding.fatigue.setBackgroundResource(R.drawable.tv_back)
                    fatigue = string
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun nordWalkingTime() {
        binding.nordWalkingTime.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
            val view = NordWalkingDialogBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val alertDialog = dialog.create()
            view.title.text = "Skandinavcha yurish vaqtini kiriting (min)"
            view.edittext.hint = "Vaqtni kiriting"
            view.ok.setOnClickListener {
                val time = view.edittext.text.toString()
                if (time.isNotEmpty()) {
                    binding.nordWalkingTime.text = time
                    nordWalkingTime = time
                    binding.nordWalkingTime.setBackgroundResource(R.drawable.tv_back)
                    alertDialog.cancel()
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun nordWalkingLength() {
        binding.nordWalkingLength.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
            val view = NordWalkingDialogBinding.inflate(
                LayoutInflater.from(binding.root.context),
                null,
                false
            )
            val alertDialog = dialog.create()
            view.title.text = "Skandinavcha yurish masofasini kiriting (metr)"
            view.ok.setOnClickListener {
                val length = view.edittext.text.toString()
                if (length.isNotEmpty()) {
                    binding.nordWalkingLength.text = length
                    nordWalkingLength = length
                    binding.nordWalkingLength.setBackgroundResource(R.drawable.tv_back)
                    alertDialog.cancel()
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun trainingEndTime() {
        binding.trainingEndTime.setOnClickListener {
            val dialog = TimePickerDialog(
                binding.root.context,
                { view, hourOfDay, minute ->
                    val date =
                        SimpleDateFormat("HH:mm").format(Date(2021, 11, 2, hourOfDay, minute))
                    binding.trainingEndTime.text = date
                    binding.trainingEndTime.setBackgroundResource(R.drawable.tv_back)
                    trainingEndTime = date
                }, 12, 0, true
            )
            dialog.show()
        }
    }

    private fun trainingStartTime() {
        binding.trainingStartTime.setOnClickListener {
            val dialog = TimePickerDialog(
                binding.root.context,
                { view, hourOfDay, minute ->
                    val date =
                        SimpleDateFormat("HH:mm").format(Date(2021, 11, 2, hourOfDay, minute))
                    binding.trainingStartTime.text = date
                    trainingStartTime = date
                    binding.trainingStartTime.setBackgroundResource(R.drawable.tv_back)
                }, 12, 0, true
            )
            dialog.show()
        }
    }

    private fun sleepClick() {
        binding.sleep.setOnClickListener {
            val array = arrayListOf("5", "6", "7", "8", "9", "10", "11", "12", "13", "14")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.sleep.text = string
                    alertDialog.cancel()
                    sleep = string
                    binding.sleep.setBackgroundResource(R.drawable.tv_back)
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()

        }
    }

    private fun desireClick() {
        binding.desire.setOnClickListener {
            val array = arrayListOf("Yuqori", "O'rta", "Past")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.desire.text = string
                    alertDialog.cancel()
                    desire = string
                    binding.desire.setBackgroundResource(R.drawable.tv_back)
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun moodClick() {
        binding.mood.setOnClickListener {
            val array = arrayListOf("Yaxshi", "Qoniqarli", "Yomon")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.mood.text = string
                    alertDialog.cancel()
                    mood = string
                    binding.mood.setBackgroundResource(R.drawable.tv_back)
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()

        }
    }

    private fun appetiteClick() {
        binding.appetite.setOnClickListener {
            val array = arrayListOf("Yaxshi", "Qoniqarli", "Yomon")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.appetite.text = string
                    alertDialog.cancel()
                    appetite = string
                    binding.appetite.setBackgroundResource(R.drawable.tv_back)
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()

        }
    }

    private fun healthClick() {
        binding.health.setOnClickListener {
            val array = arrayListOf("Yaxshi", "Qoniqarli", "Yomon")
            adapter.setAdapter(array)
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.health.text = string
                    alertDialog.cancel()
                    health = string
                    binding.health.setBackgroundResource(R.drawable.tv_back)
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun trainingClick() {
        binding.training.setOnClickListener {
            if (monthId != 0) {
                val trainingData =
                    UserDatabase.Get.getUserDatabase().getDao()
                        .getFinishedTrainingByMonthId(monthId)
                if (trainingData.isNotEmpty()) {
                    adapter.setAdapter(trainingData)
                    view.rv.adapter = adapter
                    adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                        override fun onClick(string: String, position: Int) {
                            binding.training.text = string
                            alertDialog.cancel()
                            training = string
                            binding.training.setBackgroundResource(R.drawable.tv_back)
                        }
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
                    }
                    view.title.text = "Diqqat!"
                    view.text.text = "Ushbu oy uchun tugallangan mashg'ulot mavjud emas"
                    view.text.gravity = Gravity.CENTER
                    view.text.textSize = 22f
                    alertDialog.setView(view.root)
                    alertDialog.show()
                }

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
                }
                view.title.text = "Diqqat!"
                view.text.text = "Avval oyni tanlang!"
                view.text.gravity = Gravity.CENTER
                view.text.textSize = 22f
                alertDialog.setView(view.root)
                alertDialog.show()
            }
        }
    }

    private fun monthClick() {
        binding.month.setOnClickListener {
            adapter.setAdapter(trainingDao.getAllMonthName())
            view.rv.adapter = adapter
            adapter.onDailyItemClick = object : DailyAdapter.OnDailyItemClick {
                override fun onClick(string: String, position: Int) {
                    binding.month.text = string
                    alertDialog.cancel()
                    monthId = position + 1
                    month = string
                    training = ""
                    binding.month.setBackgroundResource(R.drawable.tv_back)
                    binding.training.setBackgroundResource(0)
                    binding.training.text = "Mashg'ulot"
                }
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun loadDialog() {
        dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
        view = DailyDailogBinding.inflate(LayoutInflater.from(binding.root.context), null, false)
        adapter = DailyAdapter()
        alertDialog = dialog.create()

    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyFragment().apply {
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