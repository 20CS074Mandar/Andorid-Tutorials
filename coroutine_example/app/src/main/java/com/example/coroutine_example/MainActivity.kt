package com.example.coroutine_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
     val TAG="MainActivity"
     lateinit var btnStartActivity:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(3000L)
            Log.d(TAG,"Hello by coroutine from thread ${Thread.currentThread().name}")
        }
        Log.d(TAG,"Hello from thread ${Thread.currentThread().name}")
        btnStartActivity=findViewById(R.id.btnStartActivity)
        
        /*If the coroutine started in main activity uses resources of main activity which will be destroyed in the
        * second global scope. Even the mainActivity is destroyed its resources won't bee garbage collected even though the
        * activity is destroyed ,because coroutine still uses those resources
        * and to solve this proble we use lifecycleScope */
        btnStartActivity.setOnClickListener {
            //GlobalScope.launch
            lifecycleScope.launch{
                while (true){
                    delay(1000L)
                    Log.d(TAG,"Still Running")
                }
            }
            //GlobalScope.launch
            lifecycleScope.launch{
                delay(5000L)
                Intent(this@MainActivity,second_activity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

    }
}