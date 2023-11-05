package com.example.paintbox

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.paintbox.databinding.ActivityDetailPaintingBinding


class DetailPaintingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPaintingBinding

    companion object {
        const val EXTRA_PAINTINGS = "extra_paintings"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_painting)

        this.getSupportActionBar()?.hide()

        binding = ActivityDetailPaintingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paintings = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PAINTINGS, paintings::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PAINTINGS)
        }

        if (paintings != null) {
            val judul = paintings.name
            val deskripsi = paintings.description
            val maker = paintings.maker
            val tahun = paintings.timeCreated
            val photo = paintings.photo

            binding.detailTitleTv.text = judul
            binding.detailDescTv.text = deskripsi
            binding.paintTitleInfo.text = judul
            binding.paintCreatorInfo.text = maker
            binding.paintYearInfo.text = tahun
            Glide.with(this)
                .load(photo)
                .into(binding.detailImageView)
        }

        binding.actionShare.setOnClickListener {
            if (paintings != null) {
                val message = "I shared the information i got from PaintBox App\n" +
                        "this is ${paintings.name} created by ${paintings.maker} in ${paintings.timeCreated}\n" +
                        "You can check the image in this link\n" +
                        "${paintings.photo}"

                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.type = "text/plain"

                startActivity(Intent.createChooser(intent, "Share to : "))
            }
        }

    }


}