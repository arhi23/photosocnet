package com.github.arhi23.photosocnet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection.Ltr

fun String.codePointsSize() = codePoints().count()

operator fun PaddingValues.plus(plus: PaddingValues): PaddingValues = PaddingValues(
  start = this.calculateStartPadding(Ltr) +
      plus.calculateStartPadding(Ltr),
  top = this.calculateTopPadding() + plus.calculateTopPadding(),
  end = this.calculateEndPadding(Ltr) +
      plus.calculateEndPadding(Ltr),
  bottom = this.calculateBottomPadding() + plus.calculateBottomPadding(),
)
