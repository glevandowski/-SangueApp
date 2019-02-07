package company.inside.sangue.data.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreference {

    companion object {
        lateinit var  sharedPreference: SharedPreference;
        val PREFS_NAME = "SangueAppAndroid"
        val KEY_USER_CADASTRO = "KeyUserCadastro"

        fun  getInstance(): SharedPreference {
            if (sharedPreference == null) { sharedPreference = SharedPreference()
            }

            return sharedPreference;
        }
    }

    open fun save(context:Context, Key:String, text:String) {
        val settings:SharedPreferences
        val editor:SharedPreferences.Editor

        //settings = PreferenceManager.getDefaultSharedPreferences(mActivity);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Key, text); //3

        editor.commit(); //4
    }

    open fun getValue( context:Context , Key:String) :String{
        val settings:SharedPreferences
        var text = ""
        //  settings = PreferenceManager.getDefaultSharedPreferences(mActivity);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        text = settings.getString(Key, "")
        return text;
    }

    fun clear(context:Context) {
        val settings:SharedPreferences
        val editor:SharedPreferences.Editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(mActivity);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    fun removeValue(context:Context , value:String) {
        val settings:SharedPreferences;
        val editor:SharedPreferences.Editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(value);
        editor.commit();
    }
}
