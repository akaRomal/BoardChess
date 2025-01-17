package kz.akaromal.boardchess.customboard.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

sealed class Marker(val position: Position, val type: MarkerType) {
    abstract fun drawMarker(drawScope: DrawScope, cellSize: Float)

    /**
     * Маркер последнего хода
     */
    class LastMove(position: Position) :
        Marker(position = position, type = MarkerType.LAST_MOVE) {
        override fun drawMarker(drawScope: DrawScope, cellSize: Float) {
            val offset = Offset(position.x * cellSize, position.y * cellSize)
            val size = Size(cellSize, cellSize)
            drawScope.drawRect(
                color = type.color,
                topLeft = offset,
                size = size
            )
        }
    }

    /**
     * Маркер возможного хода
     */
    class PossibleMove(position: Position) :
        Marker(position = position, type = MarkerType.POSSIBLE_MOVE) {
        override fun drawMarker(drawScope: DrawScope, cellSize: Float) {
            val center = Offset(
                position.x * cellSize + cellSize / 2,
                position.y * cellSize + cellSize / 2
            )
            drawScope.drawCircle(
                color = type.color,
                radius = cellSize / 6,
                center = center
            )
        }
    }

    /**
     * Маркер возможного взятия
     */
    class PossibleCapture(position: Position) :
        Marker(position = position, type = MarkerType.POSSIBLE_CAPTURE) {
        override fun drawMarker(drawScope: DrawScope, cellSize: Float) {
            val offset = Offset(position.x * cellSize, position.y * cellSize)
            drawScope.drawCaptureMarker(
                offset = offset,
                cellSize = cellSize,
                color = type.color
            )
        }
    }

    /**
     * Маркер шаха или мата
     */
    class Check(position: Position) : Marker(position = position, type = MarkerType.CHECK) {
        override fun drawMarker(drawScope: DrawScope, cellSize: Float) {
            val offset = Offset(position.x * cellSize, position.y * cellSize)
            val size = Size(cellSize, cellSize)
            drawScope.drawRect(
                color = type.color,
                topLeft = offset,
                size = size
            )
        }
    }
}

/**
 * Функция для отрисовки маркера с фигурой которую можно взять
 */
private fun DrawScope.drawCaptureMarker(
    offset: Offset,
    cellSize: Float,
    color: Color,
    triangleSizeFraction: Float = 0.25f
) {
    // размер треугольника
    val triangleSize = cellSize * triangleSizeFraction

    // Координаты углов квадрата
    val corners = arrayOf(
        Offset(offset.x, offset.y),                                         // Верхний левый
        Offset(offset.x + cellSize, offset.y),                            // Верхний правый
        Offset(offset.x, offset.y + cellSize),                            // Нижний левый
        Offset(offset.x + cellSize, offset.y + cellSize)                // Нижний правый
    )

    // Направления для каждого угла для вычисления вершин треугольников
    val directions = arrayOf(
        Pair(1f, 1f),   // Верхний левый
        Pair(-1f, 1f),  // Верхний правый
        Pair(1f, -1f),  // Нижний левый
        Pair(-1f, -1f)  // Нижний правый
    )

    corners.zip(directions).forEach { (corner, dir) ->
        val (dx, dy) = dir
        val path = Path().apply {
            moveTo(corner.x, corner.y)
            lineTo(corner.x + dx * triangleSize, corner.y)
            lineTo(corner.x, corner.y + dy * triangleSize)
            close()
        }
        drawPath(
            path = path,
            color = color
        )
    }
}