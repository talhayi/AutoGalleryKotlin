package com.example.autogallerykotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autogallerykotlin.data.model.AdvertiseDetailImage
import com.example.autogallerykotlin.databinding.DetailImageItemLayoutBinding
import com.example.autogallerykotlin.util.Util.BASE_URL

class AdvertiseDetailImageAdapter: RecyclerView.Adapter<AdvertiseDetailImageAdapter.AdvertiseDetailImageViewHolder>() {
    class AdvertiseDetailImageViewHolder(var binding: DetailImageItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object: DiffUtil.ItemCallback<AdvertiseDetailImage>(){
        override fun areItemsTheSame(
            oldItem: AdvertiseDetailImage,
            newItem: AdvertiseDetailImage
        ): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(
            oldItem: AdvertiseDetailImage,
            newItem: AdvertiseDetailImage
        ): Boolean {
            return newItem == oldItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    var detailImages: List<AdvertiseDetailImage>

        get() = differ.currentList
            set(value) {
                differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertiseDetailImageViewHolder {
        return AdvertiseDetailImageViewHolder(DetailImageItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)
           // ,mListener
        )
    }

    override fun onBindViewHolder(holder: AdvertiseDetailImageViewHolder, position: Int) {
       val currentDetailImages = detailImages[position]

        holder.binding.apply {
            advertiseDetailImageView.load(BASE_URL + currentDetailImages.image){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int {
      return detailImages.size
    }
}