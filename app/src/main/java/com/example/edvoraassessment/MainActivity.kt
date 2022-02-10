package com.example.edvoraassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.edvoraassessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var list: List<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        colorSelector()
        setUpViewElement()

    }

    //image view elements
    private fun setUpViewElement() {
        binding.apply {
            list = listOf(
                pencilImageView,
                arrowImageView,
                squareImageView,
                circleImageView,
                colorPaletteImageView
            )
        }

        binding.colorPaletteImageView.setOnClickListener {
            canvasState = COLOR
            buttonPressed(it)
            binding.colorSet.visibility = View.VISIBLE
        }

        binding.pencilImageView.setOnClickListener {
            canvasState = PENCIL
            buttonPressed(it)
        }

        binding.arrowImageView.setOnClickListener {
            canvasState = ARROW
            buttonPressed(it)
        }

        binding.squareImageView.setOnClickListener {
            canvasState = SQUARE
            buttonPressed(it)
        }

        binding.circleImageView.setOnClickListener {
            canvasState = CIRCLE
            buttonPressed(it)
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
    private fun buttonPressed(view: View) {
        clearSelectedState()
        view.isSelected = true
        view.setBackgroundResource(R.drawable.icon_selected_background)
    }

    //clear selection
    private fun clearSelectedState() {
        list.forEach {
            it.isSelected = false
            it.setBackgroundResource(R.drawable.icon_unselected_background)
            binding.colorSet.visibility = View.GONE
            it.colorFilter = null
        }
    }
}