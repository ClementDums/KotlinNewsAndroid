package com.clt.dumas.clem.dmii

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.clt.dumas.clem.dmii.Fragments.ChoiceFragment
import com.clt.dumas.clem.dmii.Fragments.LocationFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val choiceFragment = ChoiceFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.choice_fragment_container, choiceFragment)
            //ajouter la transaction dans la stack
            addToBackStack(null)
        }.commit()

    }




    companion object{
        const val PERMISSION_CODE = 0
    }
//    private fun Double.format()= String().format("%.2f")
}
