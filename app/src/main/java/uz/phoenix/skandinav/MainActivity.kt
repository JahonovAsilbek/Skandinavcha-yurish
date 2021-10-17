package uz.phoenix.skandinav

import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import phoenix.skandinav.R
import phoenix.skandinav.databinding.ActivityMainBinding
import phoenix.skandinav.databinding.InfoDialogBinding


class MainActivity : AppCompatActivity() {

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

        backClick()
        infoClick()
        settingsClick()

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

    private fun backClick() {
        binding.layout.back.setOnClickListener {
            binding.drawerLayout.closeDrawers()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}