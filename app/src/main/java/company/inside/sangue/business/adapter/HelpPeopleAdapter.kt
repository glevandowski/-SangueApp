package company.inside.sangue.business.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.inside.sangue.R
import company.inside.sangue.business.adapter.holder.HelpUserHolder
import company.inside.sangue.network.model.HelpPeople
import kotlin.collections.ArrayList

class HelpPeopleAdapter(context: Context, coinArrayList:ArrayList<HelpPeople>): RecyclerView.Adapter<HelpUserHolder>(){


    var peopleList:ArrayList<HelpPeople>
    var context: Context

    init {
        this.peopleList = coinArrayList
        this.context = context
    }

    override fun getItemCount(): Int {
        return peopleList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpUserHolder {
       val view:View = LayoutInflater.from(context).inflate(R.layout.card_people_layout, parent, false);
       val holder = HelpUserHolder(view);
       return holder;
    }

    override fun onBindViewHolder(holder: HelpUserHolder, position: Int) {
        setText(holder,peopleList.get(position))

        holder.itemView.tag = position
        holder.itemView.setOnClickListener {
//            "ola ${peopleList.get(position).name}".toast(context)
        }
    }


    fun setText(holder: HelpUserHolder, helpPeople: HelpPeople){
            holder.name.setText(helpPeople.name)
            holder.description.setText(helpPeople.blood)
    }
}

