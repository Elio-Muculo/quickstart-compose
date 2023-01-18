package com.example.app.courses

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Course(
    @StringRes val text: Int,
    val value: Int,
    @DrawableRes val image: Int
)
