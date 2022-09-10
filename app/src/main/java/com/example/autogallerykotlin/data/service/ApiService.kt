package com.example.autogallerykotlin.data.service


import com.example.autogallerykotlin.data.model.Login
import com.example.autogallerykotlin.data.model.Register
import com.example.autogallerykotlin.data.model.Verification
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Login>


    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<Register>

    @FormUrlEncoded
    @POST("verification.php")
    suspend fun verification(
        @Field("email") email: String,
        @Field("code") code: String
    ): Response<Verification>
}