package com.kagami.animalcrossingwiki.global

import android.util.Base64
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ImageView.loadBase64Image(str:String){
    MainScope().launch {
        GlideApp.with(this@loadBase64Image)
            .load(decodeBase64(str))
            .into(this@loadBase64Image)
    }
}
private suspend fun decodeBase64(str:String) = withContext(Dispatchers.Default){
    Base64.decode(str,Base64.DEFAULT)
}