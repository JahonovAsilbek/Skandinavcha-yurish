package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "main_part")
class MainPart : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var taskId: Int? = null
    var task1Text: String? = null
    var task1Video: String? = null
    var task2Text: String? = null
    var task2Video: String? = null
    var task3Text: String? = null
    var task3Video: String? = null
    var actionGame: String? = null
    var actionGameImage: String? = null
    var nordWalking: String? = null

    constructor(
        id: Int?,
        taskId: Int?,
        task1Text: String?,
        task1Video: String?,
        task2Text: String?,
        task2Video: String?,
        task3Text: String?,
        task3Video: String?,
        actionGame: String?,
        actionGameImage: String?,
        nordWalking: String?
    ) {
        this.id = id
        this.taskId = taskId
        this.task1Text = task1Text
        this.task1Video = task1Video
        this.task2Text = task2Text
        this.task2Video = task2Video
        this.task3Text = task3Text
        this.task3Video = task3Video
        this.actionGame = actionGame
        this.actionGameImage = actionGameImage
        this.nordWalking = nordWalking
    }
}