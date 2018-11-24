package business.curry.thepiekie.space.business.data.remote

import android.content.Context
import android.provider.SyncStateContract
import business.curry.thepiekie.space.business.data.local.PrefsDataStore
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(val prefsDataStore: PrefsDataStore, val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .addHeader("authorization", "Bearer " + prefsDataStore.getAccessToken(context))
            .build()

        return chain.proceed(request)
    }

}