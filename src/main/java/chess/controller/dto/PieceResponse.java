package chess.controller.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceResponse {

    private final int fileIndex;
    private final int rankIndex;
    private final String color;
    private final String type;

    private PieceResponse(int fileIndex, int rankIndex, String color, String type) {
        this.fileIndex = fileIndex;
        this.rankIndex = rankIndex;
        this.color = color;
        this.type = type;
    }

    public static PieceResponse of(Position position, Piece piece) {
        int fileIndex = position.getFileIndex();
        int rankIndex = position.getRankIndex();
        String color = piece.getColor().name();
        String pieceType = piece.getName();
        return new PieceResponse(fileIndex, rankIndex, color, pieceType);
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
