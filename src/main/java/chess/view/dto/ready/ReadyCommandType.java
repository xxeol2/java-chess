package chess.view.dto.ready;

import chess.view.InputView;

public enum ReadyCommandType {

    USE, CREATE;

    public static ReadyCommandType from(String input) {
        try {
            return valueOf(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE);
        }
    }
}
