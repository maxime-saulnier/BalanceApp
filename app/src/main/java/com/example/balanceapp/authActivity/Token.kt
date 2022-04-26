package com.example.balanceapp.authActivity

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONException

class Token {
    companion object {
        @JvmStatic
        fun saveJWT(context: Context, saveToken: String) {
            val edit: SharedPreferences.Editor
            val prefs: SharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            edit = prefs.edit()
            edit.putString("token", saveToken)
            Log.i("Login", saveToken)
            edit.commit()
        }
        @JvmStatic
        fun getJWT(context: Context): String? {
            var prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val token: String? = prefs.getString("token", "")
            return token
        }
    }
}