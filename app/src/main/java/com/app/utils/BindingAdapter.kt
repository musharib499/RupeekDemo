package com.app.utils

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.R
import com.app.data.api.LoadingState
import com.app.ui.base.BaseAdapterBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */

@BindingAdapter("isVisible")
fun View.setIsVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("isEmpty")
fun View.setEmptyText(message: String?) {
    this.visibility = if (TextUtils.isEmpty(message)) View.GONE else View.VISIBLE
}

@BindingAdapter("entity")
fun RecyclerView.setEntity(data: List<Any>?) {
    data?.let { (this.adapter as BaseAdapterBinding<Any>).setData(data) }

}
@BindingAdapter("dateTimeStamp")
fun AppCompatTextView.setDateTimeStamp(time: Long?) {
    var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
     this.text = simpleDateFormat.format(time)

}



@BindingAdapter("thumbnail")
fun ImageView.setImage(url: String?) {
    Glide.with(this.context)
            .load(url ?: "")
            .optionalCenterCrop()
            .placeholder(R.drawable.placeholder)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(this);

}

@BindingAdapter("isLoading")
fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
    loadingState?.let {
        isVisible = when (it.status) {
            LoadingState.Status.RUNNING -> true
            LoadingState.Status.SUCCESS -> false
            LoadingState.Status.FAILED -> false
        }
    }
}



