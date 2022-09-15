package com.vagrok.vag_collar.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.vagrok.vag_collar.R
import kotlinx.coroutines.runInterruptible

class CowScannerActivity : AppCompatActivity() {
    private lateinit var codeScanner : CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cow_scanner)
        //logic scanner
        if(ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED )
        {
            ActivityCompat.requestPermissions( this , arrayOf(Manifest.permission.CAMERA) , 123)
        }else{
            startScanning()
        }

    }

    private fun startScanning() {
        val scannerView : CodeScannerView = findViewById(R.id.view_scanner)
        codeScanner = CodeScanner(this , scannerView)
        //region scanner settings
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = true //false

        //endregion

        //region callback scanner resultat
        codeScanner.decodeCallback = DecodeCallback{
            runOnUiThread {
                Toast.makeText(this , "decoding this ${it.text}" , Toast.LENGTH_LONG).show()
                val intent = Intent(this , NewCowActivity::class.java)
                startActivity(intent)
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this , "decoding this ${it.message}" , Toast.LENGTH_LONG).show()
            }
        }
        //endregion

        //region Scanner onclick listener
        scannerView.setOnClickListener(View.OnClickListener {
           codeScanner.startPreview()
       })
        //endregion
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ( requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this , "CAMERA PERMISSION GRANTED" , Toast.LENGTH_SHORT).show()
                startScanning()
            }else{
                Toast.makeText(this , "CAMERA PERMISSION DENIED" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized){
            codeScanner?.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized){
            codeScanner?.releaseResources()
        }
        super.onPause()

    }
}