package chess.application;

import chess.application.dao.MoveDao;
import chess.application.dao.RoomDao;
import chess.domain.piece.Color;
import chess.domain.position.Move;
import java.util.List;

public class GameService {

    private final RoomDao roomDao;
    private final MoveDao moveDao;

    public GameService(RoomDao roomDao, MoveDao moveDao) {
        this.roomDao = roomDao;
        this.moveDao = moveDao;
    }

    public List<Move> findMoves(long roomId) {
        return moveDao.findAllByRoomId(roomId);
    }

    public void createMove(long roomId, String source, String target) {
        moveDao.save(roomId, Move.from(source, target));
    }

    public void updateWinner(long roomId, Color winner) {
        roomDao.updateWinner(roomId, winner);
    }
}
