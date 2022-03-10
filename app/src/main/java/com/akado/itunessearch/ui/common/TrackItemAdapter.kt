package com.akado.itunessearch.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akado.itunessearch.R
import com.akado.itunessearch.databinding.ViewTrackItemBinding
import com.akado.itunessearch.domain.model.TrackItemDomainModel

class TrackItemAdapter(
    private var onItemClickListener: OnItemClickListener? = null,
    private var onItemFavoriteCallback: OnItemFavoriteCallback? = null
) : RecyclerView.Adapter<TrackItemAdapter.ViewHolder>() {

    private var items: MutableList<TrackItemDomainModel> = mutableListOf()
    private var favoriteStates: List<Boolean> = listOf()

    interface OnItemClickListener {
        fun onItemClick(model: TrackItemDomainModel)
    }

    interface OnItemFavoriteCallback {
        fun isFavorite(model: TrackItemDomainModel): Boolean
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.onItemClickListener = listener
    }

    fun setOnItemFavoriteCallback(listener: OnItemFavoriteCallback?) {
        this.onItemFavoriteCallback = listener
    }

    fun updateItems(items: List<TrackItemDomainModel>) {
        val newFavoriteStates: MutableList<Boolean> = mutableListOf()
        items.forEach { newFavoriteStates.add(onItemFavoriteCallback?.isFavorite(it) == true) }

        val diffCallback = DiffUtilCallback(this.items, items, this.favoriteStates, newFavoriteStates)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items.run {
            clear()
            addAll(items)
        }
        this.favoriteStates = newFavoriteStates;
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
        holder.binding.ivFavorite.setImageResource(
            if (onItemFavoriteCallback?.isFavorite(model) == true) R.drawable.ic_star
            else R.drawable.ic_star_outline
        )
    }

    class ViewHolder(val binding: ViewTrackItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: TrackItemDomainModel) {
            binding.viewModel = model
        }
    }

    class DiffUtilCallback(
        private val oldList: List<TrackItemDomainModel>,
        private val newList: List<TrackItemDomainModel>,

        private val oldFavoriteStates: List<Boolean>,
        private val newFavoriteStates: List<Boolean>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            val oldState = oldFavoriteStates[oldItemPosition]
            val newState = newFavoriteStates[newItemPosition]

            return oldItem.artistId == newItem.artistId
                    && oldItem.collectionId == newItem.collectionId
                    && oldItem.trackId == newItem.trackId
                    && oldItem.artistName == newItem.artistName
                    && oldItem.collectionName == newItem.collectionName
                    && oldItem.trackName == newItem.trackName
                    && oldItem.artworkUrl60 == newItem.artworkUrl60
                    && oldState == newState
        }
    }
}