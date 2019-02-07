package company.inside.sangue.ui.donator.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import company.inside.sangue.R
import company.inside.sangue.ui.core.BaseFragment
import company.inside.sangue.ui.donator.help.HelpPeopleFragment
import company.inside.sangue.util.OpenManageClass

class BloodOptionsFragment : BaseFragment() {


    lateinit var btnRegister:Button
    lateinit var listView: ListView
    lateinit var optionsBlood: Array<String>
    lateinit var name:String
    lateinit var email:String
    lateinit var token:String
    lateinit var blood:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blood_options, container, false)
        this.findViews(view)
        this.receivingBundle()
        this.setupAdapter()
        this.onClickActions()
        return view
    }

    fun findViews(view: View) {
        listView = view.findViewById(R.id.list_blood)
        btnRegister = view.findViewById(R.id.btn_register_donator)
        optionsBlood = resources.getStringArray(R.array.options_blood)
    }
    fun receivingBundle(){

        name = arguments?.getString("name","").toString()
        email = arguments?.getString("email","").toString()
        token = arguments?.getString("token","").toString()

    }

    fun onClickActions() {
        btnRegister.isEnabled = false

        listView.setOnItemClickListener { parent, view, position, id ->
            blood = optionsBlood.get(position)
            btnRegister.isEnabled = true
            btnRegister.visibility = View.VISIBLE
        }

        btnRegister.setOnClickListener {
            //TODO AGUARDANDO BACKEND PARA CADASTRAR

            //IF TUDO OK?
            OpenManageClass.openFragment(HelpPeopleFragment(), requireActivity().supportFragmentManager, false)
        }

    }

    fun setupAdapter() = listView.setAdapter(ArrayAdapter(requireContext(), R.layout.row_blood_option, optionsBlood))
}