package com.ahmed.protectapikey

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ahmed.protectapikey.ui.theme.ProtectApiKeyTheme
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtectApiKeyTheme {

                Log.d("JSON_TAG", BuildConfig.JSON_URL)
//
//                val encrypted = EncryptUtil.encrypt(BuildConfig.JSON_URL)
//                Log.d("JSON_TAG", encrypted!!)

                val decrypted = EncryptUtil.decrypt(BuildConfig.JSON_URL)
//                Log.d("JSON_TAG", decrypted!!)

                callServer(decrypted!!)

            }
        }
    }

   private fun callServer(url: String) {
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->

                Log.d("JSON_TAG", response.getString("api_key"))

            },
            { error ->

            }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }

}


