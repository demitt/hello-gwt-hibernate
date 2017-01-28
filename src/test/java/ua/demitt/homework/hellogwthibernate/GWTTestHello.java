package ua.demitt.homework.hellogwthibernate;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.server.service.HelloServiceImpl;

public class GWTTestHello extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "ua.demitt.homework.hellogwthibernate.HelloJUnit";
    }

    public void testAsyncService() {
        String login = "ivan";
        String password = "secret";

        Timer timer = new Timer() {
            public void run() {
                HelloService helloService = new HelloServiceImpl();
                helloService.login(login, password);
                finishTest();
            }
        };

        delayTestFinish(1000);
        timer.schedule(100);
    }
}