package ua.demitt.homework.hellogwthibernate.server.util;

import ua.demitt.homework.hellogwthibernate.server.dao.entity.User;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

public class EntityDtoConverter {

    private EntityDtoConverter() {   }

    public static UserDto convert(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        return userDto;
    }

}
