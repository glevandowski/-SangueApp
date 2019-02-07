package company.inside.sangue.ui.donator.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import company.inside.sangue.R
import company.inside.sangue.ui.core.BaseFragment


class CongratulationsFragment : BaseFragment() {

    lateinit var call:FloatingActionButton
    lateinit var btnMaps:Button
    lateinit var btnShare:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_congratulations, container, false)
        this.findViews(view)
        this.onClickActions()
        return view
    }

    fun findViews(view: View) {
        call = view.findViewById(R.id.float_call)
        btnMaps = view.findViewById(R.id.btn_maps_congrulations)
        btnShare = view.findViewById(R.id.btn_share_congrulations)
    }

    fun onClickActions() {
        call.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "51999115308")))
        }

        btnMaps.setOnClickListener {
            var mapUri = Uri.parse("geo:0,0?q="+"latitude"+","+"longitude"+"("+"title"+")");

            startActivity(Intent(Intent.ACTION_VIEW, mapUri).setPackage("com.google.android.apps.maps"))
        }

        btnShare.setOnClickListener {
            share()
        }
    }

    fun share() = startActivity(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT,
            "*+SANGUE* Compartilhamento enviado pelo app"))
}
