package kz.akaromal.boardchess.customboard.models

/**
 * Класс [Position] представляет позицию фигуры на шахматной доске с координатами по осям X и Y.
 *
 * Координаты `x` и `y` соответствуют столбцам и строкам шахматной доски соответственно.
 * Метод [toBoardCoordinates] преобразует эти координаты в стандартную шахматную нотацию (например, "e4"),
 * а функция [fromBoardCoordinates] выполняет обратное преобразование.
 *
 * @property x Координата по оси X (столбец) на шахматной доске (0-7).
 * @property y Координата по оси Y (ряд) на шахматной доске (0-7).
 * @throws IllegalArgumentException для функций [toBoardCoordinates] и [fromBoardCoordinates]
 */
data class Position(
    val x: Int,
    val y: Int,
) {
    /**
     * Преобразует координаты позиции в стандартную шахматную нотацию.
     *
     * @param isBlackAtTop Флаг, указывающий, расположены ли чёрные фигуры сверху на доске.
     * @return Строка с координатами позиции в шахматной нотации (например, "e4").
     * @throws IllegalArgumentException Если координаты `x` или `y` выходят за допустимые пределы (0-7).
     */
    fun toBoardCoordinates(isBlackAtTop: Boolean): String {
        require(x in 0..7) { "Координата 'x' должна быть в диапазоне от 0 до 7, получено: $x" }
        require(y in 0..7) { "Координата 'y' должна быть в диапазоне от 0 до 7, получено: $y" }

        val file = if (isBlackAtTop) 'a' + x else 'h' - x
        val rank = if (isBlackAtTop) 8 - y else y + 1
        return "$file$rank"
    }

    companion object {
        /**
         * Создаёт объект [Position] из строки шахматной нотации.
         *
         * @param coordinate Строка с координатами позиции в шахматной нотации (например, "e4").
         * @param isBlackAtTop Флаг, указывающий, расположены ли чёрные фигуры сверху на доске.
         * @return Объект [Position], соответствующий заданной шахматной нотации.
         * @throws IllegalArgumentException Если строка координаты имеет некорректный формат или содержит недопустимые символы.
         */
        fun fromBoardCoordinates(coordinate: String, isBlackAtTop: Boolean): Position {
            require(coordinate.length == 2) { "Координата должна состоять из двух символов, получено: '$coordinate'" }

            val fileChar = coordinate[0].lowercaseChar()
            val rankChar = coordinate[1]

            // Проверка допустимости символов файла (столбца)
            require(fileChar in 'a'..'h') { "Недопустимый символ файла: '$fileChar'. Ожидалось от 'a' до 'h'." }

            // Проверка допустимости символов ранга (ряда)
            require(rankChar in '1'..'8') { "Недопустимый символ ранга: '$rankChar'. Ожидалось от '1' до '8'." }

            val file = fileChar - 'a'
            val rank = rankChar.toString().toInt()

            val x = if (isBlackAtTop) file else 'h' - fileChar
            val y = if (isBlackAtTop) 8 - rank else rank - 1

            // Проверка корректности вычисленных координат
            require(x in 0..7) { "Вычисленная координата x выходит за пределы: $x" }
            require(y in 0..7) { "Вычисленная координата y выходит за пределы: $y" }

            return Position(x, y)
        }
    }
}
