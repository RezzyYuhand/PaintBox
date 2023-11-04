package com.example.paintbox
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class paintings(
    val name: String,
    val description: String,
    val maker: String,
    val timeCreated: String,
    val photo: String
) : Parcelable
