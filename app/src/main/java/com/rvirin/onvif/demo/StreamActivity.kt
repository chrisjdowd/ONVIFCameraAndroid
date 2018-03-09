package com.rvirin.onvif.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.pedro.vlc.VlcListener
import com.pedro.vlc.VlcVideoLibrary
import com.rvirin.onvif.R

class StreamActivity : AppCompatActivity(), VlcListener, View.OnClickListener {


    private var vlcVideoLibrary: VlcVideoLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stream)

        val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        val bStartStop = findViewById<Button>(R.id.b_start_stop)
        bStartStop.setOnClickListener(this)
        vlcVideoLibrary = VlcVideoLibrary(this, this, surfaceView)
    }

    override fun onComplete() {
        Toast.makeText(this, "Loading video...", Toast.LENGTH_LONG).show()
    }

    override fun onError() {
        Toast.makeText(this, "Error, make sure your endpoint is correct", Toast.LENGTH_SHORT).show()
        vlcVideoLibrary?.stop()
    }


    override fun onClick(v: View?) {

        vlcVideoLibrary?.let { vlcVideoLibrary ->

            if (!vlcVideoLibrary.isPlaying) {
                val url = intent.getStringExtra(RTSP_URL)
                vlcVideoLibrary.play(url)

            } else {
                vlcVideoLibrary.stop()

            }
        }
    }
}

