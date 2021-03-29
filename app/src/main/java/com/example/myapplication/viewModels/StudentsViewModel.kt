package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.repositories.StudentsRepository
import com.example.myapplication.repositories.models.Student

class StudentsViewModel : ViewModel() {
    var isLoading: LiveData<Boolean>
    val students: LiveData<MutableList<Student>>
    private var _isLoading: MutableLiveData<Boolean>
    private lateinit var studentsRepository: StudentsRepository

    init {
        _isLoading = MutableLiveData<Boolean>().apply { value = false }
        isLoading = _isLoading

        //initialize students repository

        val students = MediatorLiveData<MutableList<Student>>().apply { value = mutableListOf() }
        this.students = students
    }

    fun onStart(isInitial: Boolean) {
        if (isInitial) {
            loadStudents()
        }
    }

    fun loadStudents() {
        _isLoading.value = true

        studentsRepository.load {
            this.students.value?.addAll(studentsRepository.results.value ?: return@load)
            _isLoading.value = false
        }
    }

}