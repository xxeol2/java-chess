package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiagonalStrategyTest {

    private MoveStrategy strategy;

    @BeforeEach
    void setUpStrategy() {
        strategy = DiagonalStrategy.instance();
    }

    @DisplayName("대각선 여러 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c2", "b1", "c4", "b5", "e2", "f1", "e4", "f5"})
    void canMove_Diagonal_Infinite(String position) {
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(strategy.canMove(move)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "b2"})
    void canNotMove(String position) {
        Move move = new Move(new Position("d3"), new Position(position));

        assertThat(strategy.canMove(move)).isFalse();
    }
}
