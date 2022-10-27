package com.example.coroutine_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RunBlockingActivity : AppCompatActivity() {
    val TAG="RunBlockingActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_blocking)

        //Even if we call delay it won't acctually block the thread it is running in
        //however there is a function that will start a routine in the main thread and also block it which is called run blocking

        //The difference to global scope and runblocking is that run blocking will block the main thread but the displacher in globalscope.launch

        Log.d(TAG,"Before Runblocking")
        runBlocking {
            // the run runblocking won't affect the couroutine it will only affect the main thread
            launch (Dispatchers.IO){
                delay(3000L)
                Log.d(TAG,"Finished IOCoroutine 1")

            }
            launch (Dispatchers.IO){
                delay(3000L)
                Log.d(TAG,"Finished IOCoroutine 2")

            }
            Log.d(TAG,"Start Runblocking")
            delay(5000L)
            Log.d(TAG,"End Runblocking")
        }
        Log.d(TAG,"After Runblocking")
    }
}