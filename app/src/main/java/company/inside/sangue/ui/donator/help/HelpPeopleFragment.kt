package company.inside.sangue.ui.donator.help

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import company.inside.sangue.R
import company.inside.sangue.business.adapter.HelpPeopleAdapter
import company.inside.sangue.network.model.HelpPeople
import company.inside.sangue.network.model.parameter.UserParameters
import company.inside.sangue.ui.core.BaseFragment
import company.inside.sangue.util.OpenManageClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HelpPeopleFragment : BaseFragment() {

    lateinit var reciclerView: RecyclerView
    lateinit var adapterCoin: HelpPeopleAdapter
    lateinit var btnHelp:Button
    lateinit var peopleArrayList:ArrayList<HelpPeople>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help_people, container, false)
        this.findViews(view)
        this.findPeople()
        this.setupRecyclerViewLayout()
        this.onClickActions()
        return view
    }

    fun findViews(view: View) {
        reciclerView = RecyclerView(view.context)
        reciclerView = view.findViewById(R.id.recicler_view_people)
        btnHelp = view.findViewById(R.id.btn_help)
    }

    fun setupAdapter() {
        adapterCoin = HelpPeopleAdapter(requireContext(), peopleArrayList)
        reciclerView.setAdapter(adapterCoin)
        adapterCoin.notifyDataSetChanged()
    }

    fun setupRecyclerViewLayout() {
        val layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        reciclerView.setHasFixedSize(true);
        reciclerView.setLayoutManager(layoutManager);
    }


    fun findPeople() {
        peopleArrayList = ArrayList()
        service.getUser().enqueue(object: Callback<UserParameters> {

            override fun onResponse(call: Call<UserParameters>, response: Response<UserParameters>) {
                if(!response.isSuccessful) return

                for (i in 0 until response.body()?.data?.size!!)
                    peopleArrayList.add(response.body()?.data?.get(i)!!)
                setupAdapter()
            }

            override fun onFailure(call: Call<UserParameters>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun onClickActions() {
        btnHelp.setOnClickListener {
            OpenManageClass.openFragment(RequirimentsFragment(), requireActivity().supportFragmentManager,false)
        }
    }
}
