package com.fin.sampletest.base

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.fin.sampletest.R
import com.fin.sampletest.databinding.ToastLayoutBinding

class CustomToast {
    companion object {
        private fun makeTopToast(
            context: Context,
            text: String,
            @ColorRes color: Int,
            @ColorRes borderColor: Int,
            visibleIcon: Boolean,
            @DrawableRes icon: Int = R.drawable.ic_launcher_foreground,
            textColor: Int,

            ) {
            val toastModel =
                CustomToastModel(
                    text,
                    visibleIcon,
                    ContextCompat.getColor(context, color),
                    ContextCompat.getColor(context, borderColor),
                    icon,
                    textColor
                )
            val binding = ToastLayoutBinding.inflate(LayoutInflater.from(context))
                .apply { model = toastModel
                }
            binding.imgToast
            val scope = when (context) {
              /*  is AppCompatActivity -> context.lifecycleScope
                is Fragment -> context.lifecycleScope*/
                else -> null
            }

            if (scope == null) {
                Toast(context.applicationContext).apply {
                    setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
                    duration = Toast.LENGTH_SHORT
                    view = binding.root
                    show()
                }
            }

        }

        fun successToast(context: Context, text: String) {
            try {
                makeTopToast(
                    context,
                    text,
                    R.color.black,
                    R.color.purple_500,
                    false,
                    R.drawable.ic_launcher_foreground,
                    textColor = 2
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class CustomToastModel(
    val text: String,
    val visibleIcon: Boolean,
    @ColorInt val bgColor: Int,
    @ColorInt val bgBorderColor: Int,
    @DrawableRes val icon: Int = R.drawable.ic_launcher_foreground,
    val textColor: Int = 0,
    val visibleEndIcon: Boolean = false
)