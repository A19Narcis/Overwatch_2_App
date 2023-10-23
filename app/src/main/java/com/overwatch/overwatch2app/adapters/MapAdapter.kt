package com.overwatch.overwatch2app.adapters

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
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.models.Maps.MapList
import com.overwatch.overwatch2app.models.Modes.ModeList
import com.santalu.diagonalimageview.DiagonalImageView

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
        val posComa = mapListL.location.indexOf(',')
        locatioTV.text = if (posComa != -1) {
            mapListL.location.substring(0, posComa)
        } else {
            mapListL.location
        }
        countrycodeTV.text = mapListL.country_code
        modoMapaTV.text = mapListL.gamemodes.get(0).toString()
        //modoMapaTV2.text = mapListL.gamemodes.get(1).toString()
        modoMapaIV.setImageResource(R.drawable.assault_icon)
        //modoMapaIV2.setImageResource(R.drawable.assault_icon)

        qpIV.setImageResource(R.drawable.assault_icon)
        compIV.setImageResource(R.drawable.assault_icon)
        arcadeIV.setImageResource(R.drawable.assault_icon)


        // Ajusta el texto automaticamente dependiendo del tamaño del textView
        val min1 = 10
        val max1 = 36
        val step1 = 2

        mapTV.setAutoSizeTextTypeUniformWithConfiguration(
            min1,
            max1,
            step1,
            TypedValue.COMPLEX_UNIT_SP
        )
    }

}
