package chess.repository;

import chess.domain.piece.Color;
import chess.domain.room.Room;
import java.util.List;
import java.util.Optional;

public interface RoomDao {

    long save(Room room);

    List<Room> findAllByUserId(long userId);

    Optional<Room> findByUserIdAndName(long userId, String name);

    void updateWinner(long id, Color winner);
}
