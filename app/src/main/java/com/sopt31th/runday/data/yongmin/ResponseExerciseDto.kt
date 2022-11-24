package com.sopt31th.runday.data.yongmin

import com.google.gson.annotations.SerializedName

data class ResponseExerciseDto(
    val data: List<Data>,
    val message: String,
    val status: Int
) {
    data class Data(
        val highlight: String,
        val id: Int,
        @SerializedName("is_liked")
        val isLiked: Boolean,
        val routine: String,
        val stage: String,
        val title: String
    )
}
