package uz.pdp.skandinav.database.daos

import androidx.room.*
import uz.pdp.skandinav.database.entities.User
import uz.pdp.skandinav.database.entities.UserData

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
    fun insert(userData: UserData)

    @Update
    fun updateUserData(userData: UserData)

    @Query("select * from userdata")
    fun getUserData():UserData


}