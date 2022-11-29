package com.example.psm_pocketschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.psm_pocketschool.databinding.ActivityHomeBinding
import com.example.psm_pocketschool.databinding.ActivityMainBinding
import com.example.psm_pocketschool.fragments.*
import com.example.psm_pocketschool.fragments.Home

class Home : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_home) //o
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.search->{
                    replaceFragment(Search())
                }
                R.id.profile->{
                    replaceFragment(Profile())
                }
                R.id.home->{
                    replaceFragment(Home())
                }
                R.id.mistareas->{
                    replaceFragment(MisTareas())
                }
                R.id.misgrupos->{

                    replaceFragment(Groups())
                }
                else->{

                }
            }
            true
        }
        binding.bottomNavigationView.menu.findItem(R.id.home).setChecked(true)

        //indicamos al controlador que se cambio la toolbar por una nueva
        val toolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val drawer=findViewById<DrawerLayout>(R.id.drawer)
        setSupportActionBar(toolbar)
        //Icono hamburgesa
        val toggle=ActionBarDrawerToggle(this, drawer, toolbar, R.string.Open,R.string.Close )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}