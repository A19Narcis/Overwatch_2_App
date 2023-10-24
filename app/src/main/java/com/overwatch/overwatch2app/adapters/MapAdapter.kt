package com.overwatch.overwatch2app.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonElement
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.models.Maps.MapList
import java.util.Locale

class MapAdapter(private val context: Context, private val mapList: ArrayList<MapList>):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        return MapViewHolder(LayoutInflater.from(context).inflate(R.layout.map_item, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mapListL: MapList = mapList[position]
        if (holder is MapViewHolder) {
            binding(holder, mapListL)
        }
    }

    override fun getItemCount(): Int {
        return mapList.size
    }

    class MapViewHolder(binding: View): RecyclerView.ViewHolder(binding)

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun binding(holder: RecyclerView.ViewHolder, mapListL: MapList) {
        val mapIV: ImageView = holder.itemView.findViewById(R.id.mapIV)
        val mapTV: TextView = holder.itemView.findViewById(R.id.mapTV)
        val locatioTV: TextView = holder.itemView.findViewById(R.id.locationTV)
        val countrycodeTV: TextView = holder.itemView.findViewById(R.id.countrycodeTV)
        val modoMapaTV: TextView = holder.itemView.findViewById(R.id.modoMapaTV)
        val modoMapaIV: ImageView = holder.itemView.findViewById(R.id.modoMapaIV)
        val modoMapaTV2: TextView = holder.itemView.findViewById(R.id.modoMapaTV2)
        val modoMapaIV2: ImageView = holder.itemView.findViewById(R.id.modoMapaIV2)
        val qpIV: ImageView = holder.itemView.findViewById(R.id.qpIV)
        val compIV: ImageView = holder.itemView.findViewById(R.id.compIV)
        val arcadeIV: ImageView = holder.itemView.findViewById(R.id.arcadeIV)

        mapIV.let { Glide.with(context).load(mapListL.screenshot).into(it) }
        mapTV.text = mapListL.name

        // Propiedades para editar Location y Country Code automáticamente
        val posComa = mapListL.location.indexOf(',')
        val posPar = mapListL.location.indexOf('(')
        val patron = Regex("(?<=\\(near )\\w+")

        // Location
        locatioTV.text = if (posComa != -1) {
            mapListL.location.substring(0, posComa) //Selecciona el texto antes de la coma
        } else if (posPar != -1) {
            mapListL.location.replace(Regex("\\(.*?\\)"), "").trim() //Selecciona el texto antes del parentesis
        } else {
            mapListL.location
        }

        //Country Code
        countrycodeTV.text = if (posPar != -1) {
            patron.find(mapListL.location)?.value?.uppercase(Locale.ROOT)?.substring(0, 2) //Selecciona las 2 primeras letras del parentesis despues del (near...) en mayúsculas
        } else {
            mapListL.country_code
        }

        val listM = mapListL.gamemodes.toList()

        setModesToMap(listM, modoMapaTV, modoMapaIV, modoMapaTV2, modoMapaIV2)

        qpIV.setImageResource(R.drawable.assault_icon)
        compIV.setImageResource(R.drawable.assault_icon)
        arcadeIV.setImageResource(R.drawable.assault_icon)

        // Ajusta el texto automaticamente dependiendo del tamaño del textView
        val min1 = 10
        val max1 = 38
        val step1 = 2

        mapTV.setAutoSizeTextTypeUniformWithConfiguration(min1, max1, step1, TypedValue.COMPLEX_UNIT_SP)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setModesToMap(gamemodes: List<JsonElement>, modoMapaTV: TextView, modoMapaIV: ImageView, modoMapaTV2: TextView, modoMapaIV2: ImageView) {
        // Ajusta el texto automaticamente dependiendo del tamaño del textView
        val min1 = 14
        val max1 = 20
        val step1 = 2

        if (gamemodes.size == 2) {
            modoMapaTV2.visibility = View.VISIBLE
            modoMapaIV2.visibility = View.VISIBLE

            val gameString1 = gamemodes.get(0).toString()
            val modeClean1 = gameString1.replace("\"", "")
            val mode1 = modeClean1
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

            modoMapaTV.text = mode1.replace("-", " ")
            setIconsMode(modeClean1, modoMapaIV)

            val gameString2 = gamemodes.get(1).toString()
            val modeClean2 = gameString2.replace("\"", "")
            val mode2 = modeClean2
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

            modoMapaTV2.text = mode2.replace("-", " ")
            setIconsMode(modeClean2, modoMapaIV2)

            modoMapaTV.setAutoSizeTextTypeUniformWithConfiguration(min1, max1, step1, TypedValue.COMPLEX_UNIT_SP)
            modoMapaTV2.setAutoSizeTextTypeUniformWithConfiguration(min1, max1, step1, TypedValue.COMPLEX_UNIT_SP)
        }
        else {
            modoMapaTV2.visibility = View.GONE
            modoMapaIV2.visibility = View.GONE

            val gameString1 = gamemodes.get(0).toString()
            val modeClean1 = gameString1.replace("\"", "")
            val mode1 = modeClean1
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

            modoMapaTV.text = mode1.replace("-", " ")
            setIconsMode(modeClean1, modoMapaIV)

            modoMapaTV.setAutoSizeTextTypeUniformWithConfiguration(min1, max1, step1, TypedValue.COMPLEX_UNIT_SP)
        }
    }

    private fun setIconsMode(modeClean: String, modoMapaIV: ImageView) {
        when {
            modeClean.contains("assault") -> {
                modoMapaIV.setImageResource(R.drawable.assault_icon)
            }
            modeClean.contains("capture-the-flag") -> {
                modoMapaIV.setImageResource(R.drawable.capture_the_flag_icon)
            }
            modeClean.contains("control") -> {
                modoMapaIV.setImageResource(R.drawable.control_icon)
            }
            modeClean.contains("team-deathmatch") -> {
                modoMapaIV.setImageResource(R.drawable.team_deathmatch_icon)
            }
            modeClean.contains("deathmatch") -> {
                modoMapaIV.setImageResource(R.drawable.deathmatch_icon)
            }
            modeClean.contains("elimination") -> {
                modoMapaIV.setImageResource(R.drawable.elimination_icon)
            }
            modeClean.contains("escort") -> {
                modoMapaIV.setImageResource(R.drawable.escort_icon)
            }
            modeClean.contains("flashpoint") -> {
                modoMapaIV.setImageResource(R.drawable.flashpoint_icon)
            }
            modeClean.contains("hybrid") -> {
                modoMapaIV.setImageResource(R.drawable.hybrid_icon)
            }
            modeClean.contains("push") -> {
                modoMapaIV.setImageResource(R.drawable.push_icon)
            }
        }
    }
}
