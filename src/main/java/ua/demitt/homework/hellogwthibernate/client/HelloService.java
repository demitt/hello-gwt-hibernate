package ua.demitt.homework.hellogwthibernate.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.demitt.homework.hellogwthibernate.shared.Response;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

@RemoteServiceRelativePath("helloService")
public interface HelloService extends RemoteService {
    UserDto myMethod(String login, String password); //UserDto -> Response
}
