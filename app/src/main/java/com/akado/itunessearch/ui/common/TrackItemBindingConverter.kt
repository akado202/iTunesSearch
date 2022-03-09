package com.akado.itunessearch.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.bumptech.glide.Glide

object TrackItemBindingConverter {

    @BindingAdapter("trackItems")
    @JvmStatic
    fun setTrackItems(
        recyclerView: RecyclerView,
        items: List<TrackItemDomainModel>?
    ) {
        val adapter = getOrCreateAdapter(recyclerView)
        adapter.updateItems(items ?: listOf())
    }

    @BindingAdapter("onTrackItemClick")
    @JvmStatic
    fun setTrackItemClick(
        recyclerView: RecyclerView,
        listener: TrackItemAdapter.OnItemClickListener?
    ) {
        val adapter = getOrCreateAdapter(recyclerView)
        adapter.setOnItemClickListener(listener)
    }

    private fun getOrCreateAdapter(recyclerView: RecyclerView): TrackItemAdapter {
        return if (recyclerView.adapter != null
            && recyclerView.adapter is TrackItemAdapter
        ) {
            recyclerView.adapter as TrackItemAdapter
        } else {
            val adapter = TrackItemAdapter()
            recyclerView.adapter = adapter
            adapter
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