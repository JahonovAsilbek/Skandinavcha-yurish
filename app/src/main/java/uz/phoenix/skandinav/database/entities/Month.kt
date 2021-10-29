package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "month")
class Month : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name: String? = null

    @Ignore
    constructor(id: Int?, name: String?) {
        this.id = id
        this.name = name
    }

    constructor()
}