package com.example.labthread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var counter: Int = 0
    private lateinit var thread: Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()


    }

    private fun init() {
        thread = Thread( Runnable {
            kotlin.run {
                for(i in 1 until 100){
                    counter++
                    try {
                        Thread.sleep(1000)
                    }catch (e: InterruptedException){
                        return@Runnable
                    }
                    mainThread()
                }
            }
        })
        thread.start()
    }

    private fun mainThread() {
        runOnUiThread( Runnable {
            kotlin.run {
                try{

                    tvCounter.text = "$counter"

                }catch (e:InterruptedException){

                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        thread.isInterrupted
    }

}
