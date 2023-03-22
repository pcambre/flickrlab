package com.pcambre.flickrlab.data.service

import com.pcambre.flickrlab.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val APY_KEY = "api_key"
private const val FORMAT = "format"
private const val NO_JSON_CALLBACK = "nojsoncallback"

class FlickrInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().apply {
            val newUrl = this.url.newBuilder()
                .addQueryParameter(APY_KEY, BuildConfig.FLICKR_API_KEY)
                .addQueryParameter(FORMAT, "json")
                    //Raw json
                .addQueryParameter(NO_JSON_CALLBACK, "1")
                .build()

            val newRequest = newBuilder().url(newUrl).build()

            return chain.proceed(newRequest)
        }
    }
}