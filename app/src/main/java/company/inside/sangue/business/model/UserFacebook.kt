package company.inside.sangue.business.model

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class UserFacebook(jsonObject: JSONObject){

    var jsonObject:JSONObject

    init {
        this.jsonObject = jsonObject
    }

    fun getId() = jsonObject.findJsonObject("id")

    fun getEmail() = jsonObject.findJsonObject("email")

    fun getFullName() = " ${jsonObject.findJsonObject("first_name")} ${jsonObject.findJsonObject("last_name")}"

    fun getFirstName() = jsonObject.findJsonObject("first_name")

    fun getLastName() = jsonObject.findJsonObject("last_name")

    fun getGender() = jsonObject.findJsonObject("gender")

    fun getBirthDay() = jsonObject.findJsonObject("birthday")

    fun getAge(){
        var sla = Calendar.getInstance()
        sla.setTime(SimpleDateFormat("yyyy/mm/dd").parse(jsonObject.findJsonObject("birthday")))
    }

    fun JSONObject.findJsonObject(string: String?): String =  if(this.has(string)) this.getString(string) else ""

}