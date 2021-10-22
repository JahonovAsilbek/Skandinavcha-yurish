package uz.phoenix.skandinav.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.phoenix.skandinav.database.daos.TrainingDao
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.database.entities.Training

@Database(entities = [Training::class, Month::class], version = 1, exportSchema = false)
abstract class TrainingDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao

    companion object {
        @Volatile
        private var database: TrainingDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        TrainingDatabase::class.java,
                        "training.db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
    }

    object Get {
        fun getTrainingDatabase(): TrainingDatabase = database!!
    }
}