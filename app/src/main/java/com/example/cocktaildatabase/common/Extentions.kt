package com.example.cocktaildatabase.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun ViewGroup.layoutInflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun Context.createToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.addFragment(fragment:Fragment, frameId: Int){
    val fragmentManager:FragmentManager = supportFragmentManager
    val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.add(frameId, fragment)
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    val fragmentManager:FragmentManager = supportFragmentManager
    val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(frameId, fragment)
}