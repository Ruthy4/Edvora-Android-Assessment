package com.example.edvoraassessment

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.edvoraassessment.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isColorPaletteVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        colorSelector()
        setUpViewElement()

    }

    private fun toggleColorPaletteImageView() {
        isColorPaletteVisible = !isColorPaletteVisible
        if (isColorPaletteVisible){
            binding.colorSet.visibility = View.VISIBLE
        } else {
            binding.colorSet.visibility = View.GONE
        }
    }

    //image view elements
    private fun setUpViewElement() {

        binding.colorPaletteLayout.setOnClickListener {
            canvasState = COLOR
            toggleOnSpecificLayout(it, binding.colorPaletteImageView)
            toggleColorPaletteImageView()
        }

        binding.pencilLayout.setOnClickListener {
            canvasState = PENCIL
            binding.colorSet.visibility = View.GONE
            isColorPaletteVisible = false
            toggleOnSpecificLayout(it, binding.pencilImageView)
        }

        binding.arrowLayout.setOnClickListener {
            canvasState = ARROW
            binding.colorSet.visibility = View.GONE
            isColorPaletteVisible = false
            toggleOnSpecificLayout(it, binding.arrowImageView)
        }

        binding.squareLayout.setOnClickListener {
            canvasState = SQUARE
            binding.colorSet.visibility = View.GONE
            isColorPaletteVisible = false
            toggleOnSpecificLayout(it, binding.squareImageView)
        }

        binding.circleLayout.setOnClickListener {
            canvasState = CIRCLE
            binding.colorSet.visibility = View.GONE
            isColorPaletteVisible = false
            toggleOnSpecificLayout(it, binding.circleImageView)
        }
    }

    //assign colours to views
    private fun colorSelector() {
        binding.apply {
            blackColor.setOnClickListener {
                val color = ResourcesCompat.getColor(resources, R.color.black, null)
                mCanvasView.canvasView.setColor(color)
            }
            redColor.setOnClickListener {
                val color = ResourcesCompat.getColor(resources, R.color.red, null)
                mCanvasView.canvasView.setColor(color)
            }
            greenColor.setOnClickListener {
                val color = ResourcesCompat.getColor(resources, R.color.green, null)
                mCanvasView.canvasView.setColor(color)
            }
            blueColor.setOnClickListener {
                val color = ResourcesCompat.getColor(resources, R.color.blue, null)
                mCanvasView.canvasView.setColor(color)
            }
        }
    }

    // add selector background on click
    private fun toggleOffAllLayouts() {

        binding.apply {
            setViewBackgroundColor(pencilLayout, R.color.light_ash)
            setViewBackgroundColor(arrowLayout, R.color.light_ash)
            setViewBackgroundColor(squareLayout, R.color.light_ash)
            setViewBackgroundColor(circleLayout, R.color.light_ash)
            setViewBackgroundColor(colorPaletteLayout, R.color.light_ash)
            pencilImageView.setColorFilter(ContextCompat.getColor(
                this@MainActivity, R.color.icon_default_color))
            arrowImageView.setColorFilter(ContextCompat.getColor(
                this@MainActivity, R.color.icon_default_color))
            squareImageView.setColorFilter(ContextCompat.getColor(
                this@MainActivity, R.color.icon_default_color))
            circleImageView.setColorFilter(ContextCompat.getColor(
                this@MainActivity, R.color.icon_default_color))
            colorPaletteImageView.setColorFilter(ContextCompat.getColor(
                this@MainActivity, R.color.icon_default_color))
        }
    }

    private fun setViewBackgroundColor(view: View, backgroundColor: Int) {
        val drawable: GradientDrawable = view.background as GradientDrawable
        drawable.setColor( ContextCompat.getColor(this@MainActivity,
            backgroundColor))
    }

    private fun toggleOnSpecificLayout(backgroundView: View, imageView: ImageView) {
        toggleViewBackground(object : ViewCallback {
            override fun toggleBackground() {
                toggleOffAllLayouts()
            }
        })
        imageView.setColorFilter(ContextCompat.getColor(
            this@MainActivity, R.color.black))
        setViewBackgroundColor(backgroundView, R.color.dark_ash)
    }


    private fun toggleViewBackground(viewCallback: ViewCallback) {
        viewCallback.toggleBackground()
    }
}

interface ViewCallback {
    fun toggleBackground()
}