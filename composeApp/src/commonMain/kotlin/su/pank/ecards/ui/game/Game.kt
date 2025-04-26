package su.pank.ecards.ui.game

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun Game() {
    Box() { // Maybe bg

        val cards = remember { mutableStateListOf(Card(1), Card(2), Card(3), Card(4), Card(5)) }
        var polePosition: Offset? by remember { mutableStateOf(null) }
        var poleSize: IntSize? by remember { mutableStateOf(null) }
        var placed by remember { mutableStateOf(Offset.Zero) }

        Row(modifier = Modifier.fillMaxSize()) {
            Shop(modifier = Modifier.fillMaxHeight())
            Column(modifier = Modifier.fillMaxWidth()) {
                CardSelector(modifier = Modifier.fillMaxWidth(), isEnabled = false)
                Pole(Modifier.fillMaxHeight(0.7f)) {
                    polePosition = it.positionInWindow()
                    poleSize = it.size
                }
                CardSelector(modifier = Modifier.fillMaxWidth(), cards = cards) { offset, card ->
                    println("$offset $polePosition $poleSize")

                    if (offset.y >= polePosition!!.y && offset.x >= polePosition!!.x && offset.y <= polePosition!!.y + poleSize!!.height && offset.x <= polePosition!!.x + poleSize!!.width) {
                        println("removed")
                        cards.remove(card)
                    }
                    placed = offset
                }
            }
        }
        Box(
            modifier = Modifier.graphicsLayer(
                translationX = placed.x, translationY = placed.y
            ).background(Color.Black).size(20.dp)
        )
    }
}

data class Card(val id: Int) {

}

@Composable
fun Shop(cards: List<Card> = listOf(Card(1), Card(2), Card(3), Card(4), Card(5)), modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically), modifier = modifier) {
        cards.forEach {
            Card(card = it, orientation = Orientation.Horizontal)
        }
        CardStack()
    }
}

@Composable
fun Pole(modifier: Modifier = Modifier, onPlaced: (LayoutCoordinates) -> Unit) {
    // Enemy pole
    Column(modifier) {
        Box(Modifier.fillMaxWidth().weight(1f))
        HorizontalDivider(Modifier.fillMaxWidth())
        Box(Modifier.fillMaxWidth().background(Color.Red).weight(1f).onPlaced {
            onPlaced(it)
        })

    }
}


@Composable
fun CardSelector(
    cards: List<Card> = listOf(Card(1), Card(2), Card(3), Card(4), Card(5), Card(6)),
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onDragEnd: (Offset, Card) -> Unit = { _, _ -> }
) {

    var hoveredCardIndex: Int? by remember { mutableStateOf(null) }

    Row(modifier = modifier.padding(50.dp), horizontalArrangement = Arrangement.Center) {
        cards.forEachIndexed { index, card ->
            var startCardPosition: Offset? by remember { mutableStateOf(null) }
            var size: IntSize? by remember { mutableStateOf(null) }

            val interactionSource = remember { MutableInteractionSource() }
            var isDraggable by remember { mutableStateOf(false) }

            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect { interaction ->
                    when (interaction) {
                        is HoverInteraction.Enter -> {
                            hoveredCardIndex = index
                        }

                        is HoverInteraction.Exit -> {
                            //delay(500.milliseconds)
                            if (hoveredCardIndex == index) {
                                hoveredCardIndex = null
                            }
                        }

                    }

                }
            }
            var ofX by remember { mutableStateOf(0f) }

            val offsetX by animateFloatAsState(
                if (hoveredCardIndex != null && hoveredCardIndex!! < index) {
                    ofX - 50f * index + 50f
                } else {
                    ofX - 50f * index
                }
            )

            var ofY by remember { mutableStateOf(0f) }

            val offsetY by animateFloatAsState(if (isDraggable) ofY else 0f)

            Box {
                Card(card, modifier = Modifier.graphicsLayer {
                    if (isEnabled) {
                        translationX = offsetX
                        translationY = offsetY
                    }

                }.background(Color.White).hoverable(interactionSource).onPlaced {
                    startCardPosition = it.positionInWindow()
                    size = it.size
                }.pointerInput(Unit) {


                    detectDragGestures(onDragEnd = {
                        onDragEnd(startCardPosition?.apply {
                            x + (size?.width ?: 0) / 2
                            y + (size?.height ?: 0) / 2
                        } ?: Offset.Zero, card)


                        isDraggable = false
                        ofY = 0f
                        ofX = 0f
                    }) { change, dragAmount ->
                        change.consume()

                        isDraggable = true
                        ofX += dragAmount.x
                        ofY += dragAmount.y
                    }
                })
            }
        }
    }
}


@Composable
fun CardStack() {
    Card(
        modifier = Modifier.shadow(10.dp, RoundedCornerShape(12.dp)).background(Color.White),
        orientation = Orientation.Horizontal
    )
}

@Composable
fun Card(card: Card? = null, modifier: Modifier = Modifier, orientation: Orientation = Orientation.Vertical) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.border(1.dp, Color.Black, RoundedCornerShape(12.dp)).clip(RoundedCornerShape(12.dp))
            .size(if (orientation == Orientation.Vertical) DpSize(200.dp, 320.dp) else DpSize(320.dp, 200.dp))
    ) {
        Box(Modifier.fillMaxSize())
        Text(card?.id.toString())
    }
}

