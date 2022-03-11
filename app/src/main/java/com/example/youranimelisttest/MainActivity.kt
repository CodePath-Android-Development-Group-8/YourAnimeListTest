package com.example.youranimelisttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import org.json.JSONException


private const val TAG = "MainActivity"
private const val SUGGESTED_ANIME_URL = "https://api.myanimelist.net/v2/anime/season/2022/winter?fields=rank,limit=10"
class MainActivity : AppCompatActivity() {

    private val animes = mutableListOf<Anime>()
    private lateinit var rvAnimes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvAnimes = findViewById(R.id.rvAnimes)

        val animeAdapter = AnimeAdapter(this, animes)
        rvAnimes.adapter = animeAdapter
        rvAnimes.layoutManager = LinearLayoutManager(this)

        val headers = RequestHeaders()
        headers["X-MAL-CLIENT-ID"] = "550abb198266c53b8de9c9f81358ccfe"
        val params = RequestParams()
        val client = AsyncHttpClient()

        client.get(SUGGESTED_ANIME_URL, headers, params, object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON data $json")
                try {
                    val seasonalAnime = json.jsonObject.getJSONArray("data")
                    animes.addAll(Anime.fromJsonArray(seasonalAnime))
                    animeAdapter.notifyDataSetChanged()
                    Log.i(TAG, "$animes")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")

                }

            }

        })
    }
}