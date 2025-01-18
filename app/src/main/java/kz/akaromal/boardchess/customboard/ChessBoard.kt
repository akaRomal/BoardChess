package kz.akaromal.boardchess.customboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kz.akaromal.boardchess.BuildConfig
import kz.akaromal.boardchess.R
import kz.akaromal.boardchess.customboard.models.MarkerPosition
import kz.akaromal.boardchess.customboard.models.PiecePosition
import kz.akaromal.boardchess.customboard.models.Position
import kz.akaromal.boardchess.customboard.models.PromotionPiece
import kz.akaromal.boardchess.customboard.models.PromotionPieceColor
import kotlin.math.min

/**
 * Функция для рисования шахматной доски, фигур, маркеров(возможный ход, возможное взятие, шах,
 * последний ход) и окна выбора фигуры при достижение пешки противоположеной стороны доски.
 */
@Composable
fun ChessBoard(
    modifier: Modifier = Modifier,
    piecePosition: PiecePosition,
    markerPosition: MarkerPosition,
    coordinate: (Position) -> Unit,
    promotionPieceColor: PromotionPieceColor? = null,
    selectedPromotion: (PromotionPiece) -> Unit,
    rotatePieces: Boolean = false,
) {

    val pieceImagesDefault: Map<Char, ImageBitmap> = mapOf(
        BuildConfig.FEN_BLACK_ROOK to ImageBitmap.imageResource(id = R.drawable.black_rook),
        BuildConfig.FEN_BLACK_KNIGHT to ImageBitmap.imageResource(id = R.drawable.black_knight),
        BuildConfig.FEN_BLACK_BISHOP to ImageBitmap.imageResource(id = R.drawable.black_bishop),
        BuildConfig.FEN_BLACK_KING to ImageBitmap.imageResource(id = R.drawable.black_king),
        BuildConfig.FEN_BLACK_QUEEN to ImageBitmap.imageResource(id = R.drawable.black_queen),
        BuildConfig.FEN_BLACK_PAWN to ImageBitmap.imageResource(id = R.drawable.black_pawn),
        BuildConfig.FEN_WHITE_ROOK to ImageBitmap.imageResource(id = R.drawable.white_rook),
        BuildConfig.FEN_WHITE_KNIGHT to ImageBitmap.imageResource(id = R.drawable.white_knight),
        BuildConfig.FEN_WHITE_BISHOP to ImageBitmap.imageResource(id = R.drawable.white_bishop),
        BuildConfig.FEN_WHITE_KING to ImageBitmap.imageResource(id = R.drawable.white_king),
        BuildConfig.FEN_WHITE_QUEEN to ImageBitmap.imageResource(id = R.drawable.white_queen),
        BuildConfig.FEN_WHITE_PAWN to ImageBitmap.imageResource(id = R.drawable.white_pawn)
    )

    val promotionDialogColor = Color(color = 0xFFFFFFFF).copy(alpha = 0.9f)
    val boardSquareWhiteColor = Color(color = 0xFFEAEBCF)
    val boardSquareBlackColor = Color(color = 0xFFADADAD)

    Canvas(modifier = modifier.pointerInput(promotionPieceColor) {
        val boardSize = min(size.width, size.height).toFloat()
        val cellSize = boardSize / 8
        detectTapGestures(
            onPress = { tapOffset ->
                if (promotionPieceColor != null) {
                    val (promotionX, promotionY) = getPromotionPieceCoordinates(
                        cellSize,
                        rotatePieces,
                        PromotionPieceColor.WHITE
                    )
                    if (tapOffset.x in promotionX.first()..(promotionX.last() + cellSize)
                        && tapOffset.y in promotionY..(promotionY + cellSize)
                    ) {
                        val selectedCellX = (tapOffset.x / cellSize - 2).toInt()
                        val promotionPieceList =
                            if (rotatePieces && promotionPieceColor == PromotionPieceColor.BLACK) {
                                PromotionPiece.entries.reversed()
                            } else {
                                PromotionPiece.entries
                            }
                        if (selectedCellX in promotionPieceList.indices) {
                            selectedPromotion(promotionPieceList[selectedCellX])
                        }
                    }
                } else if (tapOffset.x in 0f..boardSize && tapOffset.y in 0f..boardSize) {
                    val selectedCellX = (tapOffset.x / cellSize).toInt()
                    val selectedCellY = (tapOffset.y / cellSize).toInt()
                    coordinate(Position(x = selectedCellX, y = selectedCellY))
                }
            }
        )
    }) {
        val canvasSize = min(size.width, size.height)
        val cellSize = canvasSize / 8

        // Отрисовываем доску
        drawChessBoard(
            sizeBoard = canvasSize,
            squareWhite = boardSquareWhiteColor,
            squareBlack = boardSquareBlackColor,
        )

        //Отрисовываем маркеры
        markerPosition.markers.forEach { marker ->
            marker.drawMarker(drawScope = this, cellSize = cellSize)
        }

        // Отрисовываем фигуры
        piecePosition.piecePositions.forEach { (position, piece) ->
            val rectX = position.x * cellSize
            val rectY = position.y * cellSize
            pieceImagesDefault[piece.fenName]?.let { image ->
                drawRotatedImage(
                    image = image,
                    rectX = rectX,
                    rectY = rectY,
                    cellSize = cellSize,
                    shouldRotate = rotatePieces && piece.fenName.isLowerCase(),
                )
            }
        }

        // Выводим окно с выбором фигур если пешка дошла до противоположеной стороны
        promotionPieceColor?.let { color ->
            val promotionPieces = PromotionPiece.getPiecesForColor(color)

            val (promotionPieceCoordinateX, promotionPieceCoordinateY) = getPromotionPieceCoordinates(
                color = color,
                cellSize = cellSize,
                rotatePieces = rotatePieces
            )

            //отрисовали окно
            drawRect(
                color = promotionDialogColor,
                topLeft = Offset(cellSize, cellSize * 3),
                size = Size(canvasSize - cellSize * 2, cellSize * 2)
            )

            // Отрисовка фигур для превращения
            promotionPieces.forEachIndexed { index, piece ->
                pieceImagesDefault[piece]?.let { imagePiece ->
                    drawRotatedImage(
                        image = imagePiece,
                        rectX = promotionPieceCoordinateX[index],
                        rectY = promotionPieceCoordinateY,
                        cellSize = cellSize,
                        shouldRotate = rotatePieces && piece.isLowerCase(),
                    )
                }
            }
        }
    }
}

