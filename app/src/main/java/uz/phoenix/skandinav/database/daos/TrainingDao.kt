package uz.phoenix.skandinav.database.daos

import androidx.room.*
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.database.entities.Tournament
import uz.phoenix.skandinav.database.entities.Training

@Dao
interface TrainingDao {

    @Query("select * from training where monthId=:monthId")
    fun getTraining(monthId: Int): List<Training>

    @Query("select * from training where id=:id")
    fun getTrainingById(id: Int): Training

    @Update
    fun updateTraining(training: Training)

    @Query("update training set isFinished=1 where id=:id")
    fun finishTraining(id: Int)

    @Query("select * from month")
    fun getAllMonth(): List<Month>

    @Query("select name from month")
    fun getAllMonthName(): List<String>

    @Query("select * from main_part where taskId=:taskId")
    fun getMainPart(taskId: Int): MainPart

    @Query("select * from tournament")
    fun getTournament(): List<Tournament>
}