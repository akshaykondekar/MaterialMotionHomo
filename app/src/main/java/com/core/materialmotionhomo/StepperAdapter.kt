package com.core.materialmotionhomo

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.materialmotionhomo.databinding.CvSteeperItemBinding

class StepperAdapter(val spacies : List<Any>)
        : RecyclerView.Adapter<StepperAdapter.ViewHolder>() {
    lateinit var stepClickListener : StepClickListener
    private var selected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            CvSteeperItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = spacies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    inner class ViewHolder(val binding : CvSteeperItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            if(position == spacies.size -1 ){
                binding.view.visibility = GONE
            }
            else{
                binding.view.visibility = VISIBLE
            }

            binding.tvStepperName.isSelected = position == selected
            binding.btnStepper.isSelected = position == selected

            binding.tvStepperName.text = position.toString()
            binding.btnStepper.setOnClickListener {
                val item = selected
                selected = position
                stepClickListener.onStepClick(position)
                notifyItemChanged(item)
                notifyItemChanged(selected)
            }
        }
    }

    interface StepClickListener {
        fun onStepClick(position: Int)
    }
}


