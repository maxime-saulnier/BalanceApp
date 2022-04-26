package com.example.balanceapp.authActivity

import android.R.id
import android.content.Context
import android.util.Base64
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.balanceapp.dto.UserRegisterDTO
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class SDKregister {


    companion object {

        @JvmStatic
        val client_id     = "ae84f69696062b18413a54dca39cb8ea2cf8b7bb62939eaa0f74fa28b916d334"
        val client_secret = "a3584d488290868afc67c0369dd29710af4433a848d29e22d503751f308c01c5"
        fun register(context: Context, userRegister: UserRegisterDTO)
        {
            creatfff()
            val jsonLists = JSONObject()
            val nonce = getNonce(context)
            val signature = nonce?.let { getSignature(it) }
            jsonLists.put("action", "createuser")
            jsonLists.put("client_id", client_id)
            jsonLists.put("nonce", nonce)
            jsonLists.put("signature", signature)
            jsonLists.put("mailingpref", "0")
            jsonLists.put("birthdate", userRegister.getBirthdate())
            jsonLists.put("measures", "[{\"value\": "+userRegister.getTaille()+",\"unit\": -2,\"type\": 4},{\"value\": "+userRegister.getPoids()+",\"unit\": -2,\"type\": 1}]")
            jsonLists.put("gender", userRegister.getGender())
            jsonLists.put("preflang", "fr_FR")
            jsonLists.put("unit_pref", "{\"weight\":1,\"height\":6,\"distance\":6,\"temperature\":11}")
            jsonLists.put("timezone", "Europe/Paris")
            jsonLists.put("email", userRegister.getEmail())
            jsonLists.put("shortname", userRegister.getShortname())
            jsonLists.put("external_id", userRegister.getEmail())
            jsonLists.put("lastname", userRegister.getLastName())
            jsonLists.put("firstname", userRegister.getFirstName())
            Log.d("MonActivity", "jsonLists = $jsonLists")
            val url = "https://wbsapi.withings.net/v2/sdk"
            val que = Volley.newRequestQueue(context)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonLists,
                { response ->
                    Log.d("MonActivity","Response: %s".format(response.toString()))
                },
                { Log.d("MonActivity","That didn't work!")}
            )
            que.add(jsonObjectRequest)
        }


        fun getSignature(nonce : String): String {
            return SHA256("createuser" , client_id ,nonce)
        }
        fun SHA256(n1 : String , n2 : String ,n3 : String): String {
            val liter: ArrayList<String> = ArrayList()
            liter.add(n1)
            liter.add(n2)
            liter.add(n3)
            liter.sort()
            val data: String = liter[0]+","+liter[1]+","+liter[2]
            Log.d("MonActivity", "data = $data")
            val mac: Mac = Mac.getInstance("HmacSHA256")
            val secretKey = SecretKeySpec(client_secret.toByteArray(), "SHA256")
            mac.init(secretKey)
            return Base64.encodeToString(mac.doFinal(data.toByteArray()), 0)
        }
        fun getNonce(context: Context): String? {
            val jsonLists = JSONObject()
            jsonLists.put("action", "getnonce")
            jsonLists.put("client_id", client_id)
            val now = Date()
            val longTime = now.time / 1000
            val milliSeconds: String = longTime.toInt().toString()
            jsonLists.put("timestamp", milliSeconds)
            val signature : String = SHA256("getnonce" , client_id ,milliSeconds)

            jsonLists.put("signature", signature)
            Log.d("MonActivity", "signature = $signature")
            Log.d("MonActivity", "jsonLists = $jsonLists")
            var rep: String? = null
            val url =
                "https://wbsapi.withings.net/v2/signature"
            val que = Volley.newRequestQueue(context)
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonLists,
                { response ->
                    Log.d("MonActivity","Response: %s".format(response.toString()))
                    if (response.has("nonce"))
                        rep=response.getString("nonce")
                    else
                        rep = null

                },
                {
                    Log.d("MonActivity","That didn't work!")
                    rep = null
                }
            )
            que.add(jsonObjectRequest)
            return rep
        }
        fun creatfff(){
            val url = URL("https://wbsapi.withings.net/v2/signature")
            val conn = url.openConnection() as HttpURLConnection
            conn.doOutput = true
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            conn.setRequestProperty("Accept", "application/json")
            conn.requestMethod = "POST"
            //
            val now = Date()
            val longTime = now.time / 1000
            val milliSeconds: String = longTime.toInt().toString()
            //
            val jsonObj: JSONObject =
                JSONObject().put("action", "getnonce").put("client_id", client_id).put("timestamp", milliSeconds).put("signature", SHA256("getnonce" , client_id ,milliSeconds))

            val writer = OutputStreamWriter(conn.outputStream)
            writer.write(jsonObj.toString())
            writer.close()

        }
    }
}