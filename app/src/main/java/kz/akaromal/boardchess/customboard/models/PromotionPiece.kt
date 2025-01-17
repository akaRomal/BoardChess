package kz.akaromal.boardchess.customboard.models

import kz.akaromal.boardchess.BuildConfig

enum class PromotionPiece {
    QUEEN,
    KNIGHT,
    ROOK,
    BISHOP;

    companion object {
        fun getPiecesForColor(color: PromotionPieceColor): List<Char> {
            return when (color) {
                PromotionPieceColor.WHITE -> listOf(
                    BuildConfig.FEN_WHITE_QUEEN,
                    BuildConfig.FEN_WHITE_KING,
                    BuildConfig.FEN_WHITE_ROOK,
                    BuildConfig.FEN_WHITE_BISHOP,
                )

                PromotionPieceColor.BLACK -> listOf(
                    BuildConfig.FEN_BLACK_QUEEN,
                    BuildConfig.FEN_BLACK_KING,
                    BuildConfig.FEN_BLACK_ROOK,
                    BuildConfig.FEN_BLACK_BISHOP,
                )
            }
        }
    }
}
