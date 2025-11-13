package com.crs.receitafacil.ui.presentation.components.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneOriginalTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val phoneMask = text.text.mapIndexed { index, c ->
            when (index) {
                1, 2 -> "$c "
                6 -> "$c-"
                else -> "$c"
            }
        }.joinToString(separator = "")

        return TransformedText(
            text = AnnotatedString(phoneMask),
            offsetMapping = PhoneOffsetMapping
        )
    }

    object PhoneOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset >= 7 -> offset + 3
                offset >= 3 -> offset + 2
                offset >= 2 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset >= 10 -> offset - 3
                offset >= 5 -> offset - 2
                offset >= 3 -> offset - 1
                else -> offset
            }
        }
    }

}