package uz.phoenix.skandinav.app

import android.app.Application
import uz.phoenix.skandinav.database.UserDatabase

class App : Application() {
    override fun onCreate() {
        UserDatabase.init(this)
        super.onCreate()
    }
}