package br.com.appforge.kotlinhearthstonecards.di

import br.com.appforge.kotlinhearthstonecards.data.remote.HearthstoneAPI
import br.com.appforge.kotlinhearthstonecards.data.repository.CardRepositoryImpl
import br.com.appforge.kotlinhearthstonecards.domain.repository.CardRepository
import br.com.appforge.kotlinhearthstonecards.domain.useCase.GetAllCardsUseCase
import br.com.appforge.kotlinhearthstonecards.services.AuthInterceptor
import br.com.appforge.kotlinhearthstonecards.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.HEARTHSTONE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideHearthstoneAPI(retrofit: Retrofit):HearthstoneAPI{
        return retrofit.create(HearthstoneAPI::class.java)
    }

    @Provides
    fun provideOkHttp(authInterceptor: AuthInterceptor):OkHttpClient{
        val okHttp = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor())
            .build()
        return okHttp
    }

    @Provides
    fun provideAuthInterceptor():AuthInterceptor{
        return AuthInterceptor()
    }

    @Provides
    fun provideCardRepository(hearthstoneAPI: HearthstoneAPI):CardRepository{
        return CardRepositoryImpl(hearthstoneAPI)
    }

    @Provides
    fun provideGetAllCardsUseCase(cardRepository: CardRepository):GetAllCardsUseCase{
        return GetAllCardsUseCase(cardRepository)
    }

}