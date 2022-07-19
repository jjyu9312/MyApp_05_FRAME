package com.kkuber.myapp_05_frame

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity: AppCompatActivity() {

    private val photoList = mutableListOf<Uri>()

    private var currentPosition = 0

    private var timer: Timer? = null

    private val photoImageView: ImageView by lazy {
        findViewById<ImageView>(R.id.PhotoImageView)
    }

    private val backgroundPhotoImageView: ImageView by lazy {
        findViewById<ImageView>(R.id.backgroundPhotoImageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photoframe)

        getPhotoUriFromIntent()
        startTimer()

    }

    private fun getPhotoUriFromIntent() {
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0..size) {
            intent.getStringExtra("photo_$i")?.let { // null이면 실행하지 않게 하기 위해 let 사용
                photoList.add(Uri.parse(it)) //Uri로 변환하여 리스트에 저장
            }
        }
    }

    private fun startTimer() {
        timer(period = 5 * 1000) {

            // 메인스레드로 변환
            runOnUiThread {

                val current = currentPosition
                val next    = if (photoList.size <= currentPosition + 1) 0 else currentPosition + 1

                backgroundPhotoImageView.setImageURI(photoList[current])
                photoImageView.alpha = 0f
                photoImageView.setImageURI(photoList[next])
                photoImageView.animate().alpha(1.0f).setDuration(1000).start()

                currentPosition = next
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("PhotoFrame", "onStop : timer cancel");
        timer?.cancel()
    }

    override fun onStart() {
        super.onStart()
        Log.d("PhotoFrame", "onStart : timer start");
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PhotoFrame", "onDestroy : timer cancel");
        timer?.cancel()
    }
}