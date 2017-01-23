package ua.demitt.homework.hellogwthibernate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.client.HelloServiceAsync;
import ua.demitt.homework.hellogwthibernate.client.i18n.Constants;
import ua.demitt.homework.hellogwthibernate.shared.dto.UserDto;

public class LoginForm extends PopupPanel {

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormUiBinder extends UiBinder<HTMLPanel, LoginForm> {  }

    private static LoginFormUiBinder loginFormUiBinder = GWT.create(LoginFormUiBinder.class);

    private Constants constants = GWT.create(Constants.class);

    @UiField //(provided = true)
    Button loginButton;

    @UiField
    TextBox login;

    @UiField
    PasswordTextBox password;

    //@UiField
    //SpanElement message;

    private HelloServiceAsync helloService = GWT.create(HelloService.class);
    //^ it is safe to cache the instantiated service proxy


    public LoginForm() {
        //initWidget(loginFormUiBinder.createAndBindUi(this));

        //setStyleName("");

        //message.setInnerHTML("Go!");

        //loginButton = new Button();
        //loginButton.setText("sommmme button");

        add(loginFormUiBinder.createAndBindUi(this));
    }

    @UiHandler("loginButton")   //@UiHandler({"loginButton", "btnSmth"})
    void submitLoginForm(ClickEvent event) {
        AsyncCallback callback = new AsyncCallback() {
            @Override
            public void onSuccess(Object o) {
                UserDto user = (UserDto) o;
                Window.alert("[" + constants.greetingDay() + "] , " + user.getLogin() + "!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Ошибка запроса: " + throwable.getClass().getName());
            }
        };

        //this.login.setText("ivan");
        String login = this.login.getText();
        String password = this.password.getText();
        this.helloService.myMethod(login, password, callback);
    }

    /*@UiHandler("loginButton")
    void forBtn(MouseOverEvent event) {
        //Window.setTitle("123");
    }*/

}