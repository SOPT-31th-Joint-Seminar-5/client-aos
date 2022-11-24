package com.sopt31th.runday.data.ahra

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageService{
    @GET("myPage/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ): Call<ResponseMyPageDTO>

}