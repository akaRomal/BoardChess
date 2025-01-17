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
import kz.akaromal.boardchess.ui.theme.BoardChessTheme

class MainActivity : ComponentActivity() {

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