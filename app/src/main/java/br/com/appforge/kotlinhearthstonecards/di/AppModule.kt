package br.com.appforge.kotlinhearthstonecards.di

import androidx.lifecycle.ViewModel
import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import br.com.appforge.kotlinhearthstonecards.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.HEARTHSTONE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideHeartstoneAPI(retrofit: Retrofit):HearthstoneAPI{
        return retrofit.create(HearthstoneAPI::class.java)
    }

}