package com.example.coroutine_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class JobWaitingCancelActivitiy : AppCompatActivity() {
    val TAG="JWCAcitivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_waiting_cancel_activitiy)

        //whenever we launch a coroutine it acctually returns a job
        val job= GlobalScope.launch(Dispatchers.Default){
            Log.d(TAG,"Starting First Job")
            repeat(5){
                Log.d(TAG,"Courouting is still working ")
                delay(1000L)
            }
            Log.d(TAG,"First Job Ended")
        }

        val job2= GlobalScope.launch(Dispatchers.Default){
            Log.d(TAG,"Second Job Started")
            repeat(5){
                Log.d(TAG,"Courouting is still working ")
                delay(1000L)
            }
            Log.d(TAG,"Second job Ended")
        }
        runBlocking {
            //now what this job will do is it will block our thread until the coroutine (JOB) is finished
            job.join()
            Log.d(TAG,"Main thread is continuing")
        }
        runBlocking {
            delay(2000L)
            job2.cancel()
        }

        GlobalScope.async(Dispatchers.IO){
            val time= measureTimeMillis {
                val answer1=async { networkCall(1) }
                val answer2=async { networkCall(2) }
                Log.d(TAG,"Answer 1 is ${answer1.await()}")
                Log.d(TAG,"Answer 2 is ${answer2.await()}")
            }
            Log.d(TAG,"Request tooke time ${time}")
        }

    }

    suspend fun networkCall(a:Int):String{
        delay(3000L)
        return "Answer ${a}";
    }
}