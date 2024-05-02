package com.hfad.cs481_finalproject_nutrition.nutrition

data class RecipeResponse(
    val results: List<Recipe>
)
data class Recipe(
    val label: String,
    val imageUrl: String,
)