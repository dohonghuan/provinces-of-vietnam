package com.example.provincesofvietnam.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView,
              imgCode: Int?){
    val imgUrl = "https://raw.githubusercontent.com/dohonghuan/63-provinces/main/images/${imgCode}.jpg"
    Glide.with(imgView.context).load(imgUrl).into(imgView)
}