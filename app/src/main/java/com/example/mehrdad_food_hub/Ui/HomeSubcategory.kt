package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehrdad_food_hub.Adapter.SubcategoryAdapter
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.ViewModel.SharedViewModel
import com.example.mehrdad_food_hub.databinding.FragmentHomeSubcategoryBinding
import com.example.mehrdad_food_hub.databinding.FragmentProductPageBinding


class HomeSubcategory : Fragment() {
    lateinit var binding: FragmentHomeSubcategoryBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeSubcategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = arguments?.let { HomeSubcategoryArgs.fromBundle(it).categoryName }
        binding.SubShowText.text = categoryName

        binding.backToCategory.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_homeSubcategory_to_homeScreen)
            }, 200) // 4 seconds delay
        }

        binding.RecyclerViewSubcategory.layoutManager = LinearLayoutManager(requireContext())

        viewModel.subcategories.observe(viewLifecycleOwner, Observer { subcategories ->
            val sortedSubcategories = subcategories.sortedBy { it.name }
            val subcategoryAdapter = SubcategoryAdapter(sortedSubcategories) { subcategory ->
                viewModel.setSelectedSubcategory(subcategory)
                findNavController().navigate(R.id.action_homeSubcategory_to_productPage)
            }
            binding.RecyclerViewSubcategory.adapter = subcategoryAdapter
        })
    }
}
