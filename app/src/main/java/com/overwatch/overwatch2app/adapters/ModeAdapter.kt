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

private var assaultList: ArrayList<String>? = null
private var captureList: ArrayList<String>? = null
private var controlList: ArrayList<String>? = null
private var deathmatchList: ArrayList<String>? = null
private var eliminationList: ArrayList<String>? = null
private var escortList: ArrayList<String>? = null
private var flashpointList: ArrayList<String>? = null
private var hybridList: ArrayList<String>? = null
private var pushList: ArrayList<String>? = null
private var teamDeatList: ArrayList<String>? = null

class ModeAdapter(private val context: Context, private val modeList: ArrayList<ModeList>, private val mapList: ArrayList<MapList>):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeViewHolder {
        prepareLists()
        return ModeViewHolder(LayoutInflater.from(context).inflate(R.layout.mode_item, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val modeListL: ModeList = modeList[position]
        if (holder is ModeViewHolder) {
            binding(holder, modeListL)
        }
    }

    override fun getItemCount(): Int {
        return modeList.size
    }

    private fun prepareLists() {
        assaultList = ArrayList()
        captureList = ArrayList()
        controlList = ArrayList()
        eliminationList = ArrayList()
        escortList = ArrayList()
        flashpointList = ArrayList()
        hybridList = ArrayList()
        pushList = ArrayList()
        teamDeatList = ArrayList()
        deathmatchList = ArrayList()
        for (map in mapList) {
            for (game in map.gamemodes) {
                if (game.asString.contains("assault")) assaultList!!.add(map.screenshot)
                else if (game.asString.contains("capture-the-flag")) captureList!!.add(map.screenshot)
                else if (game.asString.contains("control")) controlList!!.add(map.screenshot)
                else if (game.asString.contains("elimination")) eliminationList!!.add(map.screenshot)
                else if (game.asString.contains("escort")) escortList!!.add(map.screenshot)
                else if (game.asString.contains("flashpoint")) flashpointList!!.add(map.screenshot)
                else if (game.asString.contains("hybrid")) hybridList!!.add(map.screenshot)
                else if (game.asString.contains("push")) pushList!!.add(map.screenshot)
                else if (game.asString.contains("team")) teamDeatList!!.add(map.screenshot)
                else if (game.asString.contains("deathmatch")) deathmatchList!!.add(map.screenshot)
            }
        }
    }

    class ModeViewHolder(binding: View): RecyclerView.ViewHolder(binding)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun binding(holder: RecyclerView.ViewHolder, modeListL: ModeList) {
        val modeTV: TextView = holder.itemView.findViewById(R.id.modeTV)
        val modeIV: ImageView = holder.itemView.findViewById(R.id.modeIV)
        val modeMapIV1: DiagonalImageView = holder.itemView.findViewById(R.id.modeMapIV1)
        val modeMapIV2: DiagonalImageView = holder.itemView.findViewById(R.id.modeMapIV2)
        val modeMapIV3: DiagonalImageView = holder.itemView.findViewById(R.id.modeMapIV3)

        modeTV.text = modeListL.name

        when {
            modeListL.icon.contains("assault") -> {
                modeIV.setImageResource(R.drawable.assault_icon)
                setMapsToModes(assaultList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("capture-the-flag") -> {
                modeIV.setImageResource(R.drawable.capture_the_flag_icon)
                setMapsToModes(captureList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("control") -> {
                modeIV.setImageResource(R.drawable.control_icon)
                setMapsToModes(controlList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("team-deathmatch") -> {
                modeIV.setImageResource(R.drawable.team_deathmatch_icon)
                setMapsToModes(teamDeatList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("deathmatch") -> {
                modeIV.setImageResource(R.drawable.deathmatch_icon)
                setMapsToModes(deathmatchList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("elimination") -> {
                modeIV.setImageResource(R.drawable.elimination_icon)
                setMapsToModes(eliminationList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("escort") -> {
                modeIV.setImageResource(R.drawable.escort_icon)
                setMapsToModes(escortList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("flashpoint") -> {
                modeIV.setImageResource(R.drawable.flashpoint_icon)
                setMapsToModes(flashpointList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("hybrid") -> {
                modeIV.setImageResource(R.drawable.hybrid_icon)
                setMapsToModes(hybridList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
            modeListL.icon.contains("push") -> {
                modeIV.setImageResource(R.drawable.push_icon)
                setMapsToModes(pushList, modeMapIV1, modeMapIV2, modeMapIV3)
            }
        }

        // Ajusta el texto automaticamente dependiendo del tamaño del textView
        val min = 10
        val max = 26
        val step = 4

        modeTV.setAutoSizeTextTypeUniformWithConfiguration(min, max, step, TypedValue.COMPLEX_UNIT_SP)
    }

    private fun setMapsToModes(list: ArrayList<String>?, modeMapIV1: DiagonalImageView, modeMapIV2: DiagonalImageView, modeMapIV3: DiagonalImageView) {
        val scale = context.resources.displayMetrics.density
        when (list?.size) {
            in 3..list!!.size -> {
                val listaRandom = list.shuffled().take(3)
                modeMapIV1.visibility = View.VISIBLE
                modeMapIV2.visibility = View.VISIBLE
                modeMapIV1.layoutParams.width = (110 * scale + 0.5f).toInt()
                modeMapIV2.layoutParams.width = (110 * scale + 0.5f).toInt()
                modeMapIV3.layoutParams.width = (110 * scale + 0.5f).toInt()
                modeMapIV3.let { Glide.with(context).load(listaRandom[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(listaRandom[1]).into(it) }
                modeMapIV1.let { Glide.with(context).load(listaRandom[2]).into(it) }
            }
            2 -> {
                val listaRandom = list.shuffled().take(3)
                modeMapIV1.visibility = View.GONE
                modeMapIV2.visibility = View.VISIBLE
                modeMapIV1.layoutParams.width = (110 * scale + 0.5f).toInt()
                modeMapIV2.layoutParams.width = (150 * scale + 0.5f).toInt()
                modeMapIV3.layoutParams.width = (150 * scale + 0.5f).toInt()
                modeMapIV2.requestLayout()
                modeMapIV3.requestLayout()
                modeMapIV3.let { Glide.with(context).load(listaRandom[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(listaRandom[1]).into(it) }
            }
            1 -> {
                modeMapIV1.visibility = View.GONE
                modeMapIV2.visibility = View.GONE
                modeMapIV1.layoutParams.width = (110 * scale + 0.5f).toInt()
                modeMapIV2.layoutParams.width = (110 * scale + 0.5f).toInt()
                modeMapIV3.layoutParams.width = (270 * scale + 0.5f).toInt()
                modeMapIV3.requestLayout()
                modeMapIV3.let { Glide.with(context).load(list[0]).into(it) }
            }
        }
    }
}
