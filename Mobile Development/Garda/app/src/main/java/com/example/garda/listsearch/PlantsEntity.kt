package com.example.garda.listsearch

class PlantsEntity {
    var name : String? = null
    var science : String? = null
    var imgPlants : String? = null

    constructor(){
    }

    constructor(name : String?, science : String?, imgPlants : String?){
        this.name = name
        this.science = science
        this.imgPlants = imgPlants
    }
}