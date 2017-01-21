package ua.demitt.homework.hellogwthibernate.server.dao;

import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

public interface UserDao {
    UserDto findUser(String login);
}
