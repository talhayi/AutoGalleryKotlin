package com.example.autogallerykotlin.data.repository

import com.example.autogallerykotlin.data.model.AdvertiseDetail
import com.example.autogallerykotlin.data.model.AdvertiseDetailImage
import com.example.autogallerykotlin.data.model.ChangeFavoriteText
import com.example.autogallerykotlin.data.model.FavoriteAdvertise
import com.example.autogallerykotlin.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class AdvertiseDetailRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun advertiseDetail(
        advert_id: String
    ): Response<AdvertiseDetail> {
        return apiService.advertiseDetail(
            advert_id
        )
    }

    suspend fun advertiseDetailImage(
        advert_id: String
    ): List<AdvertiseDetailImage> {
        return apiService.advertiseDetailImage(advert_id)

    }

    suspend fun changeFavoriteText(
        user_id:String,
        advert_id: String
    ): Response<ChangeFavoriteText> {
        return apiService.changeFavoriteText(
            user_id,advert_id
        )
    }

    suspend fun favoriteAdvertise(
        user_id:String,
        advert_id: String
    ): Response<FavoriteAdvertise> {
        return apiService.favoriteAdvertise(
            user_id,advert_id
        )
    }


}