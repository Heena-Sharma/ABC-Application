package com.abc.app.adapters

import android.widget.ImageView
import coil.load
import com.abc.app.R

fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        view.load(it) {
            placeholder(R.drawable.placeholder)
        }
    }
}

