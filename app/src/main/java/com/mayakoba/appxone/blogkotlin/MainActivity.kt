package com.mayakoba.appxone.blogkotlin

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayakoba.appxone.blogkotlin.model.Blog
import com.mayakoba.appxone.blogkotlin.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel? = null
    var mBlogAdapter: BlogAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getPopularBlog()
        swiperefresh.setOnRefreshListener { getPopularBlog() }

    }

    fun getPopularBlog(){
        swiperefresh.setRefreshing(false)
        mainViewModel!!.allBlog.observe(this, Observer {
                blogList -> prepareRecyclerView(blogList)

        })
    }

    private fun prepareRecyclerView(blogList: List<Blog>?) {

        mBlogAdapter = BlogAdapter(blogList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            blogRecyclerView.setLayoutManager(LinearLayoutManager(this))
        } else {
            blogRecyclerView.setLayoutManager(GridLayoutManager(this, 4))
        }
        blogRecyclerView.setItemAnimator(DefaultItemAnimator())
        blogRecyclerView.setAdapter(mBlogAdapter)

    }
}
