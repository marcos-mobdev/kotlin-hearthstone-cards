package br.com.appforge.kotlinhearthstonecards.services

import br.com.appforge.kotlinhearthstonecards.util.Constants
import br.com.appforge.kotlinhearthstonecards.util.Secret
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestConstructor = chain.request().newBuilder()
        val request = requestConstructor
            .addHeader("x-rapidapi-key", Secret.API_KEY)
            .addHeader("x-rapidapi-host", Constants.HEARTHSTONE_API_HOST)
            .build()
        return chain.proceed(request)
    }
}