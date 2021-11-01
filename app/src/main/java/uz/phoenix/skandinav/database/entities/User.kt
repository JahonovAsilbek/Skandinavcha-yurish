package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey
    var uId = "0"
    var name: String? = null
    var surname: String? = null
    var birthday: String? = null
    var phoneNumber: String? = null
    var info: String? = null
    var imagePath: String? = null
    var point: Int = 0

    constructor()

    @Ignore
    constructor(
        name: String?,
        surname: String?,
        birthday: String?,
        phoneNumber: String?,
        info: String?,
        imagePath: String?,
        point: Int
    ) {
        this.name = name
        this.surname = surname
        this.birthday = birthday
        this.phoneNumber = phoneNumber
        this.info = info
        this.imagePath = imagePath
        this.point = point
    }

    @Ignore
    constructor(
        uId: String,
        name: String?,
        surname: String?,
        birthday: String?,
        phoneNumber: String?,
        info: String?,
        imagePath: String?,
        point: Int
    ) {
        this.uId = uId
        this.name = name
        this.surname = surname
        this.birthday = birthday
        this.phoneNumber = phoneNumber
        this.info = info
        this.imagePath = imagePath
        this.point = point
    }
}