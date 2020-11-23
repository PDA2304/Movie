package com.example.movie.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class Top_MovieFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_top__movie, container, false)
        val client = OkHttpClient()
        val request = API.invoke(true)
        var array: ArrayList<Model> = ArrayList()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val json = JSONObject(response.body()!!.string()).getJSONArray("results")
                var i = 0
                array.clear()
                while (json.length() > i) {
                    var item = Model(
                        "Оценка:"+json.getJSONObject(i).getString("vote_average")+"/10",
                        json.getJSONObject(i).getString("title"),
                        "Дата выхода"+json.getJSONObject(i).getString("release_date"),
                        json.getJSONObject(i).getString("poster_path"),
                        json.getJSONObject(i).getString("overview")
                    )
                    array.add(item)
                    i++
                }

                activity!!.runOnUiThread{
                    val recycler = v.findViewById<RecyclerView>(R.id.imageRecyclerView)
                    recycler.layoutManager = LinearLayoutManager(activity)
                    recycler.setHasFixedSize(true)
                    val adapter: Adapter = Adapter(v.context, array,false)
                    {
                        var intent = Intent(context, MainActivity2::class.java)
                        intent.putExtra("OBJECK", it)
                        startActivity(intent)
                    }
                    recycler.adapter = adapter
                }


            }
        })




        return v
    }
}