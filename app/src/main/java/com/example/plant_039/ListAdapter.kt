package com.example.plant_039

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plant_039.Model.Local.Entitys.FlowerList
import com.example.plant_039.databinding.FlowerlistBinding

class ListsAdapter : RecyclerView.Adapter<ListsAdapter.viewholder>() {

    private var listFlowers = listOf<FlowerList>()
    private val selectedFlower = MutableLiveData<FlowerList>()

    // para seleccionar el detalle de una flor
    fun selectedFlower(): LiveData<FlowerList> = selectedFlower



    // xml diseño del RV
    inner class viewholder(private val binding: FlowerlistBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(list: FlowerList) {

            Glide.with(binding.imageRv).load(list.imagen).centerCrop().into(binding.imageRv)
            binding.textNameRV.text = binding.root.context.getString(R.string.NombreRv, list.nombre)
            binding.textTipoRV.text = binding.root.context.getString(R.string.TipoRv, list.tipo)
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {

            selectedFlower.value = listFlowers[bindingAdapterPosition]
            Log.d("ONCLICK", bindingAdapterPosition.toString())
        }

    }

    fun update(list: List<FlowerList>) {
        listFlowers = list
        notifyDataSetChanged()
    }

// INFLO LA VISTA
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(FlowerlistBinding.inflate(LayoutInflater.from(parent.context)))
    }


    // LLEVA LA CUENTA DE CUANTOS ELEMENTOS TENEMOS EN EL RV
    override fun getItemCount(): Int = listFlowers.size


    // POSICIONA LOS ELEMENTOS
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(listFlowers[position])
    }


}