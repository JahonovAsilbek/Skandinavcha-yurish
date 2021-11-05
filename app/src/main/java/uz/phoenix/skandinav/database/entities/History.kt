package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class History {
    @PrimaryKey
    var id: Int? = null
    var monthId: Int? = null
    var trainingName: String? = null
    var health: String? = null
    var mood: String? = null
    var appetite: String? = null
    var desire: String? = null
    var sleep: String? = null
    var trainingStartTime: String? = null
    var trainingEndTime: String? = null
    var nordWalkingLength: String? = null
    var nordWalkingTime: String? = null
    var trainingEntrancePeriod: String? = null
    var trainingMainPartPeriod: String? = null
    var trainingEndPartPeriod: String? = null
    var fatigue: String? = null
    var disorder: String? = null

    @Ignore
    constructor(
        monthId: Int?,
        trainingName: String?,
        health: String?,
        mood: String?,
        appetite: String?,
        desire: String?,
        sleep: String?,
        trainingStartTime: String?,
        trainingEndTime: String?,
        nordWalkingLength: String?,
        nordWalkingTime: String?,
        trainingEntrancePeriod: String?,
        trainingMainPartPeriod: String?,
        trainingEndPartPeriod: String?,
        fatigue: String?,
        disorder: String?
    ) {
        this.monthId = monthId
        this.trainingName = trainingName
        this.health = health
        this.mood = mood
        this.appetite = appetite
        this.desire = desire
        this.sleep = sleep
        this.trainingStartTime = trainingStartTime
        this.trainingEndTime = trainingEndTime
        this.nordWalkingLength = nordWalkingLength
        this.nordWalkingTime = nordWalkingTime
        this.trainingEntrancePeriod = trainingEntrancePeriod
        this.trainingMainPartPeriod = trainingMainPartPeriod
        this.trainingEndPartPeriod = trainingEndPartPeriod
        this.fatigue = fatigue
        this.disorder = disorder
    }

    constructor()
}