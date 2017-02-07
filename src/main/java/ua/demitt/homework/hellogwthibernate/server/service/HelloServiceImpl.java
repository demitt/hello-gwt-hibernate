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

import javax.servlet.http.HttpServletRequest;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {

    private static final Logger serverLogger = LogManager.getLogger(HelloServiceImpl.class);
    static UserDao userDao; //package access for testing

    private static final String SESSION_KEYNAME = "userId";

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

        addSessionKey(user.getId());
        return new Response(ResponseCode.OK, user.getName());
    }

    @Override
    public Response silentLogin() {
        serverLogger.info("Starts.");
        Integer userId = getSessionKey();
        serverLogger.info("Find session key (userId) " + userId);
        UserDto user;
        if (userId == null || (user = userDao.findUser(userId)) == null) {
            serverLogger.info("User was not found");
            return new Response(ResponseCode.USER_NOT_FOUND);
        }

        serverLogger.info("User was found, username = " + user.getName());

        return new Response(ResponseCode.OK, user.getName());
    }

    @Override
    public void logout() {
        removeSessionKey();
        serverLogger.info("Session key has been removed");
    }


    private void addSessionKey(int userId) {
        HttpServletRequest request = this.getThreadLocalRequest();
        if (request == null) {
            //We are in test probably
            return;
        }
        request.getSession().setAttribute(SESSION_KEYNAME, userId);
    }

    private void removeSessionKey() {
        HttpServletRequest request = this.getThreadLocalRequest();
        if (request == null) {
            //We are in test probably
            return;
        }
        request.getSession().removeAttribute(SESSION_KEYNAME);
    }

    private Integer getSessionKey() {
        HttpServletRequest request = this.getThreadLocalRequest();
        if (request == null) {
            //We are in test probably
            return null;
        }
        return (Integer) request.getSession().getAttribute(SESSION_KEYNAME);
    }
}
