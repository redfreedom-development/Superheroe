package com.example.superheroe.activities

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroe.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_busqueda_activity_main, menu) // Inflar el menú

        val searchItem = menu?.findItem(R.id.id_menu_search)
        val searchView = searchItem?.actionView as SearchView // Obtener el SearchView

        // Configurar el SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Manejar la búsqueda al enviar la consulta es decir al dar al Enter(no haremos nada)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Manejar los cambios en el texto de búsqueda


                return false//pongo false porque esta funcion es obligatoria pero no va a llevar nada
            }
        })

        return true
    }


}