/**
 * Функция для прорисовки доски
 */
private fun DrawScope.drawChessBoard(sizeBoard: Float, squareWhite: Color, squareBlack: Color) {
    val boardSize = 8
    val tileSize = sizeBoard / boardSize
    // Рисуем по 32 белых и черных квадрата на нужных позициях
    for (row in 0 until boardSize) {
        for (col in 0 until boardSize) {
            val color = if ((row + col) % 2 == 0) squareWhite else squareBlack
            drawRect(
                color = color,
                topLeft = Offset(col * tileSize, row * tileSize),
                size = Size(tileSize, tileSize)
            )
        }
    }
}

/**
 * Функция для отрисовки фигур с возможным поворотом на 180 градусов чёрных фигур
 */
private fun DrawScope.drawRotatedImage(
    image: ImageBitmap,
    rectX: Float,
    rectY: Float,
    cellSize: Float,
    shouldRotate: Boolean,
) {
    val dstSize = IntSize(cellSize.toInt(), cellSize.toInt())
    val dstOffset = IntOffset(rectX.toInt(), rectY.toInt())

    if (shouldRotate) {
        withTransform({
            rotate(
                degrees = 180f,
                pivot = Offset(rectX + cellSize / 2, rectY + cellSize / 2)
            )
        }) {
            drawImage(
                image = image,
                dstSize = dstSize,
                dstOffset = dstOffset
            )
        }
    } else {
        drawImage(
            image = image,
            dstSize = dstSize,
            dstOffset = dstOffset
        )
    }
}

/**
 * Вспомогательная функция для получения координат для превращения
 */
private fun getPromotionPieceCoordinates(
    cellSize: Float,
    rotatePieces: Boolean = false,
    color: PromotionPieceColor
): Pair<Array<Float>, Float> {
    val promotionX = if (rotatePieces && color == PromotionPieceColor.BLACK) {
        arrayOf(cellSize * 5, cellSize * 4, cellSize * 3, cellSize * 2)
    } else {
        arrayOf(cellSize * 2, cellSize * 3, cellSize * 4, cellSize * 5)
    }
    val promotionY = cellSize * 3.5f
    return promotionX to promotionY
}