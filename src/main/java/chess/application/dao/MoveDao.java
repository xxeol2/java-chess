package chess.application.dao;

import chess.domain.position.Move;
import java.util.List;

public interface MoveDao {

    void save(long roomId, Move move);

    List<Move> findAllByRoomId(long roomId);
}
