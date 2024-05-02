package com.hfad.cs481_finalproject_nutrition.nutrition

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipeService {
    @GET("api/recipes/v2")
    fun searchRecipes(
        @Query("type") type: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("q") q: String,
        @Query("mealType") mealType: String?,
        //@Query("random") random: Boolean?
    ): Call<List<RecipeResponse>>
}