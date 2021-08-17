package com.example.asteroidradar.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.AsteroidListItemsBinding
import com.example.asteroidradar.domain.Asteroid

class AstroidAdapter(val clickListener: AstroidListener) :
  ListAdapter <Asteroid, AstroidAdapter.AstroidItemViewHolder>(AsteroidDiffCallback()) {


    class AstroidItemViewHolder(val binding: AsteroidListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        var idTextView: TextView
        var dateTextView: TextView
        var emojiImageView: ImageView

        init {
            idTextView = binding.astroidIDTextView
            dateTextView = binding.astroidDateTextView
            emojiImageView = binding.astroidEmoji
        }

        fun bind(clickListener: AstroidListener, asteroid: Asteroid) {
            idTextView.text = asteroid.id.toString()
            dateTextView.text = asteroid.approachDate
            binding.asteroid = asteroid
            binding.clickListener = clickListener
            emojiImageView.setImageResource(
                when (asteroid.isHazardous) {
                    true -> R.drawable.frown_emoji
                    else -> R.drawable.smiley_face
                }
            )
            binding.executePendingBindings()
            emojiImageView.context
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroidItemViewHolder {
        //Creates a new view, that defines the UI of the asteroid_list_items
       // val view = LayoutInflater.from(parent.context).inflate(R.layout.asteroid_list_items, parent, false)
         return from(parent)
    }


    override fun onBindViewHolder(holder: AstroidItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }

    companion object{
        fun from(parent: ViewGroup):AstroidItemViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = AsteroidListItemsBinding.inflate(layoutInflater,parent,false)
            return AstroidItemViewHolder(binding)
        }
    }
}



//Algorithm to detect the changes in the list, so list can be update efficiently
class AsteroidDiffCallback:DiffUtil.ItemCallback<Asteroid>(){
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        //che
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem

    }

}



class AstroidListener(val clickListener: (astroid:Asteroid) -> Unit){
    //Attaches click listener function to onClick function
        fun onClick(astroidId: Asteroid) = astroidId.id?.let { clickListener(astroidId) }
}
