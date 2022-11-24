package com.sopt31th.runday.data

import com.sopt31th.runday.data.yongmin.RequestLikeDto
import com.sopt31th.runday.data.yongmin.ResponseExerciseDto
import com.sopt31th.runday.data.yongmin.ResponseLikeDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RundayService {
    @GET("run/{userId}")
    fun getTimeExercise(
        @Path("userId") userId: Int
    ): Call<ResponseExerciseDto>

    @PUT("run/like/{isLikedId}")
    fun putExerciseLike(
        @Path("isLikedId") isLikedId: Int,
        @Body body: RequestLikeDto
    ): Call<ResponseLikeDto>

    @PUT("run/like/delete/{isLikedId}")
    fun deleteExerciseLike(
        @Path("isLikedId") isLikedId: Int,
        @Body body: RequestLikeDto
    ): Call<ResponseLikeDto>
}
