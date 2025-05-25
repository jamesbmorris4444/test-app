package com.jbm.testapp.repository.storage

import com.google.gson.annotations.SerializedName

class Fruit {
    @SerializedName("name") var name: String= ""
    @SerializedName("id") var id: Int = 0
    @SerializedName("family") var family: String = ""
    @SerializedName("order") var order: String = ""
    @SerializedName("genus") var genus: String = ""
    @SerializedName("nutritions") var nutritions: Nutritions = Nutritions()

    inner class Nutritions {
        @SerializedName("calories") var calories: Int = 0
        @SerializedName("fat") var fat: Float = 0f
        @SerializedName("sugar") var sugar: Float = 0f
        @SerializedName("carbohydrates") var carbohydrates:  Float = 0f
        @SerializedName("protein") var protein:  Float = 0f
    }
}