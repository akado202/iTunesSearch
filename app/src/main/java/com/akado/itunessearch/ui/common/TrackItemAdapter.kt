package com.akado.itunessearch.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akado.itunessearch.databinding.ViewTrackItemBinding
import com.akado.itunessearch.domain.model.TrackItemDomainModel
import com.bumptech.glide.Glide

class TrackItemAdapter : RecyclerView.Adapter<TrackItemAdapter.ViewHolder>() {

    private var items: List<TrackItemDomainModel> = listOf()

    fun updateItems(items: List<TrackItemDomainModel>) {
        // TODO: diffUtil code here

        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewTrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val binding: ViewTrackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TrackItemDomainModel) {
            binding.viewModel = model
        }
    }
}

object RestaurantBindingConverter {

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