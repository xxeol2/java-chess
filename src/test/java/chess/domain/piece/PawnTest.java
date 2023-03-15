package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest extends AbstractTestFixture {

    @DisplayName("백은 처음에 1칸 혹은 2칸 위로 움직일 수 있다.")
    @Test
    void whiteMoveUp_onceOrTwice_onFirstMove() {
        Piece pawn = Pawn.from(true);

        assertThat(pawn.canMove(createMove(UP, UP))).isTrue();
        assertThat(pawn.canMove(createMove(UP))).isTrue();
    }

    @DisplayName("흑은 처음에 1칸 혹은 2칸 아래로 움직일 수 있다.")
    @Test
    void blackMoveDown_onceOrTwice_onFirstMove() {
        Piece pawn = Pawn.from(false);

        assertThat(pawn.canMove(createMove(DOWN, DOWN))).isTrue();
        assertThat(pawn.canMove(createMove(DOWN))).isTrue();
    }

    @DisplayName("백은 아래로 움직일 수 없다")
    @Test
    void whiteCannotMoveDown() {
        Piece pawn = Pawn.from(true);

        assertThat(pawn.canMove(createMove(DOWN, DOWN))).isFalse();
        assertThat(pawn.canMove(createMove(DOWN))).isFalse();
    }

    @DisplayName("흑은 위로 움직일 수 없다")
    @Test
    void blackCannotMoveUp() {
        Piece pawn = Pawn.from(false);

        assertThat(pawn.canMove(createMove(UP, UP))).isFalse();
        assertThat(pawn.canMove(createMove(UP))).isFalse();
    }
}
