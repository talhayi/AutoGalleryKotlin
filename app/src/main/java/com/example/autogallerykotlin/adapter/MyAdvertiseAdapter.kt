package com.example.autogallerykotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autogallerykotlin.data.model.MyAdvertise
import com.example.autogallerykotlin.data.model.MyAdvertiseItem
import com.example.autogallerykotlin.databinding.MyadvertiseItemLayoutBinding

class MyAdvertiseAdapter : RecyclerView.Adapter<MyAdvertiseAdapter.MyAdvertiseViewHolder>() {

    class MyAdvertiseViewHolder(val binding: MyadvertiseItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    private val diffCallBack = object : DiffUtil.ItemCallback<MyAdvertiseItem>(){

        override fun areItemsTheSame(oldItem: MyAdvertiseItem, newItem: MyAdvertiseItem): Boolean {
          return oldItem.advert_id == newItem.advert_id
        }

        override fun areContentsTheSame(oldItem: MyAdvertiseItem, newItem: MyAdvertiseItem): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    var myAdvertise: List<MyAdvertiseItem>
    get() = differ.currentList
    set(value){
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdvertiseViewHolder {
        return MyAdvertiseViewHolder(MyadvertiseItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyAdvertiseViewHolder, position: Int) {
        val currentMyAdvertise = myAdvertise[position]
        holder.binding.apply {
            myAdvertiseRowAdvertTitleTextView.text = currentMyAdvertise.advert_title
            myAdvertiseRowAddressTextView.text = currentMyAdvertise.address
            myAdvertiseRowPriceTextView.text = currentMyAdvertise.price
            myAdvertiseRowImageView.load(currentMyAdvertise.image){
                crossfade(true)
                crossfade(1000)
            }
        }

    }

    override fun getItemCount(): Int {
       return myAdvertise.size
    }
}