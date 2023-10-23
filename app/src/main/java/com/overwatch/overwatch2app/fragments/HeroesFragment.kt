package com.overwatch.overwatch2app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.adapters.HeroesAdapter
import com.overwatch.overwatch2app.api.ApiService
import com.overwatch.overwatch2app.models.Hero.HeroList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroesFragment : Fragment() {

    // Preparamos todas las variables
    private var root : View? = null

    private val baseURL = "https://overfast-api.tekrop.fr/"

    private var heroesTV: TextView? = null
    private var selectionRoleGroup: RadioGroup? = null
    private var allRButton: RadioButton? = null
    private var tankRButton: RadioButton? = null
    private var damageRButton: RadioButton? = null
    private var supportRButton: RadioButton? = null
    private var recyclerViewHeroes: RecyclerView? = null

    private var allHeroList: ArrayList<HeroList>? = null
    private var tankHeroList: ArrayList<HeroList>? = null
    private var damageHeroList: ArrayList<HeroList>? = null
    private var supportHeroList: ArrayList<HeroList>? = null

    private var heroAdapter: HeroesAdapter? = null

    // Iniciamos la llamada GET para los heroes
    init {
        getHeroesInfo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_heroes, container, false)

        heroesTV = root?.findViewById(R.id.heroesTextView)

        selectionRoleGroup = root?.findViewById(R.id.selectionRGroup)

        allRButton = root?.findViewById(R.id.allHeroesRButton)
        tankRButton = root?.findViewById(R.id.tankRButton)
        damageRButton = root?.findViewById(R.id.damageRButton)
        supportRButton = root?.findViewById(R.id.supportRButton)

        recyclerViewHeroes = root?.findViewById(R.id.hereosRecycleView)

        // Indicamos las columnas de nuestro RecycleView
        val layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewHeroes?.layoutManager = layoutManager

        //Marcamos por defecto el campo de "All Heroes"
        allRButton?.isChecked = true

        // Dependiendo de que filtro selecciones se actualiza el RecycleView
        selectionRoleGroup?.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.allHeroesRButton -> {
                    showAllHeroes()
                }
                R.id.tankRButton -> {
                    showTankHeroes()
                }
                R.id.damageRButton -> {
                    showDamageHeroes()
                }
                R.id.supportRButton -> {
                    showSupportHeroes()
                }
            }
        }
        return root
    }

    // Llamada para obtener todos los hereos
    private fun getHeroesInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.getInfoHeroes().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful){
                    val jsonArray = response.body()
                    val heroesArray = jsonArray?.asJsonArray

                    if (heroesArray != null) {

                        //Inicializamos todas las array para prepararlas para los filtros
                        allHeroList = ArrayList()
                        tankHeroList = ArrayList()
                        damageHeroList = ArrayList()
                        supportHeroList = ArrayList()

                        for (hero in heroesArray) {
                            val jsonObject = hero.asJsonObject

                            val key = jsonObject.get("key").asString
                            val name = jsonObject.get("name").asString
                            val portrait = jsonObject.get("portrait").asString
                            val role = jsonObject.get("role").asString

                            val heroListInfo = HeroList(key, name, portrait, role)

                            //Añadimos lo hereos en las 4 listas diferentes dependiendo del tipo de filtro
                            allHeroList?.add(heroListInfo)
                            when (heroListInfo.role) {
                                "tank" -> {
                                    tankHeroList!!.add(heroListInfo)
                                }
                                "damage" -> {
                                    damageHeroList!!.add(heroListInfo)
                                }
                                "support" -> {
                                    supportHeroList!!.add(heroListInfo)
                                }
                            }
                        }
                        // Ejecutamos el adapter para que cargue el contenido al entrar en el fragment
                        runAdapter(allHeroList!!)
                    }
                }
            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.i("CHECK_RESPONSE_FAIL", t.toString())
            }
        })
    }

    // Metodo que se ejecuta cada vez que se quiere actualizar el RecycleView
    private fun runAdapter(heroList: ArrayList<HeroList>?) {
        heroAdapter = HeroesAdapter(requireContext(), heroList!!)
        recyclerViewHeroes!!.adapter = heroAdapter
    }

    // Metodos para aplicar los filtros
    private fun showAllHeroes() {
        runAdapter(allHeroList)
    }
    private fun showTankHeroes() {
        runAdapter(tankHeroList)
    }
    private fun showDamageHeroes() {
        runAdapter(damageHeroList)
    }
    private fun showSupportHeroes() {
        runAdapter(supportHeroList)
    }
}