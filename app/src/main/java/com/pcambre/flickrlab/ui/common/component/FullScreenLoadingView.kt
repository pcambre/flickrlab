import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme

@Composable
fun FullScreenLoadingView(modifier: Modifier) {
    Surface(
        color = FlickrLabTheme.colors.White.copy(alpha = 0.6f),
        modifier = modifier
    ) {
        Box() {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = FlickrLabTheme.colors.Green600
            )
        }
    }
}