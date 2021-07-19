package ru.rentateam.testproject.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.rentateam.testproject.R
import java.lang.Exception

@BindingAdapter("app:imageUrl", "progressBar" , requireAll = false)
fun loadImage(view: ImageView, imageUrl: String, progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
    Picasso.get()
        .load(imageUrl)
        .into(view, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                TODO("Not yet implemented")
            }
        });
}

@BindingAdapter("app:refreshing")
fun setRefreshing(view: SwipeRefreshLayout, refreshing: Boolean) {
    view.isRefreshing = refreshing
}

@BindingAdapter("app:onRefresh")
fun onRefresh(view: SwipeRefreshLayout, onClick: () -> Unit) {
    view.setOnRefreshListener {
        onClick()
    }
}
