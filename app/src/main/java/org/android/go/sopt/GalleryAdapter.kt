package org.android.go.sopt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerBinding

class GalleryAdapter(
    _itemList: List<Int> = listOf(),
): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    private var itemList: List<Int> = _itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemPagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GalleryViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    class GalleryViewHolder(
        private val binding: ItemPagerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(src: Int) {
            binding.imgGallery.setImageResource(src)
        }
    }

    fun setItemList(itemList: List<Int>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

}