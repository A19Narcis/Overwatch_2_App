package com.overwatch.overwatch2app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.overwatch.overwatch2app.R
import com.overwatch.overwatch2app.adapters.HeroesAdapter
import com.overwatch.overwatch2app.models.Hero

class HeroesFragment : Fragment() {

    private var root : View? = null

    private val baseURL = "https://overfast-api.tekrop.fr/"

    private var heroList : ArrayList<Hero>? = null

    private var heroesTV : TextView? = null
    private var selectionRoleGroup : RadioGroup? = null
    private var allRButton : RadioButton? = null
    private var tankRButton : RadioButton? = null
    private var damageRButton : RadioButton? = null
    private var supportRButton : RadioButton? = null
    private var recyclerViewHeroes : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_heroes, container, false)

        heroesTV = root?.findViewById(R.id.heroesTextView)
        selectionRoleGroup = root?.findViewById(R.id.seleciotnRGroup)
        allRButton = root?.findViewById(R.id.allHeroesRButton)
        tankRButton = root?.findViewById(R.id.tankRButton)
        damageRButton = root?.findViewById(R.id.damageRButton)
        supportRButton = root?.findViewById(R.id.supportRButton)
        recyclerViewHeroes = root?.findViewById(R.id.hereosRecycleView)

        val key : String = "ashe"
        val name : String = "Ashe"
        val portrait : String = "https://d15f34w2p8l1cc.cloudfront.net/overwatch/3429c394716364bbef802180e9763d04812757c205e1b4568bc321772096ed86.png"
        val role : String = "https://blz-contentstack-images.akamaized.net/v3/assets/blt9c12f249ac15c7ec/bltc1d840ba007f88a8/62ea89572fdd1011027e605d/Damage.svg"

        val hero1 = Hero(key, name, portrait, role)

        /* heroList!!.add(hero1) */

        /*val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerViewHeroes?.layoutManager = layoutManager */

        val adapter = HeroesAdapter(requireContext(), heroList)
        recyclerViewHeroes!!.adapter = adapter

       /*  selectionRoleGroup?.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.allHeroesRButton){
                makeVisibleMetricsUnitsView()
            } else if (checkedId == R.id.tankRButton){
                makeVisibleUsUnitsView()
            } else if (checkedId == R.id.damageRButton){
                makeVisibleUsUnitsView()
            } else if (checkedId == R.id.supportRButton){
                makeVisibleUsUnitsView()
            }
        } */






        return root
    }
}