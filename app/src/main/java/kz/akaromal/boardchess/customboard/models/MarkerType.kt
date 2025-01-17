package kz.akaromal.boardchess.customboard.models

import androidx.compose.ui.graphics.Color

/**
 * LAST_MOVE - последние координаты от куда и куда походили,
 * POSSIBLE_MOVE- поля куда можно,
 * POSSIBLE_CAPTURE - поля где можно взять фигуру,
 * CHECK - король под шахом,
 */
enum class MarkerType(val color: Color) {
    LAST_MOVE(color = Color(color = 0xFF00FF00).copy(alpha = 0.2f)),
    POSSIBLE_MOVE(color = Color(color = 0xFF333333).copy(alpha = 0.5f)),
    POSSIBLE_CAPTURE(color = Color(color = 0xFFFF0000)),
    CHECK(color = Color(color = 0xFFD00000)),
}