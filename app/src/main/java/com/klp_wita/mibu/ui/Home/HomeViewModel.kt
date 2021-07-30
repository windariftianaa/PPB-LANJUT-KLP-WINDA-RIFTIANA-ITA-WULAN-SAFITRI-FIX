package com.klp_wita.mibu.ui.Home

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.klp_wita.mibu.R
import com.klp_wita.mibu.model.FruitListData
import kotlin.coroutines.coroutineContext

class HomeViewModel() : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    var _fruitlist = MutableLiveData<List<FruitListData>>()
    var _localFruit = MutableLiveData<List<FruitListData>>()
    var _importFruit = MutableLiveData<List<FruitListData>>()
    var _sayurFruit = MutableLiveData<List<FruitListData>>()

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    }


    fun fetchCategorizedFruit(){
        var localFruitCollection = firestore.collection("fruit_list").whereEqualTo("category",1)
        var importFruitCollection = firestore.collection("fruit_list").whereEqualTo("category",2)
        var sayurFruitCollection = firestore.collection("fruit_list").whereEqualTo("category",3)

        localFruitCollection.addSnapshotListener { value, error ->
            if(error!=null){
                Log.d(TAG,"Listen Failed",error)
                return@addSnapshotListener
            }
            if(value!=null){
                val localFruit = ArrayList<FruitListData>()
                val doc = value.documents
                doc.forEach {
                    val fr = it.toObject(FruitListData::class.java)
                    if(fr!=null){
                        fr.id = it.id
                        localFruit.add(fr!!)
                    }
                }
                _localFruit.value = localFruit
            }
        }

        importFruitCollection.addSnapshotListener { value, error ->
            if(error!=null){
                Log.d(TAG,"Listen Failed",error)
                return@addSnapshotListener
            }
            if(value!=null){
                val importFruit = ArrayList<FruitListData>()
                val doc = value.documents

                doc.forEach {
                    val fr = it.toObject(FruitListData::class.java)
                    if(fr!=null){
                        fr.id = it.id
                        importFruit.add(fr!!)
                    }
                }
                _importFruit.value = importFruit
            }
        }

        sayurFruitCollection.addSnapshotListener { value, error ->
            if(error!=null){
                Log.d(TAG,"Listen Failed",error)
                return@addSnapshotListener
            }
            if(value!=null){
                val sayurFruit = ArrayList<FruitListData>()
                val doc = value.documents

                doc.forEach {
                    val fr = it.toObject(FruitListData::class.java)
                    if(fr!=null){
                        fr.id = it.id
                        sayurFruit.add(fr!!)
                    }
                }
                _sayurFruit.value = sayurFruit
            }
        }

    }

    fun fetchFruit(){
        var fruitCollection = firestore.collection("fruit_list")

        fruitCollection.addSnapshotListener { value, error ->
            if(error!=null){
                Log.d(TAG,"Listen Failed",error)
                return@addSnapshotListener
            }
            if(value!=null){
                val fruitList = ArrayList<FruitListData>()
                val doc = value.documents

                doc.forEach {
                    val fr = it.toObject(FruitListData::class.java)
                    if(fr!=null){
                        fr.id = it.id
                        fruitList.add(fr!!)
                    }
                }
                _fruitlist.value = fruitList
            }
        }
    }

    internal var fruilist: MutableLiveData<List<FruitListData>>
        get() {return _fruitlist}
        set(value) {_fruitlist = value}

    internal var localFruit: MutableLiveData<List<FruitListData>>
        get() {return _localFruit}
        set(value) {_localFruit = value}

    internal var importFruit: MutableLiveData<List<FruitListData>>
        get() {return _importFruit}
        set(value) {_importFruit = value}

    internal var sayurFruit: MutableLiveData<List<FruitListData>>
        get() {return _sayurFruit}
        set(value) {_sayurFruit = value}
}