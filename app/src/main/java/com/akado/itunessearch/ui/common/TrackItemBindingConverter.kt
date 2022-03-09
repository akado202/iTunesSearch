package com.akado.itunessearch.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.bumptech.glide.Glide

object TrackItemBindingConverter {
    @BindingAdapter("trackItemAdapter")
    @JvmStatic
    fun setTrackItemAdapter(
        recyclerView: RecyclerView,
        set: Boolean
    ) {
        recyclerView.adapter = if (set) TrackItemAdapter() else null
    }

    @BindingAdapter("trackItems")
    @JvmStatic
    fun setTrackItems(
        recyclerView: RecyclerView,
        items: List<TrackItemDomainModel>?
    ) {
        val adapter = recyclerView.adapter as TrackItemAdapter
        adapter.let {
            it.updateItems(items ?: listOf())

            // TODO: diffUtil 로 code 교체
            it.notifyDataSetChanged()
        }
    }

    @BindingAdapter("trackItemThumbnail")
    @JvmStatic
    fun setTrackItemThumbnail(imageView: ImageView, url: String) {
        Glide.with(imageView)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}