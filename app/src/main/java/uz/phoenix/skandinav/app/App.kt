package uz.phoenix.skandinav.app

import android.app.Application
import uz.phoenix.skandinav.database.TrainingDatabase
import uz.phoenix.skandinav.database.UserDatabase

class App : Application() {
    override fun onCreate() {
        UserDatabase.init(this)
        TrainingDatabase.init(this)
        super.onCreate()
    }
}