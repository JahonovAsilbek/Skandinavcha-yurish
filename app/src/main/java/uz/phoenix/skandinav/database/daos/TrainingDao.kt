package uz.phoenix.skandinav.database.daos

import androidx.room.*
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.database.entities.Training

@Dao
interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraining(training: Training)

    @Query("select * from training where monthId=:monthId")
    fun getTraining(monthId: Int): List<Training>

    @Query("select * from training where id=:id")
    fun getTrainingById(id:Int):Training

    @Update
    fun updateTraining(training: Training)

    @Query("select * from month")
    fun getAllMonth(): List<Month>

    @Query("select * from main_part where taskId=:taskId")
    fun getMainPart(taskId: Int): MainPart
}