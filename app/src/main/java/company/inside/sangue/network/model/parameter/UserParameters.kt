package company.inside.sangue.network.model.parameter

import com.google.gson.annotations.SerializedName
import company.inside.sangue.network.model.HelpPeople

class UserParameters(data:ArrayList<HelpPeople>) {

    @SerializedName("data")
    var data: ArrayList<HelpPeople> = data

}