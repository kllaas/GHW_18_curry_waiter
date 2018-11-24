package business.curry.thepiekie.space.business.data.local

import android.content.Context
import android.preference.PreferenceManager
import business.curry.thepiekie.space.business.data.model.LoginResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class PrefsDataStore @Inject constructor(val gson: Gson) {

    companion object {
        const val LOGIN_DATA_KEY = "LOGIN_DATA_KEY"
    }

    fun saveLoginResponse(response: LoginResponse, context: Context) {
        putString(context, LOGIN_DATA_KEY, gson.toJson(response))
    }

    fun getLoginResponse(context: Context): LoginResponse {
        val type = object : TypeToken<LoginResponse>() {}.type

        return gson.fromJson(getString(context, LOGIN_DATA_KEY, ""), type)
    }

    @Throws(ClassCastException::class)
    fun getString(context: Context, preferenceKey: String, defaultValue: String): String? {

        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        return sp.getString(preferenceKey, defaultValue)
    }

    fun putString(context: Context, preferenceKey: String, value: String?): Boolean {

        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val editorSp = sp.edit()

        if (value == null) {
            editorSp.remove(preferenceKey)
        } else {
            editorSp.putString(preferenceKey, value)
        }

        return editorSp.commit()
    }

    fun loggedIn(context: Context): Boolean {
        return !getString(context, LOGIN_DATA_KEY, "").equals("")
    }

    fun getAccessToken(context: Context): String? {
        return getLoginResponse(context).accessToken
    }

}
