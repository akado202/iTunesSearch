package com.akado.itunessearch.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

    fun updateItems() {
        updateItems(this.items)
    }

    fun updateItems(items: List<TrackItemDomainModel>) {
        val diffCallback = DiffUtilCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items = items
        diffResult.dispatchUpdatesTo(this)
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

    class DiffUtilCallback(
        private val oldList: List<TrackItemDomainModel>,
        private val newList: List<TrackItemDomainModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return oldItem.artistId == newItem.artistId
                    && oldItem.collectionId == newItem.collectionId
                    && oldItem.trackId == newItem.trackId
                    && oldItem.artistName == newItem.artistName
                    && oldItem.collectionName == newItem.collectionName
                    && oldItem.trackName == newItem.trackName
                    && oldItem.artworkUrl60 == newItem.artworkUrl60
                    && oldItem.isFavorite == newItem.isFavorite
        }
    }
}