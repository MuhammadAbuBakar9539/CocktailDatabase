package com.example.cocktaildatabase.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.view.fragments.DrinksFragment
import com.squareup.picasso.Picasso

fun ViewGroup.layoutInflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun Context.createToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    val fragmentManager: FragmentManager = supportFragmentManager
    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.add(frameId, fragment).commit()
}

fun Fragment.replaceFragmentInsideFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    frameId: Int
) {
    val transaction =
        fragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    transaction.commit()
}

fun loadImage(img_url: String, imageView: ImageView) {
    Picasso.get().load(img_url).into(imageView)
}

fun Application.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}