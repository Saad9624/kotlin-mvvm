package com.mayakoba.appxone.blogkotlin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mayakoba.appxone.blogkotlin.model.Blog
import com.mayakoba.appxone.blogkotlin.networking.RestApiService
import kotlinx.coroutines.*
import retrofit2.HttpException

class BlogRepository {

    private var movies  = mutableListOf<Blog>()

    private var mutableLiveData = MutableLiveData<List<Blog>>()
    val completableJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        RestApiService.createCorService()
    }

    fun getMutableLiveData():MutableLiveData<List<Blog>>{

        coroutineScope.launch {

            val request  = thisApiCorService.getPopularBlog()
            withContext(Dispatchers.Main){
                try{
                        val response = request.await()
                    Log.e("responses" , response.toString())
                        val mBlogWrapper = response
                    Log.e("responses" , response.toString())

                    if(mBlogWrapper !=  null &&  mBlogWrapper.blog != null){
                        movies = mBlogWrapper.blog as MutableList<Blog>
                        mutableLiveData.value = movies

                    }

                }
                catch (e: HttpException){

                }
            }
        }

        return mutableLiveData
    }

}