package company.inside.sangue.ui.donator.register.listener

import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import company.inside.sangue.business.model.UserFacebook

interface RegisterBusinessListener {

      fun responseFacebook(token:String, userFacebook: UserFacebook)

      fun responseGoogle(googleSignInAccount: GoogleSignInAccount?)

      fun stateChangedRegister(bottomSheet: View? = null, newState: Int? = null)

      fun onSlideRegister(bottomSheet: View?= null, slideOffset: Float? = null)

      fun onFail(message:String)
}