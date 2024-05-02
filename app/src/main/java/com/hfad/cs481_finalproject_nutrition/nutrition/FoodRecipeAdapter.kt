package com.hfad.cs481_finalproject_nutrition.nutrition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.cs481_finalproject_nutrition.R
import com.squareup.picasso.Picasso

class FoodRecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<FoodRecipeAdapter.RecipeViewHolder>() {

        //private val RecipeResponseList: ArrayList<RecipeResponse> = ArrayList<RecipeResponse>()
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        // Add other views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.titleTextView.text = recipe.label
        // Load image using Picasso (replace "placeholder_image" with your placeholder drawable)
        Picasso.get().load(recipe.imageUrl).placeholder(R.drawable.ic_launcher_background).into(holder.imageView)
        // Add click listener or other functionality as needed
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
