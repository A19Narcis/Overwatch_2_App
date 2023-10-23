package com.overwatch.overwatch2app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerfilFragment : Fragment() {

    private var root : View? = null

    private var searchView : SearchView? = null

    private val baseURL = "https://overfast-api.tekrop.fr/"
    private var userImage : ImageView? = null
    private var userCard : ImageView? = null
    private var userName : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_perfil, container, false)

        userName = root?.findViewById(R.id.username)
        userImage = root?.findViewById(R.id.userImage)
        userCard = root?.findViewById(R.id.userNameCard)

        searchView = root?.findViewById(R.id.searchView)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    getUserInfo(query)
                } else {
                    Toast.makeText(requireContext(), "El campo de búsqueda está vacío", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Este método se llama cuando el texto de búsqueda cambia
                // newText contiene el nuevo texto de búsqueda
                return true
            }
        })




        return root
    }

    private fun getUserInfo(userId: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.getInfoUsers(userId).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful){
                    val jsonObject = response.body()

                    val summary = jsonObject?.getAsJsonObject("summary")
                    //val stats = jsonObject?.getAsJsonObject("stats")

                    val username = summary?.get("username")?.asString
                    val avatar = summary?.get("avatar")?.asString
                    val nameCard = summary?.get("namecard")?.asString

                    userName?.text = username
                    userImage?.let { Glide.with(requireContext()).load(avatar).into(it) }
                    userCard?.let { Glide.with(requireContext()).load(nameCard).into(it) }
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.i("CHECK_RESPONSE_FAIL", t.toString())
            }
        })
    }
}