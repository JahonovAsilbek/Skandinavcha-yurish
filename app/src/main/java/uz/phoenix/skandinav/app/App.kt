package uz.phoenix.skandinav.app

import android.app.Application
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.UserDatabase

class App : Application() {
    override fun onCreate() {
        UserDatabase.init(this)
        AppDatabase.init(this)
        super.onCreate()
    }
}