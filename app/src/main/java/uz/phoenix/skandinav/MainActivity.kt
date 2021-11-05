package uz.phoenix.skandinav

import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import phoenix.skandinav.R
import phoenix.skandinav.databinding.ActivityMainBinding
import phoenix.skandinav.databinding.InfoDialogBinding
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.User
import uz.phoenix.skandinav.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity(), SettingsFragment.ProfileChangeListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, phoenix.skandinav.R.color.white)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        navView.setupWithNavController(navController)

        infoClick()
        settingsClick()
        ratingClick()
        setDataToDrawer()
        historyClick()
    }

    private fun historyClick() {
        binding.history.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("history", "history")
            binding.drawerLayout.closeDrawers()
            findNavController(R.id.nav_host_fragment_content_main).navigate(
                R.id.monthFragment,
                bundle
            )
        }
    }

    private fun ratingClick() {
        binding.rating.setOnClickListener {
            binding.drawerLayout.closeDrawers()
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.ratingFragment)
        }
    }

    fun setDataToDrawer() {
        val profileImage = findViewById<CircleImageView>(R.id.profile_image_circle)
        val name = findViewById<TextView>(R.id.name_drawer)
        val surname = findViewById<TextView>(R.id.surname_drawer)
        val user = UserDatabase.Get.getUserDatabase().getDao().getUser()

        if (user != null) {
            if (user.imagePath?.isNotEmpty() == true) {
                profileImage.setImageURI(
                    Uri.parse(
                        user.imagePath
                    )
                )
            } else profileImage.setImageResource(R.drawable.img1)
            name.text = user.name
            surname.text = user.surname
        }
    }

    private fun settingsClick() {
        binding.settings.setOnClickListener {
            binding.drawerLayout.closeDrawers()
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.settingsFragment)
        }
    }

    private fun infoClick() {
        binding.aboutApp.setOnClickListener {
            binding.drawerLayout.closeDrawers()
            val dialog = AlertDialog.Builder(this, R.style.RoundedCornersDialog)
            val alertDialog = dialog.create()
            val view = InfoDialogBinding.inflate(layoutInflater, null, false)
            view.root.setBackgroundColor(resources.getColor(R.color.white))
            view.ok.setOnClickListener {
                alertDialog.cancel()
            }
            view.title.text = "Ilova haqida"
            view.text.setText(R.string.about_app)
            alertDialog.setView(view.root)
            alertDialog.show()
        }
    }

    fun openDrawer() {
        binding.drawerLayout.openDrawer(Gravity.START)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun dataChangeListener(user: User) {
        val profileImage = findViewById<CircleImageView>(R.id.profile_image_circle)
        val name = findViewById<TextView>(R.id.name_drawer)
        val surname = findViewById<TextView>(R.id.surname)
        if (user.imagePath!!.isNotEmpty()) {
            profileImage.setImageURI(
                Uri.parse(
                    user.imagePath
                )
            )
        } else profileImage.setImageResource(R.drawable.img1)
        name.text = user.name
        surname.text = user.surname
    }
}