package ua.demitt.homework.hellogwthibernate.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.server.dao.impl.UserDaoImpl;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {

    private static UserDao userDao;

    public HelloServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public UserDto myMethod(String login, String password) {
        UserDto user = userDao.findUser(login);
        //return user; //tmp

        //tmp
        UserDto emptyUser = new UserDto();
        emptyUser.setName("NULL");

        if (user == null) {
            return emptyUser; //tmp
        }

        //TODO: хеширование, сравнение...

        //tmp:
        if (user.getPassword().equals(password)) {
            return user;
        }
        return emptyUser; //tmp
    }
}
