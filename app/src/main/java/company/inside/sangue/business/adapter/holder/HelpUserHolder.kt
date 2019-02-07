package company.inside.sangue.business.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import company.inside.sangue.R

open class HelpUserHolder(holder: View): RecyclerView.ViewHolder(holder) {
    var name:TextView = holder.findViewById( R.id.text_person)
    var description:TextView = holder.findViewById(R.id.text_person_description)
    var imagePerson:ImageView = holder.findViewById(R.id.image_user)
}