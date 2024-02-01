package com.mpd.pmdm.navigationuimenus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.mpd.pmdm.navigationuimenus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2. añadimos tollbar.SETER
        setSupportActionBar(binding.myToolbar)
        //3.Objetivo llamar al setup de action bar con nav controller para configurarlo.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        //navController y necesitamos la configuración.
    }

    //devuelve boolean o muestra o no muestra las opciones.

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflamos el menú,  seteado como action barinicializamos el menú.
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.top_menu, menu)//recurso de menú que tenemos que crear
        //como definir un menu en XML en google menus
        //recurso alt+insert y metemos
        return true
    }

    //posubles ids de cada items
    override fun onOptionsItemSelected(item: MenuItem): Boolean{

        val navController = findNavController(R.id.fragmentContainerView)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        /**
         * Aquí fue lo primero que hicimos "manualmente" ahora tenemos el codigo de arriba de dos
         * líneas que asocia el navController con el navigationUI
        val navController = findNavController(R.id.fragmentContainerView)

        return when (item.itemId) {
        //llama a lambda
        R.id.item_home -> {
            //llamamos al findNavController como parámetro, en los fragments estaba sin parametro.
            navController.navigate(R.id.homeFragment)
            true
        }

        R.id.datos_item -> {
           navController.navigate(R.id.userFragment)
            true
        }

        else -> {
            // The user's action isn't recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
        }**/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}