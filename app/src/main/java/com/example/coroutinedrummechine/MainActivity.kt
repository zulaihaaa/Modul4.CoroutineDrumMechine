package com.example.coroutinedrummechine

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity() {
    private lateinit var cymbal: MediaPlayer
    private lateinit var tom: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cymbal = MediaPlayer.create(this, R.raw.ring1)
        tom = MediaPlayer.create(this, R.raw.ring2)
        btnStart.setOnClickListener {
            runBlocking {
                launch { playBeats("x-x-x-x-x-x-x-x-x-x-x-x-", R.raw.ring2)
                }
                playBeats("x-----x-----x-----x-----", R.raw.ring1)
            }
        }
    }
    suspend fun playBeats(beats: String, fileId: Int){
        val parts = beats.split("x")
        var count = 0
        for(part in parts){
            count += part.length + 1
            if(part == ""){
                if(fileId == R.raw.ring1)
                    cymbal.start()
                else
                    tom.start()
            }else{
                delay(1000 * (part.length + 1L))
                if(count < beats.length){
                    if(fileId == R.raw.ring1)
                        cymbal.start()
                    else
                        tom.start()
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        cymbal.stop()
        tom.stop()
    }
}

