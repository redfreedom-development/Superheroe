package com.example.superheroe.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.superheroe.R
import com.example.superheroe.databinding.ActivityDetailBinding

import com.example.superheroe.databinding.ActivityMainBinding
import com.example.superheroe.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.superheroe.data.Superhero
import com.squareup.picasso.Picasso

class DetailActivity: AppCompatActivity() {



    companion object {
        //creamos constante donde irá el id pasado del main a aqui y lo inicializamos
       const val ID_SUPERHERO = ""
    }

    private lateinit var binding: ActivityDetailBinding
    lateinit var superhero: Superhero


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       //con este Binding ya podemos inflar y acceder a cualquier cosa de la pantalla(textview, imgview etcetc)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(ID_SUPERHERO)!!

        obtener_superhero_elegido(id)


    }

    private fun obtener_superhero_elegido(id: String) {

        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                superhero = service.findById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    cargar_datos_en_pantalla()
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }

    }

    private fun cargar_datos_en_pantalla() {
        supportActionBar?.title = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.imgSuperhero)
        binding.txtInteligencia.text=superhero.powerstats.intelligence.toString()
        barra_powerstats(binding.barInteligencia,superhero.powerstats.intelligence.toInt())
        binding.txtFuerza.text=superhero.powerstats.strength.toString()
        barra_powerstats(binding.barFuerza,superhero.powerstats.intelligence.toInt())


    }

    private fun barra_powerstats(progressBar: ProgressBar, cantidad:Int) {

        // Ajusta el progreso
        progressBar.progress = cantidad.coerceIn(0, progressBar.max)

        val color = when {
             cantidad < 25 -> ContextCompat.getColor(this, R.color.low) // Bajo
             cantidad < 50 -> ContextCompat.getColor(this, R.color.medium_low) // Medio
            cantidad < 75 -> ContextCompat.getColor(this, R.color.medium_higth) // Bajo
            else -> ContextCompat.getColor(this, R.color.higth) // Alto
        }

        val drawable = progressBar.progressDrawable.mutate() // Evita compartir el estado entre instancias
        drawable.setTint(color)
        progressBar.progressDrawable = drawable

    }


}




