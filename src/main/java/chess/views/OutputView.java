package chess.views;

import chess.dto.ChessBoardDto;
import chess.domain.result.Result;
import chess.domain.result.Score;

public class OutputView {
    private final static String NEW_LINE = System.lineSeparator();
    private final static String EMPTY = ".";

    public static void printInitialGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 상황 : status");
    }

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(NEW_LINE);
//
//        for (Column column : Column.values()) {
//            for (Row row : Row.values()) {
//               String display = Optional.ofNullable(chessBoard.get(Positions.of(row, column)))
//                        .map(Piece::getDisplay)
//                        .orElse(EMPTY);
//                stringBuilder.append(display);
//            }
//            stringBuilder.append(NEW_LINE);
//        }
//        System.out.println(stringBuilder.toString());
    }

    public static void printStatus(Result result) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Score score : result.getScores()) {
            stringBuilder.append(score.getPlayer());
            stringBuilder.append(" : ");
            stringBuilder.append(score.getScore());
            stringBuilder.append(NEW_LINE);
        }

        if (result.isDraw()) {
            stringBuilder.append("무승부");
        }

        if (!result.isDraw()) {
            stringBuilder.append("승자 : ");
            stringBuilder.append(result.getWinners());
        }

        System.out.println(stringBuilder.toString());
    }

    public static void printGameOver() {
        System.out.println("King이 잡혀서 게임을 종료합니다.");
    }
}