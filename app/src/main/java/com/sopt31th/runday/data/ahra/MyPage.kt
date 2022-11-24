package com.sopt31th.runday.data.ahra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyPageDTO(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserInfo
){
    @Serializable
    data class UserInfo(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("time")
        val time: String,
        @SerialName("distance")
        val distance: String,
        @SerialName("pace")
        val pace: String,
        @SerialName("calorie")
        val calorie: String,
        @SerialName("date")
        val date: String
    )
}