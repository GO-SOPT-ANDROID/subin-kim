package org.android.go.sopt

import org.android.go.sopt.data.*

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.MultiData
import org.android.go.sopt.databinding.ItemGithubRepoBinding
import org.android.go.sopt.databinding.ItemHomeTitleBinding

class MultiviewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var datas = mutableListOf<MultiData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            MULTI_TYPE1 -> {
                val binding =
                    ItemHomeTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                MultiViewHolder1(binding)
            }
            else -> {
                val binding = ItemGithubRepoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )

                MultiViewHolder2(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (datas[position].type) {
            MULTI_TYPE1 -> {
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

    fun setData(data: MutableList<MultiData>) {
        this.datas = data
        notifyDataSetChanged()
    }

    class MultiViewHolder1(private val binding: ItemHomeTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MultiData) {
            binding.tvHomeTitle.text = item.title
        }
    }

    class MultiViewHolder2(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MultiData) {
            /*binding.tvItemGithubRepo.text = item.dataInfo.email
            binding.tvItemGithubAuthor.text = item.first_name + " " + item.last_name
            Glide.with(itemView).load(item.avatar).into(binding.ivItemGithubRepo)*/
            val dataInfo = item.dataInfo ?: return // dataInfo가 null인 경우 return

            binding.tvItemGithubRepo.text = dataInfo.firstOrNull()?.email ?: ""
            binding.tvItemGithubAuthor.text = dataInfo.joinToString(separator = "\n") { "${it.first_name} ${it.last_name}" }
            Glide.with(itemView).load(dataInfo.firstOrNull()?.avatar ?: "").into(binding.ivItemGithubRepo)
        }
    }
}


