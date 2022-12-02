package com.example.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.databinding.ErrorDialogBinding

private const val POSITIVE_BUTTON_TEXT = "Ok"
const val DIALOG_PARAGRAPH_KEY = "DIALOG_PARAGRAPH_KEY"
const val NETWORK_ERROR_MESSAGE = "No Internet Connection"

class ErrorDialogFragment : DialogFragment() {

    private lateinit var binding: ErrorDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ErrorDialogBinding.inflate(layoutInflater)
        arguments?.let {
            val paragraph = it.getString(DIALOG_PARAGRAPH_KEY)
            binding.dialogText.text = paragraph
        }
        val listener = DialogInterface.OnClickListener { _, _ ->
            findNavController().popBackStack()
        }
        return AlertDialog.Builder(activity)
            .setView(binding.root)
            .setPositiveButton(POSITIVE_BUTTON_TEXT, listener)
            .setCancelable(false)
            .create()
    }
}