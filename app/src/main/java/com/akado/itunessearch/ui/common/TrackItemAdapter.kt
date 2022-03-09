package com.akado.itunessearch.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akado.itunessearch.databinding.ViewTrackItemBinding
import com.akado.itunessearch.domain.model.TrackItemDomainModel

class TrackItemAdapter(
    private var onItemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<TrackItemAdapter.ViewHolder>() {

    private var items: List<TrackItemDomainModel> = listOf()

    interface OnItemClickListener {
        fun onItemClick(model: TrackItemDomainModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.onItemClickListener = listener
    }

    fun updateItems(items: List<TrackItemDomainModel>) {
        this.items = items

        // TODO: diffUtil code here
        notifyDataSetChanged()
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
        val model = items[position]

        holder.bind(model)
        holder.binding.ivFavorite.setOnClickListener { onItemClickListener?.onItemClick(model) }
    }

    class ViewHolder(val binding: ViewTrackItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: TrackItemDomainModel) {
            binding.viewModel = model
        }
    }
}