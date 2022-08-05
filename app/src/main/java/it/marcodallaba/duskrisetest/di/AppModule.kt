package it.marcodallaba.duskrisetest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.marcodallaba.duskrisetest.data.remote.iTunesRepository
import it.marcodallaba.duskrisetest.data.remote.iTunesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideITunesRepository(
        api: iTunesApi
    ) = iTunesRepository(api)

    @Singleton
    @Provides
    fun provideITunesService(): iTunesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rss.applemarketingtools.com/api/v2/")
            .build()
            .create(iTunesApi::class.java)
    }
}