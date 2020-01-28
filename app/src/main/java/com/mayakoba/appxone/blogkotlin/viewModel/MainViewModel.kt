package com.mayakoba.appxone.blogkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mayakoba.appxone.blogkotlin.model.Blog

class MainViewModel : ViewModel() {


        val moviewRepositry = BlogRepository()
        val allBlog: LiveData<List<Blog>> get() = moviewRepositry.getMutableLiveData()

        override fun onCleared() {
            super.onCleared()

        moviewRepositry.completableJob.cancel()

        }

}