package com.example.plant_039.Model.Local

import com.example.plant_039.Model.Local.Entitys.FlowerDetails
import com.example.plant_039.Model.Local.Entitys.FlowerList
import com.example.plant_039.Model.Remote.FromInternet.DetailsFlower
import com.example.plant_039.Model.Remote.FromInternet.ListFlowers

fun fromInternetListFlowers(flowerList: List<ListFlowers>): List<FlowerList> {

    return flowerList.map {
        FlowerList(
            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion

        )
    }
}


fun fromInternetDetailsFlowers(detailsFlower: DetailsFlower): FlowerDetails {

    return FlowerDetails(
        id = detailsFlower.id,
        nombre = detailsFlower.nombre,
        tipo = detailsFlower.tipo,
        imagen = detailsFlower.imagen,
        descripcion = detailsFlower.descripcion
    )
}