package com.example.plant_039.Model.Local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plant_039.Model.Local.Entitys.FlowerDetails
import com.example.plant_039.Model.Local.Entitys.FlowerList

@Dao
interface FlowerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFlowers(flowerList: List<FlowerList>)

    @Query("SELECT * FROM List_Flowers ORDER BY id ASC")
    fun getAllFlowers(): LiveData<List<FlowerList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlowersDetail(flowerDetails: FlowerDetails)

    @Query("SELECT * FROM Details_Flowers WHERE id = :id")
    fun getFlowersDetailById(id: Int): LiveData<FlowerDetails>
}
