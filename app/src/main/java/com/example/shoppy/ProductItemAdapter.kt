package com.example.shoppy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppy.databinding.EachItemBinding

class ProductItemAdapter(
    private val productList: List<Product>
): RecyclerView.Adapter<ProductItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val itemBinding: EachItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(product: Product){
            itemBinding.tvProductName.text = product.title
            itemBinding.tvCategory.text = product.category
            itemBinding.tvPrice.text = "Price: " + product.price.toString()
            itemBinding.tvRating.text = "Rating: " + product.rating.toString()
            Glide.with(itemView.context)
                .load(product.thumbnail)
                .into(itemBinding.ivProduct)
        }
    }

    // if layout manager fails to create view for some data then this method is used
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            EachItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    // Populate data in the view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}