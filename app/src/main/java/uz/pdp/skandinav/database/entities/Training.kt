package uz.pdp.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Training {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name: String? = null
    var theoriticalInfo: String? = null
    var video: String? = null
    var preparationPart: String? = null
    var task1Text: String? = null
    var task1Video: String? = null
    var task2Text: String? = null
    var task2Video: String? = null
    var task3Text: String? = null
    var task3Video: String? = null
    var isFinished: Boolean? = null

    @Ignore
    constructor(
        id: Int?,
        name: String?,
        theoriticalInfo: String?,
        video: String?,
        preparationPart: String?,
        task1Text: String?,
        task1Video: String?,
        task2Text: String?,
        task2Video: String?,
        task3Text: String?,
        task3Video: String?,
        isFinished: Boolean?
    ) {
        this.id = id
        this.name = name
        this.theoriticalInfo = theoriticalInfo
        this.video = video
        this.preparationPart = preparationPart
        this.task1Text = task1Text
        this.task1Video = task1Video
        this.task2Text = task2Text
        this.task2Video = task2Video
        this.task3Text = task3Text
        this.task3Video = task3Video
        this.isFinished = isFinished
    }

    constructor()

    @Ignore
    constructor(
        name: String?,
        theoriticalInfo: String?,
        video: String?,
        preparationPart: String?,
        task1Text: String?,
        task1Video: String?,
        task2Text: String?,
        task2Video: String?,
        task3Text: String?,
        task3Video: String?,
        isFinished: Boolean?
    ) {
        this.name = name
        this.theoriticalInfo = theoriticalInfo
        this.video = video
        this.preparationPart = preparationPart
        this.task1Text = task1Text
        this.task1Video = task1Video
        this.task2Text = task2Text
        this.task2Video = task2Video
        this.task3Text = task3Text
        this.task3Video = task3Video
        this.isFinished = isFinished
    }
}