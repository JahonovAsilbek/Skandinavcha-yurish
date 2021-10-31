package uz.phoenix.skandinav.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.phoenix.skandinav.database.daos.TrainingDao
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.database.entities.Tournament
import uz.phoenix.skandinav.database.entities.Training

@Database(
    entities = [Month::class, Training::class, MainPart::class, Tournament::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTrainingDao(): TrainingDao

    companion object {
        @Volatile
        private var database: AppDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "skandinav.db"
                    )
                        .createFromAsset("skandinav.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
        }
    }

    object GET {
        fun getTrainingDatabase(): AppDatabase = database!!
    }
}