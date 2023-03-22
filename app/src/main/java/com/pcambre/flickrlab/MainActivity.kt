package com.pcambre.flickrlab

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme
import com.pcambre.flickrlab.ui.home.MainScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrLabTheme {
                MainScreen()
            }
        }
    }
}