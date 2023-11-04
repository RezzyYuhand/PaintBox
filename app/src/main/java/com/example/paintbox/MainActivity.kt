package com.example.paintbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paintbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<paintings>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.paintingsRv.setHasFixedSize(true)

        list.addAll(getListPaintings())
        showRecyclerList()
    }

    private fun showSelectedPainting(paintings: paintings){
        Toast.makeText(this, "lukisan ini dibuat tahun " + paintings.timeCreated, Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        binding.paintingsRv.layoutManager = LinearLayoutManager(this)
        val paintingListAdapter = PaintingListAdapter(list)
        binding.paintingsRv.adapter = paintingListAdapter

        paintingListAdapter.setOnItemClickCallback(object : PaintingListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: paintings) {
                showSelectedPainting(data)
            }
        })
    }

    private fun getListPaintings(): ArrayList<paintings> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataMaker = resources.getStringArray(R.array.data_maker)
        val dataTimeCreated = resources.getStringArray(R.array.data_year)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listPainting = ArrayList<paintings>()
        for (i in dataName.indices) {
            val painting = paintings(dataName[i], dataDescription[i], dataMaker[i], dataTimeCreated[i], dataPhoto[i])
            listPainting.add(painting)
        }
        return listPainting
    }


}