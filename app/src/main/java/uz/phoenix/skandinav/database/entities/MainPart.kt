package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class MainPart {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var taskId: Int? = null
    var task1Text: String? = null
    var task1Video: String? = null
    var task2Text: String? = null
    var task2Video: String? = null
    var task3Text: String? = null
    var task3Video: String? = null
}