package it.marcodallaba.compostetest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.marcodallaba.compostetest.data.remote.AppleRepository
import it.marcodallaba.compostetest.data.remote.AppleApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppleRepository(
        api: AppleApi
    ) = AppleRepository(api)

    @Singleton
    @Provides
    fun provideAppleApi(): AppleApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rss.applemarketingtools.com/api/v2/")
            .build()
            .create(AppleApi::class.java)
    }
}