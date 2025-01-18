package kz.akaromal.boardchess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.akaromal.boardchess.customboard.ChessBoard
import kz.akaromal.boardchess.customboard.models.Marker
import kz.akaromal.boardchess.customboard.models.MarkerPosition
import kz.akaromal.boardchess.customboard.models.Position
import kz.akaromal.boardchess.ui.theme.BoardChessTheme

class MainActivity : ComponentActivity() {

    private val fenConverter = FenConverter()
    private val testPiecePosition =
        fenConverter.parseFen(fenNotation = "rnbqkb1r/ppppp2p/5p1n/6pQ/2BPP3/8/PPP2PPP/RNB1K1NR w KQkq - 0 1")
    private val testMarkerPosition =
        MarkerPosition(
            markers = listOf(
                Marker.LastMove(position = Position(x = 3, y = 7)),
                Marker.LastMove(position = Position(x = 7, y = 3)),
                Marker.Check(position = Position(x = 4, y = 0)),
                Marker.PossibleMove(position = Position(x = 5, y = 1)),
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoardChessTheme {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 10.dp)
                ) {
                    ChessBoard(
                        modifier = Modifier
                            .wrapContentSize()
                            .aspectRatio(1f),
                        piecePosition = testPiecePosition,
                        markerPosition = testMarkerPosition,
                        coordinate = { },
                        selectedPromotion = {},
                        rotatePieces = false,
                    )
                }
            }
        }
    }
}