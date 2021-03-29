package chess.domain.piece;

import java.util.Arrays;

public enum Direction {
    TOP(0, 1),
    RIGHT_TOP(1, 1),
    RIGHT(1, 0),
    RIGHT_BOTTOM(1, -1),
    BOTTOM(0, -1),
    LEFT_BOTTOM(-1, -1),
    LEFT(-1, 0),
    LEFT_TOP(-1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction getDirectionFromWeight(int horizontalWeight, int verticalWeight) {
        final int x = distinctDirection(horizontalWeight);
        final int y = distinctDirection(verticalWeight);

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.x == x && direction.y == y)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int distinctDirection(int weight) {
        return Integer.compare(weight, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}