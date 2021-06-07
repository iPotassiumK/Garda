package com.example.garda.listsearch

class PlantsEntity {
    var name :String = ""
    var science : String = ""
    var imgPlants : String = ""

    constructor() {
    }

    constructor(txt_name : String, txt_science : String, imgPlants : String){
        this.name = txt_name
        this.science = txt_science
        this.imgPlants = imgPlants
    }
}