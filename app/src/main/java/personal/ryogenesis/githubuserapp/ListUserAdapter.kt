package personal.ryogenesis.githubuserapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import personal.ryogenesis.githubuserapp.databinding.ItemUserBinding

class ListUserAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(vwgroup: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemUserBinding.inflate(LayoutInflater.from(vwgroup.context), vwgroup, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked((userList[position]))}
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ListViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            with(binding){
                Glide.with(itemView.context)
                    .load(user.avatarImg)
                    .apply(RequestOptions().override(100,100))
                    .into(imgAvatar)

                tvUserName.text = user.username
                tvName.text = user.name
                val follower = "Followers: ${user.followers}"
                tvFollower.text = follower
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}