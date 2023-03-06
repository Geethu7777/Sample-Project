package com.fin.sampletest.api

import com.fin.sampletest.base.BaseApplication
import okhttp3.*
import java.net.SocketTimeoutException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object UnsafeOkHttpClient {
    // Create a trust manager that does not validate certificate chains
    var contex = BaseApplication.getContext()
    /*var pageKeyValue: PageKeyValue
    var langValue: Boolean

     init {
         langValue = SharedPreference.getStringPref(Constants.LANG_PREFERENCE).equals("en")
         pageKeyValue =
             AppDatabase.getDatabase(contex).languageDao().getAllData().pageKeyValue
     }*/

    val unsafeOkHttpClient:
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager
            OkHttpClient
        get() = try { // Create a trust manager that does not validate certificate chains
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(ApiFactory.TIME_OUT, TimeUnit.SECONDS)
            builder.readTimeout(ApiFactory.TIME_OUT, TimeUnit.SECONDS)
            builder.writeTimeout(ApiFactory.TIME_OUT, TimeUnit.SECONDS)
            builder.addInterceptor { chain ->

                val request: Request = chain.request().newBuilder()
                    .addHeader("content-type", "application/json")

                    .build()
                var response: Response? = null
                try {
                    response = chain.proceed(request)
                } catch (error: Exception) {
                }

                when {
                    response?.code() == 400 -> {
                        if (BaseApplication.forground) {
                            //todo handle network errors

                        }
                        return@addInterceptor response
                    }
                    response?.code() == 401 -> {

                       return@addInterceptor response
                    }
                    response?.code() == 500 -> {
                        return@addInterceptor response
                    }
                    else -> {
                        if (response == null) {
                            throw SocketTimeoutException()
                        } else return@addInterceptor response
                    }
                }
            }

            builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            builder.hostnameVerifier { hostname, session -> true }
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }





}

