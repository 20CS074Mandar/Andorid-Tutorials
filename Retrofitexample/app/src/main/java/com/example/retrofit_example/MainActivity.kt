package com.example.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_example.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException
private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //now if we use mvvm architecture hen we would make the retrofit call in the repository and then call that from viewmodel
        setUpRecyclerview()
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible=true
            val response=try{
                RestrofitInstance.api.getTodps()
            }
            catch (e:IOException){
                Log.e(TAG,"IOException, you might not have internet connection")
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HTTP EXception")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                todoAdapter.todos=response.body()!!
            }else{
                Log.e(TAG,"Response not Successfull")
            }
            binding.progressBar.isVisible=false
        }

    }
    private fun setUpRecyclerview()=binding.rcTodos.apply {
        todoAdapter= TodoAdapter()
        adapter=todoAdapter
        layoutManager=LinearLayoutManager(this@MainActivity,)
    }
}