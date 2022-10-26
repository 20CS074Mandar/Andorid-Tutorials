package com.example.retrofit_example

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface todoapi {
    @GET("/todos")
    /* for query
    fun getTodos(@Query("key") key:String): Response<List<Todo>>*/

    //we want to execute our request in coroutine so request networks always has to happen asynchronously and never on main thread
    //so what we have to do is use the suspend keyword
    suspend  fun getTodps():Response<List<Todo>>

}