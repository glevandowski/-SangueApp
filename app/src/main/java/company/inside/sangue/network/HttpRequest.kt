package company.inside.sangue.network

import android.app.Activity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import android.util.Log

class HttpRequest {
    companion object {

        fun interceptorAPI(activity:Activity) : OkHttpClient {
            var failMessage:String

            val okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                        val request = chain.request()
                        val response = chain.proceed(request)

                when (response.code()) {
                    400-> {
                        failMessage = "Pedido incorreto"
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                    401 -> {
                        failMessage = "Acesso não autorizado no servidor"
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                    403 -> {
                        failMessage = "Acesso negado"
                        Log.d("HttpRequest", failMessage)
                        return@Interceptor response
                    }
                    404 -> {
                        failMessage = "Não foi possivel realizar acesso ao servidor"
                        Log.d("HttpRequest", failMessage)
                        return@Interceptor response
                    }
                    429 -> {
                        failMessage = "Requisições acima do limite"
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                    500 -> {
                        failMessage = "Erro interno no servidor"
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                }
                response
                    }).build()

             return okHttpClient;
        }
    }
}
