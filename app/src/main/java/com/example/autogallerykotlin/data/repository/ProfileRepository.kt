package com.example.autogallerykotlin.data.repository

import com.example.autogallerykotlin.data.model.InformationProfile
import com.example.autogallerykotlin.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiService: ApiService
)  {

    suspend fun informationProfile(
        user_id: String
    ): Response<InformationProfile> {
        return apiService.informationProfile(user_id)

    }

}