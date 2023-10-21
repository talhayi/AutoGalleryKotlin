package com.example.autogallerykotlin.data.repository

import com.example.autogallerykotlin.data.model.InformationProfile
import com.example.autogallerykotlin.data.model.UpdateProfile
import com.example.autogallerykotlin.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun informationProfile(
        user_id: String
    ): Response<InformationProfile> {
        return apiService.informationProfile(user_id)
    }
    suspend fun updateProfile(
        user_id: String,
        email: String,
      /*  password: String,
        againPassword: String,
        phoneNumber: String,
        city: String,
        district: String,
        neighborhood: String,*/
    ): Response<UpdateProfile> {
        return apiService.updateProfile(
            user_id,
            email,
          /*  password,
            againPassword,
            phoneNumber,
            city,
            district,
            neighborhood,*/
        )
    }
}