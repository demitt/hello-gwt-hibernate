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

public class LoginForm extends PopupPanel {

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormUiBinder extends UiBinder<HorizontalPanel, LoginForm> {  }
    private static LoginFormUiBinder uiBinder = GWT.create(LoginFormUiBinder.class);

    private Constants constants = GWT.create(Constants.class);

    private Widget root;

    @UiField //(provided = true)
    Button loginButton;
    @UiField
    TextBox login;
    @UiField
    PasswordTextBox password;

    private HelloServiceAsync helloService = GWT.create(HelloService.class);

    public LoginForm() {
        this.root = uiBinder.createAndBindUi(this);
        add(this.root);
    }

    @UiHandler("loginButton")
    @SuppressWarnings("unchecked")
    void submitLoginForm(ClickEvent event) {
        AsyncCallback callback = new AsyncCallback() {
            @Override
            public void onSuccess(Object o) {
                Response response = (Response) o;
                clearFields();
                if (response.getCode() == ResponseCode.USER_NOT_FOUND) {
                    return;
                }

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

        String login = this.login.getText();
        String password = this.password.getText();
        this.helloService.login(login, password, callback);
    }


    private void clearFields() {
        login.setText("");
        password.setText("");
    }
}