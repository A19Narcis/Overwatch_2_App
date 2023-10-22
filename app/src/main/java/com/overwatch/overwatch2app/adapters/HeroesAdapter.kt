package com.overwatch.overwatch2app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.models.Hero

class HeroesAdapter(private val context :Context, private val heroList: ArrayList<Hero>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(LayoutInflater.from(context).inflate(R.layout.hero_item, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val heroL : Hero = heroList!![position]
        if (holder is HeroViewHolder) {
            binding(holder, heroL)
        }
    }

    override fun getItemCount(): Int {
        return heroList!!.size
    }

    class HeroViewHolder(binding: View): RecyclerView.ViewHolder(binding)

    fun clearData() {
        heroList?.clear()
    }
    private fun binding(holder: RecyclerView.ViewHolder, heroL : Hero) {
        val heroPortrait : ImageView = holder.itemView.findViewById(R.id.heroPortraitIV)
        val heroRole : ImageView = holder.itemView.findViewById(R.id.roleIV)
        val heroName : TextView = holder.itemView.findViewById(R.id.heroNameTV)

        heroPortrait.let { Glide.with(context).load(heroL.portrait).into(it) }
        heroRole.let { Glide.with(context).load(heroL.role).into(it) }

        //heroPortrait.setImageURI(Uri.parse(heroL.portrait))
        //heroRole.setImageURI(Uri.parse(heroL.role))
        heroName.text = heroL.name
    }


}
