package com.example.myapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.myapplication.Model.CategoryModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError


class MainViewModel:ViewModel() {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    private val _category=MutableLiveData<MutableList<CategoryModel>>()

    val category:LiveData<MutableList<CategoryModel>> = _category


    fun loadCategory(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }
                _category.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}