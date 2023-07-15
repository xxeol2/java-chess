package chess.view.dto.category;

import chess.view.InputView;

public enum CategoryCommandType {

    RECORD, PLAY;

    public static CategoryCommandType from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE);
        }
    }
}
