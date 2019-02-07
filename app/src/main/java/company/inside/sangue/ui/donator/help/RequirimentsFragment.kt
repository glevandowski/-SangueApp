package company.inside.sangue.ui.donator.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import company.inside.sangue.R
import company.inside.sangue.ui.core.BaseFragment
import company.inside.sangue.util.OpenManageClass

class RequirimentsFragment : BaseFragment() {

    lateinit var textRequiriments:TextView
    lateinit var btnContinue:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_requiriments, container, false)
        this.findViews(view)
        this.finRequiriments()
        this.onClickActions()
        return view
    }

    fun findViews(view:View) {
        textRequiriments = view.findViewById(R.id.text_view_requiriments)
        btnContinue = view.findViewById(R.id.btn_continue_requiriments)
    }

    fun finRequiriments(){
        //TODO Aguardando backend para atualizar requisitos
    }

    fun onClickActions() {
        btnContinue.setOnClickListener {
            OpenManageClass.openFragment(CongratulationsFragment(),requireActivity().supportFragmentManager,false)
        }
    }
}
