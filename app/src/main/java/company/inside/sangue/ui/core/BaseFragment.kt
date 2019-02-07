package company.inside.sangue.ui.core

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import company.inside.sangue.network.HttpRequest
import company.inside.sangue.network.services.UserService
import company.inside.sangue.util.ManageKeyboard
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseFragment:Fragment() {
    lateinit var callbackManager: CallbackManager
    lateinit var manageKeyboard:ManageKeyboard
    lateinit var service: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupSdkFacebook()
        this.createData()
        this.initInstances()
    }

    fun setupSdkFacebook() {
        FacebookSdk.sdkInitialize(context)
        AppEventsLogger.activateApp(context)
        callbackManager = CallbackManager.Factory.create()
    }
    fun initInstances() {
        manageKeyboard = ManageKeyboard(requireActivity())
    }

    fun String.toast() = Toast.makeText(context,this,Toast.LENGTH_LONG).show()

    fun JSONObject.findJsonObject(string: String?): String =  if(this.has(string)) this.getString(string) else ""

    fun createData() {
        val g: Gson = GsonBuilder().create()
        val retro: Retrofit = Retrofit.Builder().client(HttpRequest.interceptorAPI(this.requireActivity())).baseUrl(UserService.BASE_URL).addConverterFactory(GsonConverterFactory.create(g)).build()
        service = retro.create(UserService::class.java)
    }


}