package company.inside.sangue.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import company.inside.sangue.data.preferences.SharedPreference
import company.inside.sangue.network.model.HelpPeople

class DonatorRepository (context: Context):Repository<HelpPeople> {

     var context:Context;
     var  gson:Gson;

    init {
        this.context = context;
        this.gson =  Gson()
    }

    override fun add(item:HelpPeople) {
        SharedPreference.getInstance().save(context, SharedPreference.KEY_USER_CADASTRO, gson.toJson(item));
    }

    @Override
    override fun update(item:HelpPeople) {
        val usuario = getUsuario();
        usuario.name= item.name;
        usuario.blood = item.blood;
        SharedPreference.getInstance().save(context, SharedPreference.KEY_USER_CADASTRO, gson.toJson(usuario));
    }

   override fun remove(item:HelpPeople) = SharedPreference.getInstance().clear(context);

    override fun findAll():List<HelpPeople> =
            gson.fromJson<List<HelpPeople>>(SharedPreference.getInstance().getValue(context, SharedPreference.KEY_USER_CADASTRO));


    fun getUsuario():HelpPeople =
            gson.fromJson<HelpPeople>(SharedPreference.getInstance().getValue(context, SharedPreference.KEY_USER_CADASTRO))


    fun isEmpty():Boolean =
            (gson.fromJson<HelpPeople>(SharedPreference.getInstance().getValue(context, SharedPreference.KEY_USER_CADASTRO)) == null)


    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}
