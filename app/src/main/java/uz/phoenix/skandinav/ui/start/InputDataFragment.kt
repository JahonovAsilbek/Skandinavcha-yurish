package uz.phoenix.skandinav.ui.start

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentInputDataBinding
import phoenix.skandinav.databinding.InfoDialogBinding
import uz.phoenix.skandinav.MainActivity
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.UserData


class InputDataFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var binding: FragmentInputDataBinding
    private var speed = ""
    private var age = ""
    private var height = 0
    private var heightStick = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputDataBinding.inflate(layoutInflater)

        setSeekbar()
        speedClick()
        ageClick()
        infoClick()
        continueClick()

        return binding.root
    }

    private fun continueClick() {
        binding.continueBtn.setOnClickListener {
            if (height != 120) {
                if (speed.isNotEmpty()) {
                    if (age.isNotEmpty()) {
                        val userData =
                            UserData(height.toString(), speed, age, heightStick.toString())
                        UserDatabase.Get.getUserDatabase().getDao().insertUserData(userData)

                        startActivity(Intent(activity, MainActivity::class.java))
                        requireActivity().finish()
                    } else Toast.makeText(binding.root.context, "Yoshni tanlang", Toast.LENGTH_LONG)
                        .show()
                } else Toast.makeText(
                    binding.root.context,
                    "Yurish tezligini tanlang",
                    Toast.LENGTH_LONG
                ).show()
            } else Toast.makeText(binding.root.context, "Bo'yingizni tanlang", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun infoClick() {
        binding.info.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
            val alertDialog = dialog.create()
            val view = InfoDialogBinding.inflate(layoutInflater, null, false)
            view.ok.setOnClickListener {
                alertDialog.cancel()
            }
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    private fun ageClick() {
        binding.age7.setOnClickListener {
            clearAgeVisibility()
            binding.age7.setTextColor(resources.getColor(R.color.main_blue))
            binding.agePicker7.visibility = View.VISIBLE
            age = "7-8 yosh"
        }
        binding.age9.setOnClickListener {
            clearAgeVisibility()
            binding.age9.setTextColor(resources.getColor(R.color.main_blue))
            binding.agePicker9.visibility = View.VISIBLE
            age = "9-10 yosh"
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun speedClick() {

        binding.high.setOnClickListener {
            clearSpeedVisibility()
            binding.high.setTextColor(resources.getColor(R.color.main_blue))
            binding.speedPickerHigh.visibility = View.VISIBLE
            speed = "Yuqori"
        }

        binding.normal.setOnClickListener {
            clearSpeedVisibility()
            binding.normal.setTextColor(resources.getColor(R.color.main_blue))
            binding.speedPickerNormal.visibility = View.VISIBLE
            speed = "O'rtacha"
        }

        binding.slow.setOnClickListener {
            clearSpeedVisibility()
            binding.slow.setTextColor(resources.getColor(R.color.main_blue))
            binding.speedPickerSlow.visibility = View.VISIBLE
            speed = "Sekin"
        }

    }

    private fun clearSpeedVisibility() {
        binding.high.setTextColor(resources.getColor(R.color.main_blue_40))
        binding.normal.setTextColor(resources.getColor(R.color.main_blue_40))
        binding.high.setTextColor(resources.getColor(R.color.main_blue_40))
        binding.speedPickerHigh.visibility = View.INVISIBLE
        binding.speedPickerNormal.visibility = View.INVISIBLE
        binding.speedPickerSlow.visibility = View.INVISIBLE
    }

    @SuppressLint("ResourceAsColor")
    private fun clearAgeVisibility() {
        binding.age7.setTextColor(resources.getColor(R.color.main_blue_40))
        binding.age9.setTextColor(resources.getColor(R.color.main_blue_40))
        binding.agePicker7.visibility = View.INVISIBLE
        binding.agePicker9.visibility = View.INVISIBLE
    }

    private fun setSeekbar() {
        val max = 170
        val min = 70
        val total = max - min

        val slider = binding.seekbar
        slider.positionListener = { pos ->
            val height = min + (total * pos).toInt()
            val heightStick = (height * 0.68).toInt()
            slider.bubbleText = height.toString()
            binding.height.text = "Bo'yingiz\n$height"
            binding.stickHeight.text = "Tayoq\nuzunligi\n${heightStick}"
            binding.stickHeight2.text = "$heightStick cm"
            this.height = height
            this.heightStick = heightStick
        }
        slider.position = 0.5f
        slider.startText = "$min"
        slider.endText = "$max"

    }
}