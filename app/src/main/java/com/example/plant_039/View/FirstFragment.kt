package com.example.plant_039.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plant_039.ListsAdapter
import com.example.plant_039.R
import com.example.plant_039.ViewModel.FlowersViewModel
import com.example.plant_039.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding : FragmentFirstBinding
    // REFERENCIA A VIEWMODEL
    private val viewModel: FlowersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListsAdapter()
        binding.RvList.adapter = adapter
        binding.RvList.layoutManager = GridLayoutManager( context,2)




// función para observar la lista de flores
      viewModel.getFlowersList().observe(viewLifecycleOwner, Observer {

          it?.let {
              Log.d("LISTADO", it.toString())
              adapter.update(it)
          }

      })


        // función para seleccionar

        adapter.selectedFlower().observe(viewLifecycleOwner){
            it?.let {
                Log.d("FlowerChoose", it.toString())
                viewModel.getFlowersDetailsByIdFromInternet(it.id)

            }

            val bundle  = Bundle().apply {

                putInt("FlowerId",it.id)
                putString("FlowerName", it.nombre)
            }


            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)

        }











    }













    override fun onDestroyView() {
        super.onDestroyView()
      //  _binding = null
    }
}