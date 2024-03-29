package legym.fuck.logic.legym.network

import android.util.Log
import legym.fuck.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy


/**
 * @author ldh
 * 时间: 2022/3/18 12:53
 *
 */
object ServiceCreator {

    private const val BASE_URL = "https://cpes.legym.cn/"

    private val okHttpClient = OkHttpClient.Builder()
        .proxy(Proxy.NO_PROXY)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    LoggingInterceptor.Builder()
                        .setLevel(Level.BASIC)
                        .log(Log.VERBOSE)
                        .build()
                )
            }
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)

    inline fun<reified T> create(): T = create(T::class.java)
}