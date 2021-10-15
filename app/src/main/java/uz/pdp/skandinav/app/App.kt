package uz.pdp.skandinav.app

import android.app.Application
import uz.pdp.skandinav.database.UserDatabase

class App : Application() {
    override fun onCreate() {
        UserDatabase.init(this)
        super.onCreate()
    }
}