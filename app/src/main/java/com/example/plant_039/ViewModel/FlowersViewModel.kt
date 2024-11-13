package com.example.plant_039.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.plant_039.Model.Local.Databse.FlowerDataBase
import com.example.plant_039.Model.Local.Entitys.FlowerDetails
import com.example.plant_039.Model.Local.Entitys.FlowerList
import com.example.plant_039.Model.FlowerRepository
import kotlinx.coroutines.launch

class FlowersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FlowerRepository
    private var idSelected: Int = 0

    init {
        val bd = FlowerDataBase.getdatabase(application)
        val Dao = bd.getFlowerDao()
        repository = FlowerRepository(Dao)
        viewModelScope.launch {
            repository.fetchList()
        }


    }

    fun getFlowersList(): LiveData<List<FlowerList>> = repository.flowerListLiveData



    // es cuando selecciono una
    fun getFlowersDetailsByIdFromInternet(id: Int) = viewModelScope.launch {
        idSelected = id
        repository.fetchFlowerDetails(id)

    }


    // obtener el detalle de una flor
    fun getFlowersDetail(): LiveData<FlowerDetails> = repository.getFlowersDetailsById(idSelected)
}