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

private var assaultList: ArrayList<String> = ArrayList()
private var captureList: ArrayList<String> = ArrayList()
private var controlList: ArrayList<String> = ArrayList()
private var deathmatchList: ArrayList<String> = ArrayList()
private var eliminationList: ArrayList<String> = ArrayList()
private var escortList: ArrayList<String> = ArrayList()
private var flashpointList: ArrayList<String> = ArrayList()
private var hybridList: ArrayList<String> = ArrayList()
private var pushList: ArrayList<String> = ArrayList()
private var teamDeatList: ArrayList<String> = ArrayList()

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
        for (map in mapList) {
            for (game in map.gamemodes) {
                if (game.equals("assault")) assaultList.add(map.screenshot)
                else if (game.equals("capture-the-flag")) captureList.add(map.screenshot)
                else if (game.equals("control")) controlList.add(map.screenshot)
                else if (game.equals("deathmatch")) deathmatchList.add(map.screenshot)
                else if (game.equals("elimination")) eliminationList.add(map.screenshot)
                else if (game.equals("escort")) escortList.add(map.screenshot)
                else if (game.equals("flashpoint")) flashpointList.add(map.screenshot)
                else if (game.equals("hybrid")) hybridList.add(map.screenshot)
                else if (game.equals("push")) pushList.add(map.screenshot)
                else if (game.equals("team")) teamDeatList.add(map.screenshot)
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
                modeMapIV1.let { Glide.with(context).load(assaultList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(assaultList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(assaultList[2]).into(it) }
            }
            modeListL.icon.contains("capture-the-flag") -> {
                modeIV.setImageResource(R.drawable.capture_the_flag_icon)
                modeMapIV1.let { Glide.with(context).load(captureList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(captureList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(captureList[2]).into(it) }
            }
            modeListL.icon.contains("control") -> {
                modeIV.setImageResource(R.drawable.control_icon)
                modeMapIV1.let { Glide.with(context).load(controlList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(controlList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(controlList[2]).into(it) }
            }
            modeListL.icon.contains("deathmatch") -> {
                modeIV.setImageResource(R.drawable.deathmatch_icon)
                modeMapIV1.let { Glide.with(context).load(deathmatchList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(deathmatchList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(deathmatchList[2]).into(it) }
            }
            modeListL.icon.contains("elimination") -> {
                modeIV.setImageResource(R.drawable.elimination_icon)
                modeMapIV1.let { Glide.with(context).load(eliminationList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(eliminationList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(eliminationList[2]).into(it) }
            }
            modeListL.icon.contains("escort") -> {
                modeIV.setImageResource(R.drawable.escort_icon)
                modeMapIV1.let { Glide.with(context).load(escortList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(escortList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(escortList[2]).into(it) }
            }
            modeListL.icon.contains("flashpoint") -> {
                modeIV.setImageResource(R.drawable.flashpoint_icon)
                modeMapIV1.let { Glide.with(context).load(flashpointList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(flashpointList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(flashpointList[2]).into(it) }
            }
            modeListL.icon.contains("hybrid") -> {
                modeIV.setImageResource(R.drawable.hybrid_icon)
                modeMapIV1.let { Glide.with(context).load(hybridList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(hybridList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(hybridList[2]).into(it) }
            }
            modeListL.icon.contains("push") -> {
                modeIV.setImageResource(R.drawable.push_icon)
                modeMapIV1.let { Glide.with(context).load(pushList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(pushList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(pushList[2]).into(it) }
            }
            modeListL.icon.contains("team-deathmatch") -> {
                modeIV.setImageResource(R.drawable.team_deathmatch_icon)
                modeMapIV1.let { Glide.with(context).load(teamDeatList[0]).into(it) }
                modeMapIV2.let { Glide.with(context).load(teamDeatList[1]).into(it) }
                modeMapIV3.let { Glide.with(context).load(teamDeatList[2]).into(it) }
            }
        }

        // Ajusta el texto automaticamente dependiendo del tamaño del textView
        val min = 10
        val max = 30
        val step = 2

        modeTV.setAutoSizeTextTypeUniformWithConfiguration(
            min,
            max,
            step,
            TypedValue.COMPLEX_UNIT_SP
        )
    }
}
