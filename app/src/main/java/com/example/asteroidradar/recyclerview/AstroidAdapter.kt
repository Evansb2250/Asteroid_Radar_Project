package com.example.asteroidradar.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.R
import com.example.asteroidradar.domain.Asteroid

class AstroidAdapter(private val data: List<Asteroid>): RecyclerView.Adapter<AstroidAdapter.AstroidItemViewHolder>(){


    class AstroidItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var idTextView : TextView
        var dateTextView:TextView

        init {
            idTextView = view.findViewById(R.id.astroidIDTextView)
            dateTextView = view.findViewById(R.id.astroidDateTextView)
        }

        fun bind(asteroid : Asteroid){
            idTextView.text = asteroid.id.toString()
            dateTextView.text = asteroid.approachDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstroidItemViewHolder {
        //Creates a new view, that defines the UI of the asteroid_list_items
        val view = LayoutInflater.from(parent.context).inflate(R.layout.asteroid_list_items, parent, false)
        return AstroidItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AstroidItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount():Int = data.size
}



