package company.inside.sangue.ui.donator.register.presenter

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.FrameLayout
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import company.inside.sangue.R
import company.inside.sangue.business.model.UserFacebook
import company.inside.sangue.ui.donator.register.BloodOptionsFragment
import company.inside.sangue.ui.donator.register.RegisterDonatorFragment
import company.inside.sangue.ui.donator.register.listener.RegisterBusinessListener
import company.inside.sangue.util.ManageKeyboard
import company.inside.sangue.util.OpenManageClass

class RegisterPresenter {

    lateinit var registerFragment: RegisterDonatorFragment
    lateinit var registerBusinessListener: RegisterBusinessListener
    lateinit var ManageKeyboard:ManageKeyboard

    fun newInstance(registerFragment: RegisterDonatorFragment): RegisterPresenter {
        val registerPresenter = RegisterPresenter()
        registerPresenter.registerFragment = registerFragment
        registerPresenter.registerBusinessListener = registerFragment
        registerPresenter.ManageKeyboard = ManageKeyboard(activity = registerFragment.requireActivity())
        return registerPresenter
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 39) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                registerBusinessListener.responseGoogle(result.signInAccount)
            } else {
                registerBusinessListener.onFail("A sua autenticação com a google não foi bem sucedida")
            }
        }
    }

    fun requestAuthFacebook() = object: FacebookCallback<LoginResult> {
        var fieldsFacebook = registerFragment.resources.getStringArray(R.array.fields_facebook)
        override fun onSuccess(loginResult: LoginResult) {

            val request = GraphRequest.newMeRequest(loginResult.accessToken) {
                requestValues, response ->

                registerBusinessListener.responseFacebook(response.request.accessToken.token, UserFacebook(response.jsonObject))
            }

            val parameters = Bundle()
            var values:String? = fieldsFacebook.get(0)

           for(i in 1 until fieldsFacebook.size)
               values = "$values,${fieldsFacebook.get(i)}"

            parameters.putString("fields", values)
            request.parameters = parameters
            request.executeAsync()
        }

        override fun onCancel() {registerBusinessListener.onFail("Autenticação cancelada")}

        override fun onError(error: FacebookException) {registerBusinessListener.onFail("Ocorreu algum erro ao realizar a autenticação")}
    }

    fun setBottomSheet(llBottomSheet: FrameLayout?, view: View? = null) {
        llBottomSheet?.visibility = View.VISIBLE
        val bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet)

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        bottomSheetBehavior.setHideable(true)

        ManageKeyboard.showKeyBoard()
        view?.requestFocus()

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                registerBusinessListener.stateChangedRegister(bottomSheet, newState)

                if(newState == BottomSheetBehavior.STATE_HIDDEN) {
                    llBottomSheet?.visibility = View.GONE
                    bottomSheet.clearFocus()
                    ManageKeyboard.hideKeyBoard()
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                registerBusinessListener.onSlideRegister(bottomSheet,slideOffset)
            }
        })
    }

    fun openFragment(activity: FragmentActivity, token: String, name:String, email:String, noAddBackStack:Boolean) {
        if(name.isEmpty() && email.isEmpty())
            registerBusinessListener.onFail("Ops.. houve problemas ao identificar seu nome e email")
        else if (name.isEmpty())
            registerBusinessListener.onFail("Ops...Não identificamos seu nome")
        else if (email.isEmpty())
            registerBusinessListener.onFail("Ops...Não identificamos seu email")
        else {
            val bundle = Bundle()
            bundle.putString("email", email)
            bundle.putString("name", name)
            bundle.putString("token",token)

            OpenManageClass.openFragment(BloodOptionsFragment(), activity.supportFragmentManager,bundle, noAddBackStack)
        }
    }

}