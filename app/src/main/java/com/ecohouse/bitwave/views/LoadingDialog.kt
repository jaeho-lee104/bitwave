package com.ecohouse.bitwave.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialog
import com.ecohouse.bitwave.R
import com.ecohouse.bitwave.databinding.DialogLoadingBinding


/**
 * @author leejaeho on 2021. 03. 25..
 */
class LoadingDialog(context: Context) : AppCompatDialog(context, R.style.LoadingDialogTheme) {

    private lateinit var binding: DialogLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setCancelable(false)
    }

}