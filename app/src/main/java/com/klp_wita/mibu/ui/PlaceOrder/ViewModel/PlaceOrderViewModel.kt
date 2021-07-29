package com.klp_wita.mibu.ui.PlaceOrder.ViewModel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.klp_wita.mibu.model.FruitListData
import com.klp_wita.mibu.ui.PlaceOrder.PlaceOrderActivity

class PlaceOrderViewModel(): ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    var _itemData = MutableLiveData<List<FruitListData>>()
    val shippingcharge = 20000
    private lateinit var mAuth: FirebaseAuth
    val itemprice = MutableLiveData<Int>().apply {
        value = 0
    }

    val currentOrderID = MutableLiveData<String>().apply {
        value = ""
    }

    val savestatus = MutableLiveData<Boolean>().apply {
        value = false
    }

    val totalitemprice = MutableLiveData<Int>().apply {
        value = 0
    }

    val itemqty = MutableLiveData<Int>().apply {
        value = 1
    }

    val fullname = MutableLiveData<String>().apply {
        value = ""
    }
    val address = MutableLiveData<String>().apply {
        value = ""
    }

    val currentName = MutableLiveData<String>().apply {
        value = ""
    }

    val currentAddress = MutableLiveData<String>().apply {
        value = ""
    }

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        mAuth = FirebaseAuth.getInstance()
    }

    fun initCart(id:String){
        firestore.collection("fruit_list")
            .document(id)
            .get()
            .addOnSuccessListener {
                itemprice.value?.let { a ->
                    itemprice.value = it.get("price").toString().toInt()
                }
                totalitemprice.value?.let { a ->
                    totalitemprice.value = (it.get("price").toString().toInt())+shippingcharge
                }

                val item = ArrayList<FruitListData>()
                val fr = it.toObject(FruitListData::class.java)
                if(fr!=null){
                    fr.id = it.id
                    item.add(fr)
                }

                _itemData.value = item
            }

        firestore.collection("user_details")
            .document(mAuth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener { a ->
                currentAddress.value?.let{
                    currentAddress.value = a.get("address").toString()
                }

                currentName.value?.let {
                    currentName.value = a.get("name").toString()
                }

            }

    }

    fun updateCartPrice(qty:Int){
        totalitemprice.value?.let { a ->
            totalitemprice.value = (qty*itemprice.value.toString().toInt())+shippingcharge
        }

        itemqty.value?.let { a ->
            itemqty.value = qty
        }
    }

    fun save(){
        val name = fullname.value.toString()
        val address = address.value.toString()
        val itemid = _itemData.value?.get(0)?.id.toString()
        val tprice = totalitemprice.value
        val qty = itemqty.value

        val orderData = hashMapOf(
            "name" to name,
            "address" to address,
            "item_id" to itemid,
            "item_qty" to qty,
            "total_price" to tprice
        )

        firestore.collection("order_list")
            .add(orderData)
            .addOnSuccessListener { ref ->
                savestatus.value = true
                currentOrderID.value = ref.id
            }
            .addOnFailureListener { e ->
                Log.e(TAG,"ERROR SAVE TO DATABASE",e)
            }
    }

    var itemData : MutableLiveData<List<FruitListData>>
        get() {return _itemData}
        set(value) {_itemData = value}

}