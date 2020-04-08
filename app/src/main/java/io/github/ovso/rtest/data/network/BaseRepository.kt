package io.github.ovso.rtest.data.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.github.com"

abstract class BaseRepository<T>(
  private val baseUrl: String,
  private val cls: Class<T>,
  private val token: String
) {

  fun api(): T = createRetrofit().create(cls)

  private fun createRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .client(createClient())
      .build()
  }

  private fun createClient(): OkHttpClient {


    val httpClient = OkHttpClient.Builder()
    with(httpClient) {
      readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
      connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
      followRedirects(false)
      addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
          .header("Content-Type", "application/json")
          .addHeader("Authorization", "token $token")
        val request = requestBuilder.build()
        chain.proceed(request)
      }
    }
    return httpClient.build()
  }

  companion object {
    const val TIMEOUT_SECONDS = 60
  }
}
