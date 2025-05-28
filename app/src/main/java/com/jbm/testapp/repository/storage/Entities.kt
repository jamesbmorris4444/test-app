package com.jbm.testapp.repository.storage

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language

class Country {
    @SerializedName("capital") var capital: String= ""
    @SerializedName("code") var code: String = ""
    @SerializedName("currency") var currency: Currency = Currency()
    @SerializedName("flag") var flag: String = ""
    @SerializedName("language") var language: Language = Language()
    @SerializedName("name") var name: String= ""
    @SerializedName("region") var region: String = ""

    inner class Currency {
        @SerializedName("code") var code: String = ""
        @SerializedName("name") var name: String = ""
        @SerializedName("symbol") var symbol: String = ""
    }

    inner class Language {
        @SerializedName("code") var code: String = ""
        @SerializedName("name") var name: String = ""
    }
}