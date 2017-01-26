package ua.demitt.homework.hellogwthibernate.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.client.ui.RootPanel;
import ua.demitt.homework.hellogwthibernate.client.ui.LoginForm;
import ua.demitt.homework.hellogwthibernate.shared.Const;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Hello implements EntryPoint {

    private static Logger logger = Logger.getLogger("HelloLogger");

    public void onModuleLoad() {
        logger.addHandler(new ConsoleLogHandler());
        logger.log(Level.INFO, "Hello-module was loaded");
        //Label theGreeting = new Label("Hello World!");
        //Button helloButton = new Button("БесполезнаяКнопка");

        LoginForm loginForm = new LoginForm();

        /*HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setBorderWidth(1);
        horizontalPanel.add(theGreeting);
        horizontalPanel.add(helloButton);*/

        //RootPanel.get("content").add(horizontalPanel);
        RootPanel.get(Const.CONTENT_ID).add(loginForm);
        //setUpGui();
    }


    /*private void setUpGui() {

    }*/

}
