package com.funnco.kotlinlearning1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.funnco.kotlinlearning1.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportActionBar?.hide()


        //Create okhttp3 client
        val builder = UnsafeOkHttpClient.getUnsafeOkHttpClient()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://u1382784.plsk.regruhosting.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder)
            .build()

        var api = retrofit.create(API::class.java)



        api.getWeather().enqueue(object : Callback<List<Weather>> {
            override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                Log.i("tset1", t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {
                if (!response.isSuccessful) {
                    Log.i("tset1", response.message())
                } else Log.i("tset1", response.body().toString())
            }

        })

        api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.i("tset2", t.localizedMessage)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (!response.isSuccessful) {
                    Log.i("tset2", response.message())
                } else Log.i("tset2", response.body().toString())
            }

        })

        var user = User("195", System.currentTimeMillis(), "Петя", 13)


        api.addUser(user).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.i("tset3", t.localizedMessage)
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (!response.isSuccessful) {
                    Log.i("tset3", response.code().toString())
                } else Log.i("tset3", response.body().toString())
            }
        })


    }

    fun buildLogPost(post: Weather): String {
        return "Date: ${post.date}\n" +
                "TemperatureC: ${post.temperatureC}\n" +
                "TemperatureF: ${post.temperatureF}\n" +
                "Summary: ${post.summary}\n\n"
    }

}