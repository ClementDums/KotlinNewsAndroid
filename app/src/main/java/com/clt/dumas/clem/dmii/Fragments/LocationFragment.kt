package com.clt.dumas.clem.dmii.Fragments

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.clt.dumas.clem.dmii.MainActivity
import com.clt.dumas.clem.dmii.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.location_fragment.*

class LocationFragment:Fragment(){
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity ?: return

        //J'ai la permission ?
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Il a deja dit non ?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {

                val builder: AlertDialog.Builder? = activity.let {
                    AlertDialog.Builder(it)
                }
                val builder2 = builder ?: return
                builder2.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)

                builder2.apply {
                    setPositiveButton(R.string.ok,
                        DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                            askLocation(activity)
                        })
                    setNegativeButton(R.string.cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            errorPermission()
                        })
                }

                val dialog: AlertDialog? = builder2.create()
                dialog?.show()

            } else {
                askLocation(activity)
            }
        } else {
            //La permission est accordée
            //val locationManager = LocationManager(LocationManager)
            displayLocation(activity)
        }

    }

    fun askLocation(activity:FragmentActivity){
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            MainActivity.PERMISSION_CODE
        )
    }




    fun displayLocation(activity: FragmentActivity){
        print("LOCAAAATIOONNNN")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                println(location)
                textView.text = location.toString()
            }
    }

    fun errorPermission(){
        textView.text = "Désolez vous n'avez pas la permission"
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MainActivity.PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    activity?.let {
                        displayLocation(it)
                    }
                } else {

                }
            }
            else -> {
                // Le code ne concerne pas la permission, on l'ignore
            }
        }
    }
}