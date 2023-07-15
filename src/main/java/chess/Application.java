package chess;

import chess.application.GameService;
import chess.application.RoomService;
import chess.application.UserService;
import chess.application.dao.MoveDao;
import chess.application.dao.RoomDao;
import chess.application.dao.UserDao;
import chess.controller.GameController;
import chess.controller.RoomController;
import chess.controller.UserController;
import chess.infrastructure.JdbcMoveDao;
import chess.infrastructure.JdbcRoomDao;
import chess.infrastructure.JdbcUserDao;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        UserDao userDao = new JdbcUserDao();
        RoomDao roomDao = new JdbcRoomDao();
        MoveDao moveDao = new JdbcMoveDao();

        UserService userService = new UserService(userDao);
        RoomService roomService = new RoomService(roomDao);
        GameService gameService = new GameService(roomDao, moveDao);

        GameController gameController = new GameController(inputView, outputView, gameService);
        RoomController roomController = new RoomController(inputView, outputView, roomService, gameController);
        UserController userController = new UserController(inputView, outputView, userService, roomController);

        userController.login();

        scanner.close();
    }
}
