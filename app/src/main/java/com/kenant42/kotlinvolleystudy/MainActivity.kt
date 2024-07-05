package com.kenant42.kotlinvolleystudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUser("Hasan")
    }

    fun deleteUser() {
        val url = "http://kasimadalan.pe.hu/kisiler/delete_kisiler.php"

        val request = object : StringRequest(Method.POST, url, Response.Listener { response ->

        }, Response.ErrorListener { e ->
            e.printStackTrace()
        }) {
            override fun getParams(): MutableMap<String, String>? {

                val params = HashMap<String, String>()
                params["kisi_id"] = "17468"
                return params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(request)


    }

    fun saveUser() {
        val url = "http://kasimadalan.pe.hu/kisiler/insert_kisiler.php"

        val request = object : StringRequest(Method.POST, url, Response.Listener { response ->

        }, Response.ErrorListener { e ->
            e.printStackTrace()
        }) {
            override fun getParams(): MutableMap<String, String>? {
                val datas = HashMap<String, String>()
                datas["kisi_ad"] = "Kenan"
                datas["kisi_tel"] = "05455555566"

                return datas
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(request)
    }

    fun updateUser() {
        val url = "http://kasimadalan.pe.hu/kisiler/update_kisiler.php"

        val request = object : StringRequest(Method.POST, url, Response.Listener { response ->

        }, Response.ErrorListener { e ->
            e.printStackTrace()
        }) {
            override fun getParams(): MutableMap<String, String>? {
                val datas = HashMap<String, String>()
                datas["kisi_id"] = "17462"
                datas["kisi_ad"] = "Hasan"
                datas["kisi_tel"] = "YENITEL"

                return datas
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(request)
    }

    fun getAllUsers() {

        val url = "http://kasimadalan.pe.hu/kisiler/tum_kisiler.php"


        val request =  StringRequest(Request.Method.GET, url, Response.Listener { response ->

            try {
                val jsonObject = JSONObject(response)
                val usersList = jsonObject.getJSONArray("kisiler")
                for (i in 0 until usersList.length()) {
                    val user = usersList.getJSONObject(i)
                    val kisi_id = user.getInt("kisi_id")
                    val kisi_ad = user.getString("kisi_ad")
                    val kisi_tel = user.getString("kisi_tel")


                    Log.e("Kişi id",kisi_id.toString())
                    Log.e("Kişi ad",kisi_ad)
                    Log.e("Kişi tel",kisi_tel)
                    Log.e("********","*********")
                }
            } catch (e: Exception) {

            }

        }, Response.ErrorListener { e -> e.printStackTrace() })


        Volley.newRequestQueue(this@MainActivity).add(request)

    }

    fun getUser(kisiAd:String){
        val url = "http://kasimadalan.pe.hu/kisiler/tum_kisiler_arama.php"
        val request = object:StringRequest(Method.GET,url,Response.Listener { response->

            Log.e("Arama Cevap",response)

            try{
                val jsonObject = JSONObject(response)
                val kisilerListe = jsonObject.getJSONArray("kisiler")

                for(i in 0 until kisilerListe.length()){
                    val k = kisilerListe.getJSONObject(i)

                    val kisi_id = k.getInt("kisi_id")
                    val kisi_ad = k.getString("kisi_ad")
                    val kisi_tel = k.getString("kisi_tel")

                    Log.e("Kişi id",kisi_id.toString())
                    Log.e("Kişi ad",kisi_ad)
                    Log.e("Kişi tel",kisi_tel)
                    Log.e("********","*********")

                }


            }catch (e: JSONException){
                e.printStackTrace()
            }
        },Response.ErrorListener { e->
            e.printStackTrace()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val datas = HashMap<String,String>()
                datas["kisi_ad"] = kisiAd
                return datas
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(request)
    }

}