package chess.controller;

import chess.application.UserService;
import chess.domain.user.User;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.ready.ReadyCommandType;
import chess.view.dto.ready.ReadyRequest;
import java.util.List;

public class UserController {

    private final InputView inputView;
    private final OutputView outputView;
    private final UserService userService;
    private final RoomController roomController;

    public UserController(InputView inputView, OutputView outputView, UserService userService,
            RoomController roomController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
        this.roomController = roomController;
    }

    public void login() {
        long userId = IllegalArgumentExceptionHandler.repeat(this::selectUser);
        roomController.joinRoom(userId);
    }

    private long selectUser() {
        printAskUserNameMessages();
        ReadyRequest request = inputView.askReadyCommand();
        if (request.getCommandType() == ReadyCommandType.USE) {
            User user = userService.findByName(request.getName());
            return user.getId();
        }
        return userService.create(request.getName());
    }

    private void printAskUserNameMessages() {
        List<String> userNames = userService.findUserNames();
        if (!userNames.isEmpty()) {
            outputView.printSelectUserMessage(userNames);
        }
        outputView.printCreateUserMessage();
    }
}
