package uz.phoenix.skandinav.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.phoenix.skandinav.database.daos.UserDao
import uz.phoenix.skandinav.database.entities.Finished
import uz.phoenix.skandinav.database.entities.User
import uz.phoenix.skandinav.database.entities.UserData

@Database(
    entities = [User::class, UserData::class, Finished::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getDao(): UserDao

    companion object {
        @Volatile
        private var database: UserDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build()
                }
            }
        }
    }

    object Get {
        fun getUserDatabase(): UserDatabase = database!!
    }
}