package uz.pdp.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Month {
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