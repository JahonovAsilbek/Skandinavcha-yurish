package uz.phoenix.skandinav.database.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "finished")
class Finished {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var finishedMonthId: Int? = null
    var finishedTrainingName: String? = null
    var writedToDaily: Boolean? = null

    constructor()

    @Ignore
    constructor(finishedMonthId: Int?, finishedTrainingName: String?, writedToDaily: Boolean?) {
        this.finishedMonthId = finishedMonthId
        this.finishedTrainingName = finishedTrainingName
        this.writedToDaily = writedToDaily
    }
}