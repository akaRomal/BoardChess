package kz.akaromal.boardchess

import kz.akaromal.boardchess.customboard.models.Piece
import kz.akaromal.boardchess.customboard.models.PiecePosition
import kz.akaromal.boardchess.customboard.models.Position

class FenConverter {
    private val fenRegex =
        Regex(pattern = """^((?:[prnbqkPRNBQK1-8]{1,8}/){7}[prnbqkPRNBQK1-8]{1,8})\s([wb])\s(KQ?k?q?|K?Qk?q?|K?Q?kq?|K?Q?k?q|KQkq|-)\s([a-h][36]|-)\s(\d{1,2})\s(\d{1,3})$""")

    fun parseFen(fenNotation: String): PiecePosition {
        // Проверяем корректность FEN-строки с помощью регулярного выражения
        isValidFenUsingRegex(fen = fenNotation)
        val parts = fenNotation.split(" ")
        val piecePositionsPart = parts[0]
        return getPiecePosition(fenPiecePositions = piecePositionsPart)
    }

    private fun isValidFenUsingRegex(fen: String): Boolean {
        val matchResult = fenRegex.matchEntire(fen)
            ?: throw IllegalArgumentException("Исключение в классе ${this::class.java}. FEN не соответствует синтаксической структуре.")
        val piecePlacement = matchResult.groupValues[1]
        val kingCount =
            piecePlacement.count { it == BuildConfig.FEN_WHITE_KING || it == BuildConfig.FEN_BLACK_KING }
        if (kingCount != 2)
            throw IllegalArgumentException("Ошибка в классе ${this::class.java}. Должно быть ровно два короля (один белый и один чёрный).")
        return true
    }

    private fun getPiecePosition(
        fenPiecePositions: String
    ): PiecePosition {
        val rows = fenPiecePositions.split("/")
        val piecePositionsMap = HashMap<Position, Piece>()
        val rowIndices = 0..7

        rowIndices.forEachIndexed { y, fenRowIndex ->
            val row = rows[fenRowIndex]
            var x = 0

            row.forEach { char ->
                when {
                    char.isDigit() -> x += char.digitToInt()
                    x < 8 -> {
                        val position = Position(x = x, y = y)
                        piecePositionsMap[position] = Piece(fenName = char)
                        x++
                    }

                    else -> throw IllegalArgumentException(
                        "Ошибка в классе ${this::class.simpleName}, в функции 'getPiecePosition'. " +
                                "Координата 'x' выходит за допустимые пределы"
                    )
                }
            }
            if (x != 8) throw IllegalArgumentException(
                "Ошибка в классе ${this::class.simpleName}, в функции 'getPiecePosition'. " +
                        "FEN нотация некорректна: общее количество клеток в ряду должно быть равным 8."
            )
        }
        return PiecePosition(piecePositions = piecePositionsMap)
    }
}