package com.faridfajrak.kotlin_playground.features.currency_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faridfajrak.kotlin_playground.databinding.BindingCurrencyAdapterItem
import com.squareup.picasso.Picasso

class CurrencyListAdapter(var currencies : List<CurrencyModel>,  val onclick : (currency : CurrencyModel)-> Unit) : RecyclerView.Adapter<CurrencyListAdapter.Holder>() {

    var firstData = currencies

    fun applyFilter(query : String){
        if(query.isEmpty())
        {
            currencies = firstData
            notifyDataSetChanged()
        }else{
            currencies = firstData.filter { it.id.contains(query) }
            notifyDataSetChanged()
        }
    }

   class Holder(val binding : BindingCurrencyAdapterItem) : RecyclerView.ViewHolder(binding.root)
   {
       fun bind(currency : CurrencyModel,onClick : (currency : CurrencyModel)->Unit){

           Picasso.get()
               .load(currency.image)
               .resize(100, 100)
               .centerCrop()
               .into(binding.icon)

           binding.model = currency
           binding.cv.setOnClickListener {
               onClick(currency)
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var bindig = BindingCurrencyAdapterItem.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(bindig)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currencies[position],onclick)
    }

    override fun getItemCount(): Int = currencies.count()
}