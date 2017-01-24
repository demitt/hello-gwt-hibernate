package ua.demitt.homework.hellogwthibernate.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;

public interface HelloServiceAsync {
    void login(String login, String password, AsyncCallback<Response> async);
}
