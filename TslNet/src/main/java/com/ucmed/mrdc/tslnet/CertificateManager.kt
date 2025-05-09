package com.ucmed.mrdc.teslacore.net

import android.content.Context
import java.io.FileInputStream
import java.io.IOException
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

/**
 * Created by WX-GXM-1326 on 2018/3/21.
 */
class CertificateManager{

    companion object {

        var sslSocketFactory: SSLSocketFactory? = null

        fun getSocketFactory(mContext: Context) : SSLSocketFactory? {
            try {
                val certificate = mContext.getAssets().open("srca.cer")
                val certificateFactory = CertificateFactory.getInstance("X.509")
                val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(
                        null)
                var index = 0
                val certificateAlias = Integer.toString(index++)
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate))

                try {
                    if (certificate != null)
                        certificate!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                val sslContext = SSLContext.getInstance("TLS")

                val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())

                trustManagerFactory.init(keyStore)
                sslContext.init(null,
                        trustManagerFactory.getTrustManagers(),
                        SecureRandom()
                )

                sslSocketFactory = sslContext.getSocketFactory()

                return sslSocketFactory as SSLSocketFactory?

            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }

    }
}