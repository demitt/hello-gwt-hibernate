package ua.demitt.homework.hellogwthibernate.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.server.dao.impl.UserDaoImpl;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;
import ua.demitt.homework.hellogwthibernate.shared.response.ResponseCode;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {

    private static final Logger serverLogger = LogManager.getLogger(HelloServiceImpl.class);
    static UserDao userDao; //package access for testing

    public HelloServiceImpl() {
        serverLogger.info("Was loaded");
        userDao = new UserDaoImpl();
    }

    @Override
    public Response login(String login, String password) {
        serverLogger.info("Starts. Find login \"" + login + "\", password \"" + password + "\"");
        UserDto user = userDao.findUser(login);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            serverLogger.info("User was not found or password has not much");
            return new Response(ResponseCode.USER_NOT_FOUND);
        }

        serverLogger.info("User was found, username = " + user.getName());
        return new Response(ResponseCode.OK, user.getName());
    }
}
