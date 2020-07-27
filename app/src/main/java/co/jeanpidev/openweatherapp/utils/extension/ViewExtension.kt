package co.jeanpidev.openweatherapp.utils.extension

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import co.jeanpidev.openweatherapp.application.App
import co.jeanpidev.openweatherapp.utils.IMAGES_URL
import co.jeanpidev.openweatherapp.utils.IMAGE_EXTENSION
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

val Activity.app: App
    get() = application as App

fun ImageView.setImage(imageUrl: String?) {
    getParentActivity()?.let {
        imageUrl?.let { url ->
            val glideUrl = IMAGES_URL + url + IMAGE_EXTENSION
            Glide.with(it.baseContext).load(glideUrl)
                .transition(withCrossFade())
                .fitCenter()
                .thumbnail(0.5f)
                .into(this)
        }
    }
}
