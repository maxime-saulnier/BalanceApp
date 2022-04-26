package com.example.balanceapp.authActivity

import android.content.Context
import android.util.Log
import android.view.View
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.balanceapp.dto.UserLoginDTO
import com.example.balanceapp.dto.UserRegisterDTO
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class Authentication {
    companion object {
        @JvmStatic
        fun register(context: Context, userRegister: UserRegisterDTO)
        {
            val jsonLists = JSONObject()
            jsonLists.put("firstName", userRegister.getFirstName())
            jsonLists.put("lastName", userRegister.getLastName())
            jsonLists.put("email", userRegister.getEmail())
            jsonLists.put("password", userRegister.getPassword())
            val url = "https://pokeapi.co/api/v2/pokemon/ditto"
            val que = Volley.newRequestQueue(context)
            val jsonObjectRequest = JsonObjectRequest(1, url, jsonLists,
                { response ->
                    Log.d("MonActivity","Response: %s".format(response.toString()))
                },
                { Log.d("MonActivity","That didn't work!")}
            )
            que.add(jsonObjectRequest)
        }

        @JvmStatic
        fun login(context: Context, view: View, userlogin: UserLoginDTO)
        {
            val url = "https://pokeapi.co/api/v2/pokemon/ditto"
            val que = Volley.newRequestQueue(context)
            val jsonLists = JSONObject()
            jsonLists.put("email", userlogin.getemail())
            jsonLists.put("password", userlogin.getpassword())
            val jsonObjectRequest = JsonObjectRequest(1, url, jsonLists,
                { response ->
                    if (response.has("token"))
                    {
                        Token.saveJWT(context, response.getString("token"))
                        Log.d("MonActivity",response.getString("token"))
                    }
                    else
                    {
                        Log.d("MonActivity","Error email or password")
                        Snackbar.make(view, "email ou mot de passe incorrect", Snackbar.LENGTH_LONG).show()
                    }
                },
                {
                    Log.d("MonActivity","That didn't work!")
                    Snackbar.make(view, "problème connection au serveur", Snackbar.LENGTH_LONG).show()
                }
            )
            que.add(jsonObjectRequest)
        }
    }
}