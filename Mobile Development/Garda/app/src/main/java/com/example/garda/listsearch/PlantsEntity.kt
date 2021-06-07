package com.example.garda.listsearch

class PlantsEntity {
    var name :String = ""
    var science : String = ""
    var imgPlants : String = ""

    constructor() {
    }

    constructor(name : String, science : String, imgPlants : String){
        this.name = name
        this.science = science
        this.imgPlants = imgPlants
    }
}