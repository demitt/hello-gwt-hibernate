package ua.demitt.homework.hellogwthibernate.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.mindrot.jbcrypt.BCrypt;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.server.dao.UserDao;
import ua.demitt.homework.hellogwthibernate.server.dao.impl.UserDaoImpl;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;
import ua.demitt.homework.hellogwthibernate.shared.response.ResponseCode;

import java.util.HashMap;
import java.util.Map;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {

    private static UserDao userDao;

    public HelloServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public Response login(String login, String password) {
        UserDto user = userDao.findUser(login);

        if (user == null) {
            return new Response(ResponseCode.USER_NOT_FOUND);
        }
        
        if ( BCrypt.checkpw(password, user.getPassword()) ) {
            Map<String, String> responseData = new HashMap<>();
            responseData.put("name", user.getName());
            responseData.put("period", "EVENING"); //tmp
            return new Response(ResponseCode.OK, responseData);
        }
        return new Response(ResponseCode.USER_NOT_FOUND);
    }
}
