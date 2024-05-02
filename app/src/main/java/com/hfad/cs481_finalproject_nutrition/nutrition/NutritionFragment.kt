package com.hfad.cs481_finalproject_nutrition.nutrition

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.view.marginTop
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.hfad.cs481_finalproject_nutrition.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NutritionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NutritionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //lateinit var barChart: BarChart
    //------initalizing bars
    private lateinit var totalCalories_bar: View
    private lateinit var breakfastCalories_bar: View
    private lateinit var lunchCalories_bar: View
    private lateinit var dinnerCalories_bar: View
    private lateinit var snackCalories_bar: View
    private lateinit var water_bar: View
    private lateinit var buttonAddFood: Button

    private var initialHeight = 10 // Initial height

    private var currentHeightTC = initialHeight
    private var currentHeightBC = initialHeight
    private var currentHeightLC = initialHeight
    private var currentHeightDC = initialHeight
    private var currentHeightSC = initialHeight
    private var currentHeightW = initialHeight

    private var initialBottomMargin = 0// Initial top margin
    private var currentBottomMargin = initialBottomMargin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment )
        val view = inflater.inflate(R.layout.fragment_nutrition, container, false)

        totalCalories_bar = view.findViewById(R.id.totalCalories_bar)
        breakfastCalories_bar = view.findViewById(R.id.breakfastCalories_bar)
        lunchCalories_bar = view.findViewById(R.id.lunchCalories_bar)
        dinnerCalories_bar = view.findViewById(R.id.dinnerCalories_bar)
        snackCalories_bar = view.findViewById(R.id.snackCalories_bar)
        water_bar = view.findViewById(R.id.water_bar)

        buttonAddFood = view.findViewById(R.id.bAddFood)
        /*
        val increaseButton = view.findViewById<Button>(R.id.bIncrease)
        val decreaseButton= view.findViewById<Button>(R.id.bDecrease)

        increaseButton.setOnClickListener { animateIncreaseHeight(water_bar) }
        decreaseButton.setOnClickListener { animateDecreaseHeight(water_bar) }

         */
        buttonAddFood.setOnClickListener{
            //val intent = Intent(this, AddFoodActivity::class.java)
            //val intent = Intent(activity, AddFoodActivity::class.java)
            //getActivity().startActivity(intent)
            activity?.let {
                val intent = Intent(it, AddFoodActivity::class.java)
                it.startActivity(intent)
            }
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun animateIncreaseHeight(dynamicBar: View) {
        val startHeight = getCurrentHeight(dynamicBar)
        val endHeight = startHeight + 50 // Increase height by 50 (adjust as needed)

        val animator = ValueAnimator.ofInt(startHeight, endHeight)
        animator.addUpdateListener { valueAnimator ->
            setCurrentHeight(dynamicBar, valueAnimator.animatedValue as Int)
        }
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 500 // Animation duration in milliseconds
        animator.start()
    }

    private fun animateDecreaseHeight(dynamicBar: View) {
        val startHeight = getCurrentHeight(dynamicBar)
        val endHeight = startHeight - 50 // Decrease height by 50 (adjust as needed)

        val animator = ValueAnimator.ofInt(startHeight, endHeight)
        animator.addUpdateListener { valueAnimator ->
            setCurrentHeight(dynamicBar, valueAnimator.animatedValue as Int)
        }
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 500 // Animation duration in milliseconds
        animator.start()
    }

    private fun getCurrentHeight(dynamicBar: View): Int {
        return when (dynamicBar.id) {
            R.id.totalCalories_bar -> currentHeightTC
            R.id.breakfastCalories_bar -> currentHeightBC
            R.id.lunchCalories_bar -> currentHeightLC
            R.id.dinnerCalories_bar -> currentHeightDC
            R.id.snackCalories_bar -> currentHeightSC
            R.id.water_bar -> currentHeightW
            // Add more cases for additional dynamic bars
            else -> initialHeight
        }
    }

    private fun setCurrentHeight(dynamicBar: View, height: Int) {
        when (dynamicBar.id) {
            R.id.totalCalories_bar -> currentHeightTC = height
            R.id.breakfastCalories_bar -> currentHeightBC = height
            R.id.lunchCalories_bar -> currentHeightLC = height
            R.id.dinnerCalories_bar -> currentHeightDC = height
            R.id.snackCalories_bar -> currentHeightSC = height
            R.id.water_bar -> currentHeightW = height
            // Add more cases for additional dynamic bars
        }
        updateBarHeight(dynamicBar, height)
    }

    private fun updateBarHeight(dynamicBar: View, height: Int) {
        val layoutParams = dynamicBar.layoutParams as FrameLayout.LayoutParams
        layoutParams.height = height
        dynamicBar.layoutParams = layoutParams
        dynamicBar.requestLayout()
    }

    //=====================================================================
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NutritionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NutritionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}