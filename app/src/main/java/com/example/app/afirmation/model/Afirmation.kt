package com.example.app.afirmation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Afirmation(
     @StringRes val stringResourceId: Int,
     @DrawableRes val imageResourceId: Int
)
