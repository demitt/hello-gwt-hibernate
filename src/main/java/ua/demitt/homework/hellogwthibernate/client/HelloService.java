package ua.demitt.homework.hellogwthibernate.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;

@RemoteServiceRelativePath("helloService")
public interface HelloService extends RemoteService {
    Response login(String login, String password);
}
