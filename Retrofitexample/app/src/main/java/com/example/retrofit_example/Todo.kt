package com.example.retrofit_example

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)