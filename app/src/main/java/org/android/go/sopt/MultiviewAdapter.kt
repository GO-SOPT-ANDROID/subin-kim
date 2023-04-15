package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.MultiData
import org.android.go.sopt.data.multi_type1
import org.android.go.sopt.databinding.ItemGithubRepoBinding
import org.android.go.sopt.databinding.ItemHomeTitleBinding

class MultiviewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding1: ItemHomeTitleBinding
    lateinit var binding2 : ItemGithubRepoBinding
    var datas = mutableListOf<MultiData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View?
        return when(viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_home_title,
                    parent,
                    false
                )
                MultiViewHolder1(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_home_title,
                    parent,
                    false
                )
                MultiViewHolder2(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(datas[position].type) {
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

    inner class MultiViewHolder1(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item:MultiData) {
            binding1.tvHomeTitle.text = item.title
        }
    }

    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item:MultiData) {
            binding2.tvItemGithubRepo.text = item.repo
            binding2.tvItemGithubAuthor.text = item.name
            Glide.with(context).load(item.image).into(binding2.ivItemGithubRepo)
        }
    }
}