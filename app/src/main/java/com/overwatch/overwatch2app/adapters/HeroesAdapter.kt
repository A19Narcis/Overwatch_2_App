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
import com.overwatch.overwatch2app.models.Hero.HeroList

class HeroesAdapter(private val context: Context, private val heroList: ArrayList<HeroList>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(LayoutInflater.from(context).inflate(R.layout.hero_item, parent, false))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val heroListL: HeroList = heroList[position]
        if (holder is HeroViewHolder) {
            binding(holder, heroListL)
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    class HeroViewHolder(binding: View): RecyclerView.ViewHolder(binding)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun binding(holder: RecyclerView.ViewHolder, heroListL: HeroList) {
        val heroName: TextView = holder.itemView.findViewById(R.id.heroNameTV)
        val heroRole: ImageView = holder.itemView.findViewById(R.id.roleIV)
        val heroPortrait: ImageView = holder.itemView.findViewById(R.id.heroPortraitIV)

        heroName.text = heroListL.name

        when {
            heroListL.role.contains("tank") -> {
                heroRole.setImageResource(R.drawable.tank_role)
            }
            heroListL.role.contains("damage") -> {
                heroRole.setImageResource(R.drawable.damage_role)
            }
            heroListL.role.contains("support") -> {
                heroRole.setImageResource(R.drawable.support_role)
            }
        }

        heroPortrait.let { Glide.with(context).load(heroListL.portrait).into(it) }

        // Ajusta el texto automaticamente dependiendo del tamaño del textView
        val min = 6
        val max = 22
        val step = 2

        heroName.setAutoSizeTextTypeUniformWithConfiguration(
            min,
            max,
            step,
            TypedValue.COMPLEX_UNIT_SP
        )
    }
}
