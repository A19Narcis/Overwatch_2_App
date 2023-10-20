package com.overwatch.overwatch2app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.overwatch.overwatch2app.databinding.ActivityMainBinding
import com.overwatch.overwatch2app.fragments.HeroesFragment
import com.overwatch.overwatch2app.fragments.InicioFragment
import com.overwatch.overwatch2app.fragments.MapasModosFragment
import com.overwatch.overwatch2app.fragments.PerfilFragment

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.bottomNavView?.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.inicioMenu -> replaceFragment(InicioFragment())
                R.id.heroesMenu -> replaceFragment(HeroesFragment())
                R.id.mapasmodosMenu -> replaceFragment(MapasModosFragment())
                R.id.perfilesMenu -> replaceFragment(PerfilFragment())
            }
            true
        }

        binding?.bottomNavView?.itemIconTintList = null
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}