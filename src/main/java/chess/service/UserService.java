package chess.service;

import chess.domain.user.User;
import chess.repository.UserDao;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<String> findUserNames() {
        List<User> users = userDao.findAll();
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public User findByName(String name) {
        return userDao.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    public long create(String name) {
        userDao.findByName(name)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 사용중인 이름입니다.");
                });
        User user = new User(name);
        return userDao.save(user);
    }
}
