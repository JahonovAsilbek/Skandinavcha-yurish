package uz.phoenix.skandinav.database.daos

import androidx.room.*
import uz.phoenix.skandinav.database.entities.Finished
import uz.phoenix.skandinav.database.entities.History
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

    @Query("select finishedTrainingName from finished where finishedMonthId=:monthId and writedToDaily=0")
    fun getFinishedTrainingByMonthId(monthId: Int): List<String>

    @Query("select * from finished where finishedMonthId=:monthId and finishedTrainingName=:name")
    fun getFinishedTrainingByName(monthId: Int, name: String): List<Finished>

    @Query("delete from finished where finishedMonthId=:monthId and finishedTrainingName=:name")
    fun deleteFinishedTraining(monthId: Int, name: String)

    @Query("select * from finished")
    fun getAllFinishedTraining(): List<Finished>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History)

    @Query("select * from history where monthId=:monthId")
    fun getHistoryByMonthId(monthId: Int):List<History>
}