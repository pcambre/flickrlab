package com.pcambre.flickrlab.di

import com.pcambre.flickrlab.BuildConfig.BASE_URL
import com.pcambre.flickrlab.data.repository.FlickrPhotoRepository
import com.pcambre.flickrlab.data.repository.FlickrPhotoRepositoryImpl
import com.pcambre.flickrlab.data.service.FlickrApi
import com.pcambre.flickrlab.data.service.FlickrInterceptor
import com.pcambre.flickrlab.data.service.FlickrRemoteDataSource
import com.pcambre.flickrlab.domain.usecase.SearchPhotosUseCase
import com.pcambre.flickrlab.ui.home.MainScreenViewModel
import com.pcambre.flickrlab.ui.home.SearchViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val baseDIModule = module {

    //region view models

    viewModel { MainScreenViewModel() }
    viewModel { SearchViewModel(get()) }

    //endregion

    //region repositories

    factory<FlickrPhotoRepository> { FlickrPhotoRepositoryImpl(get()) }

    //endregion

    //region Use cases

    factory { SearchPhotosUseCase(get()) }

    //endregion

    //region Networking
    factory { FlickrInterceptor() }
    factory { provideHttpLoggingInterceptor() }
    factory { provideOkHttpClient(get(), get()) }
    factory { provideMoshi() }
    factory { provideMoshiConverter(get()) }
    factory { createWebService<FlickrApi>(get(), get()) }
    factory { FlickrRemoteDataSource(get()) }
    //endregion
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    return interceptor
}

private fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory =
    MoshiConverterFactory.create(moshi)

fun <T> createWebService(
    okHttpClient: OkHttpClient,
    clazz: Class<T>,
    moshiConverterFactory: MoshiConverterFactory
): T {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create(clazz)
}

fun provideOkHttpClient(
    flickrInterceptor: FlickrInterceptor,
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .followRedirects(false)
        .cache(null)
        .addInterceptor(flickrInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory
): T = createWebService(okHttpClient, T::class.java, moshiConverterFactory)