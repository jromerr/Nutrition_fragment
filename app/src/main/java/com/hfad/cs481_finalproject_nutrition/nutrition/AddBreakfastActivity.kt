package com.hfad.cs481_finalproject_nutrition.nutrition

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.cs481_finalproject_nutrition.R
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.RecyclerView

class AddBreakfastActivity : AppCompatActivity() {
    private val app_id = "3bbb8584"
    private val app_key = "56e2c02c506ba022c4931056ba0bf2ee"
    private val type = "public"
    private val random = true
    private val mealType = "Breakfast"
    private var query = ""
    private lateinit var foodRecipeService: FoodRecipeService

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.edamam.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodRecipeService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_breakfast)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.edamam.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        foodRecipeService = retrofit.create(FoodRecipeService::class.java)

         */
        /*
        val list = emptyList<Recipe>()
        val adapter = FoodRecipeAdapter(list)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = FoodRecipeAdapter(list)
        recyclerView.adapter = adapter

         */

        val backButton = findViewById<Button>(R.id.bBackBreakfast)
        val userInput = findViewById<EditText>(R.id.inputBreakfast)
        userInput.setOnClickListener{
            query = userInput.text.toString()
            Log.d("USER INPUT", query)
            searchRecipes(query)
            //adapter.notifyDataSetChanged()
        }
        backButton.setOnClickListener{
            finish()
        }
    }

    private fun searchRecipes(q: String) {
        val call = retrofitBuilder.searchRecipes(type, app_id, app_key, q, mealType)
        Log.d("API CALL", call.toString())
        call.enqueue(object : Callback<List<RecipeResponse>>{
            override fun onResponse(
                call: Call<List<RecipeResponse>>,
                response: Response<List<RecipeResponse>>
            ) {
                if (response.isSuccessful) {
                    val recipes = response.body()
                    recipes?.let {
                        for (recipe in it){
                            Log.d("APISUCCESS","ID: " + recipes)
                        }
                        /*
                        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                        val adapter = FoodRecipeAdapter(it)
                        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        recyclerView.adapter = FoodRecipeAdapter(recipes)
                        recyclerView.adapter = adapter

                         */

                    }
                } else {
                    // Handle API error
                }
            }
            override fun onFailure(call: Call<List<RecipeResponse>>, t: Throwable) {
                // Handle network error
                Log.d("APIFAIL", "message: " + t.message)
            }
        })
    }
}