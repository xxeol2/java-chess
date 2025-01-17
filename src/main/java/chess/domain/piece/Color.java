package chess.domain.piece;

public enum Color {
    BLACK {
        @Override
        public Color change() {
            return WHITE;
        }
    },
    WHITE {
        @Override
        public Color change() {
            return BLACK;
        }
    },
    NONE {
        @Override
        public Color change() {
            throw new UnsupportedOperationException("없는 색깔입니다.");
        }
    };

    public abstract Color change();

    public static Color calculateTurn(int moveCount) {
        if (isEven(moveCount)) {
            return WHITE;
        }
        return BLACK;
    }

    private static boolean isEven(int moveCount) {
        return moveCount % 2 == 0;
    }
}
