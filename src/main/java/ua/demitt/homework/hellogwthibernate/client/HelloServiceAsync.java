package ua.demitt.homework.hellogwthibernate.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

public interface HelloServiceAsync {
    void myMethod(String login, String password, AsyncCallback<UserDto> async);
}
