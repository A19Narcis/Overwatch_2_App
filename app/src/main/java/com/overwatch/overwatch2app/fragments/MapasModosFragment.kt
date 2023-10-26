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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.adapters.MapAdapter
import com.overwatch.overwatch2app.adapters.ModeAdapter
import com.overwatch.overwatch2app.api.ApiService
import com.overwatch.overwatch2app.models.Map.MapList
import com.overwatch.overwatch2app.models.Mode.ModeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapasModosFragment : Fragment() {
    private var root : View? = null

    private val baseURL = "https://overfast-api.tekrop.fr/"

    private var mapas: TextView? = null
    private var selectionMapModeGroup: RadioGroup? = null
    private var mapasRButton: RadioButton? = null
    private var modosRButton: RadioButton? = null
    private var recyclerViewMapas: RecyclerView? = null
    private var recyclerViewModos: RecyclerView? = null

    private var allMapsList: ArrayList<MapList>? = null
    private var allModesList: ArrayList<ModeList>? = null

    private var mapAdapter: MapAdapter? = null
    private var modeAdapter: ModeAdapter? = null

    init {
        getMapsInfo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_mapasmodos, container, false)

        mapas = root?.findViewById(R.id.mapasTextView)

        selectionMapModeGroup = root?.findViewById(R.id.selectionRGroup)
        mapasRButton = root?.findViewById(R.id.mapasRButton)
        modosRButton = root?.findViewById(R.id.modosRButton)

        recyclerViewMapas = root?.findViewById(R.id.mapasRecycleView)
        recyclerViewModos = root?.findViewById(R.id.modosRecycleView)

        val layoutManagerMaps = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewMapas?.layoutManager = layoutManagerMaps
        val layoutManagerModes = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewModos?.layoutManager = layoutManagerModes
        mapasRButton?.isChecked = true

        selectionMapModeGroup?.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.mapasRButton -> {
                    recyclerViewModos?.visibility = View.GONE
                    recyclerViewMapas?.visibility = View.VISIBLE

                }
                R.id.modosRButton -> {
                    recyclerViewMapas?.visibility = View.GONE
                    recyclerViewModos?.visibility = View.VISIBLE
                }
            }
        }
        return root
    }

    private fun getMapsInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.getInfoMaps().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful){
                    val jsonArray = response.body()
                    val mapsArray = jsonArray?.asJsonArray

                    if (mapsArray != null) {

                        allMapsList = ArrayList()

                        for (map in mapsArray) {
                            val jsonObject = map.asJsonObject

                            val name = jsonObject.get("name").asString
                            val screenshot = jsonObject.get("screenshot").asString
                            val gamemodes: JsonArray = jsonObject.get("gamemodes").asJsonArray
                            val location = jsonObject.get("location").asString
                            val countryCode = if (jsonObject.get("country_code").isJsonNull) "OW" else jsonObject.get("country_code").asString
                            var qp = false
                            var comp = false
                            var arcade = false

                            for (gm in gamemodes) {
                                if (gm.equals("assault")||gm.equals("capture-the-flag")||gm.equals("deathmatch")||gm.equals("elimination")) {
                                    arcade = true
                                }
                                else {
                                    qp = true
                                    comp = true
                                }
                            }
                            val mapListInfo = MapList(name, screenshot, gamemodes, location, countryCode, qp, comp, arcade)
                            allMapsList?.add(mapListInfo)
                        }
                        getModesInfo()
                        // Ejecutamos el adapter para que cargue el contenido al entrar en el fragment
                        runAdapterMaps(allMapsList!!)
                    }
                }
            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.e("Failure Maps", t.toString() )
            }
        })
    }

    private fun getModesInfo() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        service.getInfoModes().enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful){
                    val jsonArray = response.body()
                    val modesArray = jsonArray?.asJsonArray

                    if (modesArray != null) {

                        allModesList = ArrayList()

                        for (mode in modesArray) {
                            val jsonObject = mode.asJsonObject

                            val key = jsonObject.get("key").asString
                            val name = jsonObject.get("name").asString
                            val icon = jsonObject.get("icon").asString
                            val description = jsonObject.get("description").asString
                            val screenshot = jsonObject.get("screenshot").asString

                            val modeListInfo = ModeList(key, name, icon, description, screenshot)
                            allModesList?.add(modeListInfo)
                        }
                        // Ejecutamos el adapter para que cargue el contenido al entrar en el fragment
                        runAdapterModes(allModesList!!, allMapsList!!)
                    }
                }
            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.e("Failure Modes", t.toString() )
            }
        })
    }

    private fun runAdapterMaps(mapList: ArrayList<MapList>?) {
        mapAdapter = MapAdapter(requireContext(), mapList!!)
        recyclerViewMapas!!.adapter = mapAdapter
    }

    private fun runAdapterModes(modeList: ArrayList<ModeList>?, mapList: ArrayList<MapList>?) {
        modeAdapter = ModeAdapter(requireContext(), modeList!!, mapList!!)
        recyclerViewModos!!.adapter = modeAdapter
    }

}