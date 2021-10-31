package uz.phoenix.skandinav.database.entities

class Rating {
    var name: String? = null
    var surname: String? = null
    var point: Int? = null

    constructor()

    constructor(name: String?, surname: String?, point: Int?) {
        this.name = name
        this.surname = surname
        this.point = point
    }

}
