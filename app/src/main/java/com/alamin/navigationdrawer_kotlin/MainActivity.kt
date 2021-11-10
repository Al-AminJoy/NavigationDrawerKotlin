package com.alamin.navigationdrawer_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.system.Os.open
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar:androidx.appcompat.widget.Toolbar;
    lateinit var drawerLayout : DrawerLayout;
    lateinit var navView : NavigationView;
    lateinit var toggle : ActionBarDrawerToggle;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar)
        loadHome();
        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        toggle.isDrawerIndicatorEnabled = true;
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        navView.setNavigationItemSelectedListener {
            it.isChecked = true;
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment(),"Home");
                R.id.category -> replaceFragment(CategoryFragment(),"Category");
                R.id.settings -> replaceFragment(SettingFragment(),"Settings");
            }

            true;
        }
    }

    private fun loadHome() {
        supportFragmentManager.beginTransaction().add(R.id.mainPanel,HomeFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(toggle.onOptionsItemSelected(item)){
           return true;
       }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment:Fragment,title:String){
        val fragmentManager = supportFragmentManager;
        val fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainPanel,fragment).commit();
        drawerLayout.closeDrawers()
        setTitle(title)
    }
}