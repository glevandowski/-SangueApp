package company.inside.sangue.ui.receptor.register


import android.os.Bundle
import android.app.Fragment
import android.os.Environment
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import com.squareup.picasso.Picasso

import company.inside.sangue.R
import company.inside.sangue.ui.core.BaseFragment
import java.io.File

class RegisterReceptorFragment : BaseFragment() {

    lateinit var fabAddPicture:FloatingActionButton
    lateinit var spinnerHemocentros:Spinner
    lateinit var textName:TextInputEditText
    lateinit var imageReceptor:ImageView
    lateinit var btnContinue:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register_receptor, container, false)
        this.findViews(view)
        this.onClickActions()
        return view
    }

    fun findViews(view: View) {
        fabAddPicture = view.findViewById(R.id.fab_add_picture)
        spinnerHemocentros = view.findViewById(R.id.spinner_hospital)
        textName = view.findViewById(R.id.text_name_receptor)
        imageReceptor = view.findViewById(R.id.image_receptor)
        btnContinue = view.findViewById(R.id.btn_continue_register_receptor)
    }

    fun onClickActions() {
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)


        fabAddPicture.setOnClickListener {
            Picasso.get().load(file).noPlaceholder().centerCrop().fit().into(imageReceptor)
        }
    }
}
