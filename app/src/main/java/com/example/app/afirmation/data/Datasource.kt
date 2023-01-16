package com.example.app.afirmation.data

import com.example.app.R
import com.example.app.afirmation.model.Afirmation

class Datasource() {
    fun loadAffirmations(): List<Afirmation> {
        return listOf<Afirmation>(
            Afirmation(R.string.affirmation1, R.drawable.image1),
            Afirmation(R.string.affirmation2, R.drawable.image2),
            Afirmation(R.string.affirmation3, R.drawable.image5),
            Afirmation(R.string.affirmation4, R.drawable.image4),
            Afirmation(R.string.affirmation5, R.drawable.image5),
            Afirmation(R.string.affirmation6, R.drawable.image6),
            Afirmation(R.string.affirmation7, R.drawable.image7),
            Afirmation(R.string.affirmation8, R.drawable.image8),
            Afirmation(R.string.affirmation9, R.drawable.image9),
            Afirmation(R.string.affirmation10, R.drawable.image10))
    }
}

