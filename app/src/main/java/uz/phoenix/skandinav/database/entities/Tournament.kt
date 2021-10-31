package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tournament")
class Tournament : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var text: String? = null
}
