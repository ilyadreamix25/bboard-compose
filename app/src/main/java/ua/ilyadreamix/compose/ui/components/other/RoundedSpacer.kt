package ua.ilyadreamix.compose.ui.components.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Rounded spacer
 * @author IlyaDreamix
 */
@Composable
fun RoundedSpacer(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black.copy(alpha = .1f)
) {
    Spacer(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .height(2.dp)
            .background(backgroundColor)
            .then(modifier)
    )
}