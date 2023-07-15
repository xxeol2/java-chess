package chess.domain.piece;

import chess.domain.game.Score;
import chess.domain.position.Move;

public abstract class Piece {

    protected final Color color;
    protected final PieceType type;

    public Piece(Color color, PieceType type) {
        validateColor(color);
        this.color = color;
        this.type = type;
    }

    private void validateColor(Color color) {
        if (color == Color.NONE) {
            throw new IllegalArgumentException("없는 색깔입니다.");
        }
    }

    public abstract boolean canMove(Move move, Piece targetPiece);

    public boolean isSameColor(Piece target) {
        if (target == null) {
            return false;
        }
        return color == target.color;
    }

    public boolean checkColor(Color turn) {
        return this.color == turn;
    }

    public boolean checkType(PieceType type) {
        return this.type == type;
    }

    public String getName() {
        return type.name();
    }

    public Color getColor() {
        return color;
    }

    public Score getScore() {
        return type.getScore();
    }
}
