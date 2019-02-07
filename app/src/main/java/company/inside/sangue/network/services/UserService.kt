package company.inside.sangue.network.services

import company.inside.sangue.network.model.parameter.UserParameters
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by glevandowski on 01/07/18.
 */
 interface UserService {

    @GET("usuario")
    fun getUser(): Call<UserParameters>

    companion object {
        val BASE_URL: String = "https://c000ea56-0032-4a17-8755-d1f069bbfebe.mock.pstmn.io/";
    }



}