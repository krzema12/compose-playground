import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    var countInMainFunction: Int by mutableStateOf(0)

    renderComposable(rootElementId = "root") {
        H1 {
            Text("Counter")
        }
        Div({
            style {
                padding(25.px)
                color(Color.red)
                fontWeight("bold")
            }
        }) {
            Button(attrs = {
                onClick { countInMainFunction -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$countInMainFunction")
            }

            Button(attrs = {
                onClick { countInMainFunction += 1 }
            }) {
                Text("+")
            }
        }

        Counter()
    }
}

@Composable
fun Counter() {
    // TODO explain: https://developer.android.com/jetpack/compose/state says that remember { ... } is needed, and it
    // works well without it. Why?
    // Read:
    // - https://medium.com/androiddevelopers/understanding-jetpack-compose-part-1-of-2-ca316fe39050
    // - https://medium.com/androiddevelopers/under-the-hood-of-jetpack-compose-part-2-of-2-37b2c20c6cdd
    var countInComposable: Int by remember { mutableStateOf(0) }
    H1 {
        Text("Counter (a composable)")
    }
    Div({ style { padding(25.px) } }) {
        Button(attrs = {
            onClick { countInComposable -= 1 }
        }) {
            Text("-")
        }

        Span({ style { padding(15.px) } }) {
            Text("$countInComposable")
        }

        Button(attrs = {
            onClick { countInComposable += 1 }
        }) {
            Text("+")
        }
    }
}
