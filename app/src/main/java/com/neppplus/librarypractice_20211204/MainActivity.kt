package com.neppplus.librarypractice_20211204

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents() {

        btnCall.setOnClickListener {

//            전화 연결 해도 되는지? 권한 확인 -> OK일때만 전화연결.

//            권한 여부에 따른 행동 방침 변수

            val pl = object : PermissionListener {
                override fun onPermissionGranted() {

//                    권한 승인 됬을때 할 행동

                    //            010-5555-6666 에 실제 전화 연결

                    val myUri = Uri.parse("tel:010-5555-6666")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "권한이 없어 전화 연결 불가합니다.", Toast.LENGTH_SHORT)
                        .show()
                }

            }

//            실제 권한 확인 요청
            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions( Manifest.permission.CALL_PHONE )
                .check()


        }

        imgProfile.setOnClickListener {

            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)

        }

    }

    fun setValues() {

    }

}