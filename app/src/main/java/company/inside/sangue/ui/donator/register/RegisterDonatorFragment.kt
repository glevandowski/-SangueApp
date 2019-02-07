package company.inside.sangue.ui.donator.register

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import company.inside.sangue.R
import company.inside.sangue.business.model.UserFacebook
import company.inside.sangue.ui.core.BaseFragment
import company.inside.sangue.ui.donator.register.listener.RegisterBusinessListener
import company.inside.sangue.ui.donator.register.presenter.RegisterPresenter

open class RegisterDonatorFragment : BaseFragment(), RegisterBusinessListener {

    val FLAG_AUTH_GOOGLE = 39

    lateinit var mGoogleApiClient:GoogleApiClient
    lateinit var btnAuthFacebook:LoginButton
    lateinit var btnAuthGoogle:SignInButton
    lateinit var btnForms:Button
    lateinit var btnRegisterForms:Button
    lateinit var registerPresenter: RegisterPresenter

    lateinit var permissionFacebook:Array<String>

    lateinit var bottomSheetBehavior: FrameLayout
    lateinit var viewForms:LinearLayout
    lateinit var editTextEmail: TextInputEditText
    lateinit var editTextName: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view = inflater.inflate(R.layout.fragment_register_donator, container, false)
      this.findViews(view)
      this.setupGoogle()
      this.setupFacebook()
      this.onClickActions()
      return view
    }

    override fun onPause() {
        super.onPause()
        activity?.let { mGoogleApiClient.stopAutoManage(it) }
        mGoogleApiClient.disconnect();
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode,data)
        registerPresenter.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun findViews(view: View) {
        btnAuthFacebook = view.findViewById(R.id.btn_auth_facebook)
        btnAuthGoogle = view.findViewById(R.id.btn_auth_google)
        btnAuthGoogle.setSize(SignInButton.SIZE_WIDE)
        btnForms = view.findViewById(R.id.btn_forms)

        bottomSheetBehavior = view.findViewById(R.id.bottom_sheet)
        viewForms = view.findViewById(R.id.forms_register_donator)
        btnRegisterForms = view.findViewById(R.id.btn_register_forms)
        editTextEmail = view.findViewById(R.id.edit_text_email_donator)
        editTextName = view.findViewById(R.id.edit_text_name_donator)

        permissionFacebook = resources.getStringArray(R.array.permissions_facebook)
        registerPresenter = RegisterPresenter().newInstance(this)
    }

    fun onClickActions() {
        btnForms.setOnClickListener {
            registerPresenter.setBottomSheet(bottomSheetBehavior,editTextName)
        }

        btnAuthFacebook.setOnClickListener {
            btnAuthFacebook.registerCallback(callbackManager, registerPresenter.requestAuthFacebook())
        }

        btnAuthGoogle.setOnClickListener {
            startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient), FLAG_AUTH_GOOGLE);
        }

        btnRegisterForms.setOnClickListener {
            registerPresenter.openFragment(requireActivity(), "454", editTextName.text.toString(),
                                            editTextEmail.text.toString(), true)
        }
    }

    //region Auth Google
    fun setupGoogle(){
        //Permissions
        val options =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().build()

         mGoogleApiClient =  GoogleApiClient.Builder(requireContext())
                .enableAutoManage(requireActivity(), GoogleApiClient.OnConnectionFailedListener {  })
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build()
    }
    //endregion

    //region Auth Facebook

    fun setupFacebook() {
        //Permissions
        for (i in 0 until permissionFacebook.size)
        btnAuthFacebook.setReadPermissions(permissionFacebook.get(i))

        btnAuthFacebook.setFragment(this)
    }
    //endregion

    override fun responseFacebook(token: String, userFacebook: UserFacebook) {
        editTextEmail.setText(userFacebook.getEmail())
        editTextName.setText(userFacebook.getFullName())

        registerPresenter.openFragment(requireActivity(),token,userFacebook.getFullName(),userFacebook.getEmail(),false)
    }

    override fun responseGoogle(googleSignInAccount: GoogleSignInAccount?) {
        editTextEmail.setText(googleSignInAccount?.email)
        editTextName.setText(googleSignInAccount?.displayName)

        registerPresenter.openFragment(requireActivity(),googleSignInAccount?.id.toString(),googleSignInAccount?.displayName.toString(),
                googleSignInAccount?.email.toString(),false)
    }

    override fun stateChangedRegister(bottomSheet: View?, newState: Int?) {}

    override fun onSlideRegister(bottomSheet: View?, slideOffset: Float?) {}

    override fun onFail(message: String) {
       message.toast()
    }

}






