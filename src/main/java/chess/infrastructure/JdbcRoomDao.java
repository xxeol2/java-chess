package chess.infrastructure;

import chess.application.dao.RoomDao;
import chess.config.JdbcConnection;
import chess.domain.piece.Color;
import chess.domain.room.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcRoomDao implements RoomDao {

    @Override
    public long save(Room room) {
        final String query = "INSERT INTO Room (user_id, name, winner) VALUES (?, ?, ?)";
        try (final Connection connection = JdbcConnection.getConnection();
                final PreparedStatement preparedStatement
                        = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, room.getUserId());
            preparedStatement.setString(2, room.getName());
            preparedStatement.setString(3, room.getWinner().name());
            preparedStatement.executeUpdate();
            return getGeneratedId(preparedStatement);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private long getGeneratedId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public List<Room> findAllByUserId(long userId) {
        final String query = "SELECT id, user_id, name, winner FROM Room WHERE user_id = ?";
        try (final Connection connection = JdbcConnection.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createRooms(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private List<Room> createRooms(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            rooms.add(createRoom(resultSet));
        }
        return rooms;
    }

    @Override
    public Optional<Room> findByUserIdAndName(long userId, String name) {
        final String query = "SELECT id, user_id, name, winner FROM Room WHERE user_id = ? AND name = ?";
        try (final Connection connection = JdbcConnection.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createRoom(resultSet));
            }
            return Optional.empty();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void updateWinner(long id, Color winner) {
        final String query = "UPDATE Room SET winner = ? WHERE id = ?";
        try (final Connection connection = JdbcConnection.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, winner.name());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Room createRoom(ResultSet resultSet) throws SQLException {
        return new Room(
                (long) resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("name"),
                Color.valueOf(resultSet.getString("winner"))
        );
    }
}
