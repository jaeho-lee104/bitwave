package com.ecohouse.bitwave.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author leejaeho on 2021. 03. 22..
 */
data class Market(
    @Expose
    @SerializedName("market")
    private var _market: String?,

    @Expose
    @SerializedName("korean_name")
    private var _koreanName: String?,

    @Expose
    @SerializedName("english_name")
    private var _englishName: String?,
) {
    val market: String
        get() = _market ?: ""

    val koreanName: String
        get() = _koreanName ?: ""

    val englishName: String
        get() = _englishName ?: ""
}