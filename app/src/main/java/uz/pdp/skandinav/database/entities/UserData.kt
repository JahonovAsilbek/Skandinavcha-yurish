package uz.pdp.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class UserData {
    @PrimaryKey
    var height: String = ""
    var speed: String? = null
    var age: String? = null
    var stickLength: String? = null

    @Ignore
    constructor(height: String, speed: String?, age: String?, stickLength: String?) {
        this.height = height
        this.speed = speed
        this.age = age
        this.stickLength = stickLength
    }

    constructor()
}