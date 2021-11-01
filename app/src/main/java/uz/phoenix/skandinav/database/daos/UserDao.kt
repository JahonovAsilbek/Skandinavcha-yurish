package uz.phoenix.skandinav.database.daos

import androidx.room.*
import uz.phoenix.skandinav.database.entities.Finished
import uz.phoenix.skandinav.database.entities.User
import uz.phoenix.skandinav.database.entities.UserData

@Dao
interface UserDao {

    // funs for user

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from user")
    fun getUser(): User

    // funs for user datas

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userData: UserData)

    @Update
    fun updateUserData(userData: UserData)

    @Query("select * from userdata")
    fun getUserData(): UserData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFinishedTraining(finished: Finished)

    @Query("select finishedTrainingName from finished where finishedMonthId=:monthId ")
    fun getFinishedTrainingByMonthId(monthId: Int): List<String>

    @Query("select * from finished where finishedMonthId=:monthId and finishedTrainingName=:name")
    fun getFinishedTrainingByName(monthId: Int, name: String): List<Finished>
}