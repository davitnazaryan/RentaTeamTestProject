package ru.rentateam.testproject.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.rentateam.testproject.data.model.User
import ru.rentateam.testproject.databinding.UserRvItemBinding

class UsersAdapter(val onItemClickListener: OnItemClickListener) :
    ListAdapter<User, UsersAdapter.DeviceViewHolder>(Companion) {

    class DeviceViewHolder(
        val binding: UserRvItemBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClickListener = onItemClickListener
        }
    }

    companion object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DeviceViewHolder(
        UserRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClickListener
    )

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.user = user
        holder.binding.executePendingBindings()
    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }
}