package com.example.claserepaso2doparcial

import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

data class Quakes (
    val metadata: Metadata,
    val features: List<Terremoto> //La val se llama features porque asi se llama en el json, asi lo puede encontrar. La clase "Terremoto" se puede llamar como quiera
)

data class Metadata (
    val title: String,
    val count: Int
)

data class Terremoto (
    val type: String,
    val properties: Properties,
    val geometry: Geometry,
    val id: String
)


data class Properties (
    val mag: Double,
    val place: String,
    val title: String
)


data class Geometry (
    val coordinates: List<Double>
)