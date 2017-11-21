package com.example.android.myapplication

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager


class RecordAudiodActivity : AppCompatActivity() {
    private val TAG = RecordAudiodActivity::class.java.simpleName
    private val REQUEST_RECORD_AUDIO_PERMISSION = 200
    private var mRecorder: MediaRecorder? = null

    lateinit var outputFile: String

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> permissionToRecordAccepted =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (!permissionToRecordAccepted) finish()
    }

    fun record(view: View) {
        recordButton.isEnabled = false
        playButton.isEnabled = false
        stopButton.isEnabled = true

        mRecorder = MediaRecorder()
        mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mRecorder!!.setOutputFile(outputFile)
        mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        try {
            mRecorder!!.prepare()
        } catch (e: IOException) {
            Log.e(TAG, "prepare() failed")
        }

        mRecorder!!.start()

        Toast.makeText(this, "Record Start", Toast.LENGTH_SHORT).show()
    }

    fun stop(view: View) {
        mRecorder!!.stop()
        mRecorder!!.release()
        mRecorder = null

        recordButton.isEnabled = true
        playButton.isEnabled = true
        stopButton.isEnabled = false

        Toast.makeText(this, "Record Stop", Toast.LENGTH_SHORT).show()
    }

    fun play(view: View) {
        val mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(outputFile)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IllegalStateException) {
            Log.e(TAG,"IllegalStateException: $e")
        } catch (e: IOException) {
            Log.e(TAG,"IllegalStateException: $e")
        }

        Toast.makeText(this, "Playing Audio", Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopButton.isEnabled = false
        playButton.isEnabled = false

        outputFile = externalCacheDir!!.absolutePath + "/recording.3gp"

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

}
