package com.example.coroutine_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

class SuspendFunctionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend_functions)
        val tvSuspendFunction:TextView=findViewById(R.id.tvSuspendFunction)
        //now if i call doNetworkCall  here outside of an coroutine we will get error as we are not declaring a suspend function inside a sudspend function
        //doNetworkCall()
        lifecycleScope.launchWhenCreated {
            delay(200L)//the arrow on the right side shows that this is a suspendfunction
            tvSuspendFunction.text="The special thing about suspend function is that they can only be executed within another suspend function or iniside of an coroutine."
            tvSuspendFunction.text=doNetworkCall()
        }
    }
    suspend fun doNetworkCall():String
    {
        delay(3000)
        return "This is A suspend Function "
    }
}