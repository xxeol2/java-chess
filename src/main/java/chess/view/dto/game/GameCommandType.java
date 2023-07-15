package chess.view.dto.game;

import chess.view.InputView;
import java.util.Set;

public enum GameCommandType {

    START, END, MOVE, STATUS;

    private static final Set<GameCommandType> SINGLE_COMMAND_TYPES = Set.of(START, END, STATUS);

    public static GameCommandType from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE);
        }
    }

    public boolean isSingleCommandType() {
        return SINGLE_COMMAND_TYPES.contains(this);
    }
}
