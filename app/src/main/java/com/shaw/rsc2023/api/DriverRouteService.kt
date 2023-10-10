package com.shaw.rsc2023.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class DriverRouteService {
    fun getDriverRouteApiService(): DriverRouteApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            this.connectTimeout(60, TimeUnit.SECONDS)
            this.writeTimeout(60, TimeUnit.SECONDS)
            this.readTimeout(60, TimeUnit.SECONDS)
        }.addInterceptor {
            try {
                if (!Thread.currentThread().isInterrupted) {
                    Thread.sleep(100)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            it.proceed(it.request())
        }
        sslConfiguration(okHttpClientBuilder)
        okHttpClientBuilder.addInterceptor(logging)
        val client = okHttpClientBuilder.build()

        val builder = Retrofit.Builder()
            .baseUrl("https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
        val retrofitAdapter = builder.build()
        return retrofitAdapter.create(DriverRouteApi::class.java)
    }

    private fun sslConfiguration(clientBuilder: OkHttpClient.Builder){
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }
        })

        val sslContext = SSLContext.getInstance("SSL")

        sslContext.init(null, trustAllCerts, SecureRandom())
        clientBuilder.sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)

        val hostnameVerifier =
            HostnameVerifier { _, _ ->
                true
            }
        clientBuilder.hostnameVerifier( hostnameVerifier);
    }

}