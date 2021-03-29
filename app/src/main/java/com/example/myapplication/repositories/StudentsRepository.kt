package com.example.myapplication.repositories

import androidx.lifecycle.LiveData
import com.example.myapplication.repositories.models.Student

interface StudentsRepository {
    val results: LiveData<List<Student>>
    fun load(completion: (Boolean) -> Unit)
}