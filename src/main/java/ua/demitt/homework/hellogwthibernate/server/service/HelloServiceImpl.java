package ua.demitt.homework.hellogwthibernate.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.mindrot.jbcrypt.BCrypt;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.server.dao.impl.UserDaoImpl;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;
import ua.demitt.homework.hellogwthibernate.shared.response.ResponseCode;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {

    static UserDao userDao; //package access for testing

    public HelloServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public Response login(String login, String password) {
        UserDto user = userDao.findUser(login);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return new Response(ResponseCode.USER_NOT_FOUND);
        }

        return new Response(ResponseCode.OK, user.getName());
    }
}
