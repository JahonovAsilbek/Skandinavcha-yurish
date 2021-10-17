package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey
    var name: String = ""
    var surname: String? = null
    var birthdate: String? = null
    var phoneNumber: String? = null
    var info: String? = null

    @Ignore
    constructor(
        name: String,
        surname: String?,
        birthdate: String?,
        phoneNumber: String?,
        info: String?
    ) {
        this.name = name
        this.surname = surname
        this.birthdate = birthdate
        this.phoneNumber = phoneNumber
        this.info = info
    }

    constructor()
}