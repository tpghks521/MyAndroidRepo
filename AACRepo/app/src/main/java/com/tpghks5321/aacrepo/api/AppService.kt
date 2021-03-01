package com.tpghks5321.aacrepo.api

import com.tpghks5321.aacrepo.data.KakaoImageList
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

	@GET("/v2/search/image")
	fun getSearch(
			@Query("query") query: String,
			@Query("page") page: Int,
			@Query("size") size: Int
	): Call<KakaoImageList>


	companion object {
		private const val BASE_URL = " https://dapi.kakao.com"

		fun create(): AppService {
			val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
			val logge2r = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

			val interceptor = Interceptor {
				val newRequest = it.request().newBuilder().addHeader("Authorization", "").build()
				return@Interceptor it.proceed(newRequest)
			}


			val client = OkHttpClient.Builder()
					.addInterceptor(logger)
					.addInterceptor(logge2r)
					.addInterceptor(interceptor)
					.build()

			return Retrofit.Builder()
					.baseUrl(BASE_URL)
					.client(client)
					.addConverterFactory(GsonConverterFactory.create())
					.build()
					.create(AppService::class.java)
		}
	}


}