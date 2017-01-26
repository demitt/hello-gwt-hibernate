package ua.demitt.homework.hellogwthibernate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ua.demitt.homework.hellogwthibernate.client.HelloService;
import ua.demitt.homework.hellogwthibernate.client.HelloServiceAsync;
import ua.demitt.homework.hellogwthibernate.client.i18n.Constants;
import ua.demitt.homework.hellogwthibernate.shared.Const;
import ua.demitt.homework.hellogwthibernate.shared.response.Response;
import ua.demitt.homework.hellogwthibernate.shared.response.ResponseCode;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginForm extends PopupPanel {

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormUiBinder extends UiBinder<HorizontalPanel, LoginForm> {  }
    private static LoginFormUiBinder uiBinder = GWT.create(LoginFormUiBinder.class);

    private Constants constants = GWT.create(Constants.class);
    private HelloServiceAsync helloService = GWT.create(HelloService.class);
    private static Logger logger = Logger.getLogger("LoginFormLogger");

    private Widget root;
    AsyncCallback callback;

    @UiField //(provided = true)
    Button loginButton;
    @UiField
    TextBox login;
    @UiField
    PasswordTextBox password;


    public LoginForm() {
        this.root = uiBinder.createAndBindUi(this);
        add(this.root);
        createAsyncCallback();
        logger.addHandler(new ConsoleLogHandler());
        logger.log(Level.INFO, "Login form was loaded");
    }

    @UiHandler("loginButton")
    void submitLoginForm(ClickEvent event) {
        logger.log(Level.INFO, "Login button was pressed");
        doLogin();
    }

    @UiHandler({"login", "password"})
    void pressEnter(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            logger.log(Level.INFO, "Enter key was pressed");
            doLogin();
        }
    }


    @SuppressWarnings("unchecked")
    private void doLogin() {
        String login = this.login.getText();
        String password = this.password.getText();
        logger.log(Level.INFO, "Login = " + login + ", password = " + password);
        this.helloService.login(login, password, this.callback);
    }

    private void createAsyncCallback() {
        this.callback = new AsyncCallback() {
            @Override
            public void onSuccess(Object o) {
                Response response = (Response) o;
                clearFields();
                if (response.getCode() == ResponseCode.USER_NOT_FOUND) {
                    logger.log(Level.INFO, "User was not found");
                    return;
                }

                logger.log(Level.INFO, "User was found");

                GeneralPage generalPage = new GeneralPage(response.getUserName(), root);
                RootPanel content = RootPanel.get(Const.CONTENT_ID);
                content.clear();
                content.add(generalPage);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert(constants.loginRequestError());
            }
        };
    }

    private void clearFields() {
        login.setText("");
        password.setText("");
        logger.log(Level.INFO, "Login form's fields was cleared");
    }
}