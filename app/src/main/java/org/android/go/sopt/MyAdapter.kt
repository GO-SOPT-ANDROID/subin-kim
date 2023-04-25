package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubRepoBinding

class MyAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    val itemlist : List<Repo> = listOf(Repo("name1","author1"),Repo("name2","author2"))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : ItemGithubRepoBinding = ItemGithubRepoBinding.inflate(
            inflater,
            parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(itemlist[position])
    }

}

class MyViewHolder(private val binding: ItemGithubRepoBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun onBind(item: Repo) {
        binding.tvItemGithubRepo.text = item.name
        binding.tvItemGithubAuthor.text = item.author
    }

}



data class Repo(
    //@DrawableRes val image: Int,
    val name: String,
    val author:String
)