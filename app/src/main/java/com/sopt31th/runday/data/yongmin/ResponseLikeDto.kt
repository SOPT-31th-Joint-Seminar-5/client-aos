package com.sopt31th.runday.data.yongmin

data class ResponseLikeDto(
    val data: Data,
    val message: String,
    val status: Int
){
    data class Data(
        val id: Int,
        val isLiked: Boolean,
        val runId: Int,
        val userId: Int
    )
}