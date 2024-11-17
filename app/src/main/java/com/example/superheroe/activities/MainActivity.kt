package com.example.superheroe.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroe.data.Superhero

import com.example.superheroe.R
import com.example.superheroe.adapters.SuperheroAdapter


import com.example.superheroe.databinding.ActivityMainBinding
import com.example.superheroe.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: SuperheroAdapter

    var superheroList: List<Superhero> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        buscar_superheroe_retrofit("")

      // creamos el adapter con la funcionalidad del onClick
        adapter = SuperheroAdapter(superheroList) { position ->
            try {
                // Intenta acceder al elemento de la lista usando el position
                val superHero = superheroList[position]
                navigateToDetail(superHero)
            } catch (e: IndexOutOfBoundsException) {
                Log.e("RecyclerView", "Error al acceder al índice $position: ${e.message}")
            } catch (e: Exception) {
                Log.e("RecyclerView", "Error inesperado al hacer clic: ${e.message}")
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)




    }

    private fun navigateToDetail(superHero: Superhero) {

        //curiosamente android mantiene inflados los menus creados al pasar de actividad, por eso
        //si no lo quieres en la pantalla de details debes invalidsarlo con la sig funcion
        //parece que no ha funcionado esta funcion
       // invalidateOptionsMenu()
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID_SUPERHERO, superHero.id)
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


            menuInflater.inflate(R.menu.menu_busqueda_activity_main, menu) // Inflar el menú

            val searchItem = menu?.findItem(R.id.id_menu_search)!!
            val searchView = searchItem.actionView as SearchView // Obtener el SearchView


        // Configurar el SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Manejar la búsqueda al enviar la consulta es decir al dar al Enter
                // En tu Activity o Fragment

                buscar_superheroe_retrofit(query)

                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Manejar los cambios en el texto de búsqueda


                return false//pongo false porque esta funcion es obligatoria pero no va a llevar nada
            }
        })

        return true
    }

    private fun buscar_superheroe_retrofit(query: String) {

        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {

              val result = service.searchByName(query)



             CoroutineScope(Dispatchers.Main).launch{

                    if (result.response == "success") {
                        superheroList = result.results
                        adapter.updateItems(result.results)
                    } else {
                        //  Mostrar mensaje de que no se ha encontrado nada
                    }
               }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }


        }


    }
}