package ua.demitt.homework.hellogwthibernate.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class Hello implements EntryPoint {
    public void onModuleLoad() {
        //Label theGreeting = new Label("Hello World!");
        //Button helloButton = new Button("БесполезнаяКнопка");

        LoginForm loginForm = new LoginForm();

        /*HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setBorderWidth(1);
        horizontalPanel.add(theGreeting);
        horizontalPanel.add(helloButton);*/

        //RootPanel.get("content").add(horizontalPanel);
        RootPanel.get("content").add(loginForm);
        //setUpGui();
    }


    /*private void setUpGui() {

    }*/

}
