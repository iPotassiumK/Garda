package com.example.garda.listsearch

class PlantsEntity {
    var txt_name :String = ""
    var txt_science : String = ""
    var imgPlants : String = ""

    constructor() {
    }

    constructor(txt_name : String, txt_science : String, imgPlants : String){
        this.txt_name = txt_name
        this.txt_science = txt_science
        this.imgPlants = imgPlants
    }
}