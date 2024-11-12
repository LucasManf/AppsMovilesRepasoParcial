package com.example.claserepaso2doparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvCount: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var listQuakes = mutableListOf<Terremoto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerQuakes)
        tvTitle = findViewById(R.id.textViewMainTitle)
        tvCount = findViewById(R.id.textViewMainCount)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(listQuakes)
        recyclerView.adapter = adapter

        getQuakes()
    }

    private fun getQuakes() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getQuakesSigMonth(SIGNIFICANT_QUAKES_MONTH)
            val response = call.body()

            runOnUiThread {                 //lo que venga de codigo ahora, permita correrlo dentro del thread de la UI.
                if(call.isSuccessful) {
                    val quakes = response?.features
                    quakes?.forEach {
                        listQuakes.add(it)  //Aca estoy agarrando lo que me dio el response (que lo agarra del json, y me devuelve los valores que le dije en la clase Quake), y eso que me devuelve, lo guardo en las variables quake, metadata, etc, que son los que voy a mostrar en el UI
                    }
                    val metadata = response?.metadata?.title
                    tvTitle.text = metadata
                    val count = response?.metadata?.count
                    tvCount.text = "Mostrando $count terremotos"

                    adapter.notifyDataSetChanged()

                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
        const val ALL_QUAKES_LAST_DAY = "all_day.geojson"
        const val SIGNIFICANT_QUAKES_MONTH = "significant_month.geojson"
    }
}