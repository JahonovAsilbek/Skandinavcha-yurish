package uz.phoenix.skandinav.database.daos

import androidx.room.*
import uz.phoenix.skandinav.database.entities.Training

@Dao
interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraining(training: Training)

    @Query("select name from training")
    fun getAllTraining(): List<String>

    @Query("select * from training where monthId=:monthId")
    fun getTraining(monthId: Int): List<Training>

    @Update
    fun updateTraining(training: Training)

}