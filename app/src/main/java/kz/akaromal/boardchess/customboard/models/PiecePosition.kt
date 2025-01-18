package kz.akaromal.boardchess.customboard.models

data class PiecePosition (
    val piecePositions: HashMap<Position, Piece> = HashMap(),
)