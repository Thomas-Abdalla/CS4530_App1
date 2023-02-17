package com.example.cs4530_app1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.util.BitSet

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //data variables
    private var firstName: String? = null
    private var midName: String? = null
    private var lastName: String? = null
    private var image: Bitmap? = null
    //ui variables
    private var tvFirstName: TextView? = null
    private var tvMidName: TextView? = null
    private var tvLastName: TextView? = null
    private var ivPhoto: ImageView? = null
    private var bPhoto: Button? = null
    private var bSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Connect .xml to .kt
        tvFirstName = findViewById(R.id.et_firstname)
        tvMidName = findViewById(R.id.et_middlename)
        tvLastName = findViewById(R.id.et_lastname)
        ivPhoto = findViewById(R.id.iv_photo)
        bPhoto = findViewById(R.id.bt_photo)
        bSubmit = findViewById(R.id.bt_submit)

        bPhoto!!.setOnClickListener(this)
        bSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.bt_photo -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try{
                    cameraActivity.launch(cameraIntent)
                } catch (ex: ActivityNotFoundException){
                }
            }

            R.id.bt_submit -> {
                firstName = tvFirstName!!.toString()
                midName = tvMidName!!.toString()
                lastName = tvLastName!!.toString()

                if (firstName == null || midName == null || lastName == null || image == null){
                    Toast.makeText(
                        this@MainActivity,
                        "Please provide data for all text boxes and take a profile picture.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    val loginIntent = Intent(this, LoginActivity::class.java)
                    val loginIntentBundle = Bundle()
                    loginIntentBundle.putString("fname", firstName!!.toString())
                    loginIntentBundle.putString("mname", midName!!.toString())
                    loginIntentBundle.putString("lname", lastName!!.toString())
                    loginIntentBundle.putParcelable("image", image)
                    loginIntent.putExtras(loginIntentBundle);
                    startActivity(loginIntent)
                }


            }
        }
    }

    private val cameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == RESULT_OK){
            ivPhoto = findViewById<View>(R.id.iv_photo) as ImageView
            image = result.data!!.getParcelableExtra<Bitmap>("data")
            ivPhoto!!.setImageBitmap(image)
        }
    }
}