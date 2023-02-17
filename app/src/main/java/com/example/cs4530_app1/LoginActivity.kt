package com.example.cs4530_app1

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cs4530_app1.databinding.ActivityLoginBinding

class LoginActivity : FragmentActivity() {

    private var loginInfo: String = " is logged in!"
    private var image: Bitmap? = null

    private var tvLoginText: TextView? = null
    private var ivPic: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvLoginText = findViewById(R.id.tv_loginText)
        ivPic = findViewById(R.id.iv_picture)

        val inIntent = intent

        val fname: String? = intent.getStringExtra("fname")
        val lname: String? = intent.getStringExtra("lname")
        val inPic: Bitmap? = inIntent.getParcelableExtra("image")

        val finalText = "$fname $lname$loginInfo"
        image = inPic

        tvLoginText!!.setText(finalText)
        ivPic!!.setImageBitmap(image)
    }
}