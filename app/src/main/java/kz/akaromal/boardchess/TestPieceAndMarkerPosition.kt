package kz.akaromal.boardchess

import kz.akaromal.boardchess.customboard.models.Marker
import kz.akaromal.boardchess.customboard.models.MarkerPosition
import kz.akaromal.boardchess.customboard.models.Piece
import kz.akaromal.boardchess.customboard.models.PiecePosition
import kz.akaromal.boardchess.customboard.models.Position


val testMarkerPosition =
    MarkerPosition(
        markers = listOf(
            Marker.LastMove(position = Position(x = 3, y = 7)),
            Marker.LastMove(position = Position(x = 7, y = 3)),
            Marker.Check(position = Position(x = 4, y = 0)),
            Marker.PossibleMove(position = Position(x = 5, y = 1)),
        )
    )

val testPiecePosition =
    PiecePosition(
        pieces = listOf(
            Piece(coordinate = Position(x = 0, y = 6), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 1, y = 6), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 2, y = 6), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 3, y = 4), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 4, y = 4), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 5, y = 6), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 6, y = 6), fenName = BuildConfig.FEN_WHITE_PAWN),
            Piece(coordinate = Position(x = 7, y = 6), fenName = BuildConfig.FEN_WHITE_PAWN),

            Piece(coordinate = Position(x = 0, y = 7), fenName = BuildConfig.FEN_WHITE_ROOK),
            Piece(coordinate = Position(x = 1, y = 7), fenName = BuildConfig.FEN_WHITE_KNIGHT),
            Piece(coordinate = Position(x = 2, y = 7), fenName = BuildConfig.FEN_WHITE_BISHOP),
            Piece(coordinate = Position(x = 7, y = 3), fenName = BuildConfig.FEN_WHITE_QUEEN),
            Piece(coordinate = Position(x = 4, y = 7), fenName = BuildConfig.FEN_WHITE_KING),
            Piece(coordinate = Position(x = 2, y = 4), fenName = BuildConfig.FEN_WHITE_BISHOP),
            Piece(coordinate = Position(x = 6, y = 7), fenName = BuildConfig.FEN_WHITE_KNIGHT),
            Piece(coordinate = Position(x = 7, y = 7), fenName = BuildConfig.FEN_WHITE_ROOK),

            Piece(coordinate = Position(x = 0, y = 1), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 1, y = 1), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 2, y = 1), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 3, y = 1), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 4, y = 1), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 5, y = 2), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 6, y = 3), fenName = BuildConfig.FEN_BLACK_PAWN),
            Piece(coordinate = Position(x = 7, y = 1), fenName = BuildConfig.FEN_BLACK_PAWN),

            Piece(coordinate = Position(x = 0, y = 0), fenName = BuildConfig.FEN_BLACK_ROOK),
            Piece(coordinate = Position(x = 1, y = 0), fenName = BuildConfig.FEN_BLACK_KNIGHT),
            Piece(coordinate = Position(x = 2, y = 0), fenName = BuildConfig.FEN_BLACK_BISHOP),
            Piece(coordinate = Position(x = 3, y = 0), fenName = BuildConfig.FEN_BLACK_QUEEN),
            Piece(coordinate = Position(x = 4, y = 0), fenName = BuildConfig.FEN_BLACK_KING),
            Piece(coordinate = Position(x = 5, y = 0), fenName = BuildConfig.FEN_BLACK_BISHOP),
            Piece(coordinate = Position(x = 7, y = 2), fenName = BuildConfig.FEN_BLACK_KNIGHT),
            Piece(coordinate = Position(x = 7, y = 0), fenName = BuildConfig.FEN_BLACK_ROOK),
        )
    )