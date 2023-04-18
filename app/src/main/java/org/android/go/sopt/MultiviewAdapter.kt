package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.MultiData
import org.android.go.sopt.data.multi_type1
import org.android.go.sopt.databinding.ItemGithubRepoBinding
import org.android.go.sopt.databinding.ItemHomeTitleBinding

class MultiviewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var datas = mutableListOf<MultiData>()
    //private val binding1 by lazy { ItemHomeTitleBinding.inflate(layoutInflater) }
    //private val binding2 by lazy { ItemGithubRepoBinding.inflate(layoutInflater) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val view : View?
        return when (viewType) {
            multi_type1 -> {
                val binding =
                    ItemHomeTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                MultiViewHolder1(binding)
            }
            else -> {
                val binding = ItemGithubRepoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                MultiViewHolder2(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (datas[position].type) {
            multi_type1 -> {
                (holder as MultiViewHolder1).bind(datas[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as MultiViewHolder2).bind(datas[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun getItemViewType(position: Int): Int {
        return datas[position].type
    }

    inner class MultiViewHolder1(private val binding: ItemHomeTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MultiData) {
            binding.tvHomeTitle.text = item.title
        }
    }

    inner class MultiViewHolder2(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MultiData) {
            binding.tvItemGithubRepo.text = item.repo
            binding.tvItemGithubAuthor.text = item.name
            //Glide.with(context).load(item.image).into(binding2.ivItemGithubRepo)
            Glide.with(itemView).load(item.image).into(binding.ivItemGithubRepo)
        }
    }
}


