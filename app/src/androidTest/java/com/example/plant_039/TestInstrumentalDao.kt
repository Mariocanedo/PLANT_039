package com.example.plant_039

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.plant_039.Model.Local.Dao.FlowerDao
import com.example.plant_039.Model.Local.Databse.FlowerDataBase
import com.example.plant_039.Model.Local.Entitys.FlowerDetails
import com.example.plant_039.Model.Local.Entitys.FlowerList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test


class TestInstrumentalDao {



    private lateinit var fDao: FlowerDao
    private lateinit var fDB: FlowerDataBase



   @Before
   fun setUp(){

       val context = ApplicationProvider.getApplicationContext<Context>()
       fDB = Room.inMemoryDatabaseBuilder(context, FlowerDataBase::class.java).build()
       fDao = fDB.getFlowerDao()


   }



   @After
   fun shutDown(){
       fDB.close()

   }



   @Test
   fun inserListFlowers()= runBlocking {
     val flowerList= listOf(

         FlowerList(1, "Flor1", "tipo1", "imagen1", "descripcion1"),
         FlowerList(2, "Flor2", "tipo2", "imagen2", "descripcion2"),
         FlowerList(3, "Flor3", "tipo3", "imagen3", "descripcion3"),
         FlowerList(4, "Flor4", "tipo4", "imagen4", "descripcion4"),
         FlowerList(5, "Flor5", "tipo5", "imagen5", "descripcion5")



     )

       fDao.insertAllFlowers(flowerList)
       val flowerLiveData = fDao.getAllFlowers()
       val listFlowers: List<FlowerList> = flowerLiveData.value ?: emptyList()

       assertThat( listFlowers, not(emptyList()))
       MatcherAssert.assertThat(listFlowers.size, equalTo(5))
   }


@Test

fun insertFlowerDetails() = runBlocking {
    val details = FlowerDetails(1, "Flor1", "tipo1", "imagen1", "descripcion1")
    fDao.insertFlowersDetail(details)
    val flowerLiveData = fDao.getFlowersDetailById(1)
    val flower = flowerLiveData.value


    assertThat(flower?.id, equalTo(1))
    assertThat(flower?.nombre, equalTo("Flor1"))


}









}