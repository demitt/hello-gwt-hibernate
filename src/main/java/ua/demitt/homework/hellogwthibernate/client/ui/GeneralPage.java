package ua.demitt.homework.hellogwthibernate.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import ua.demitt.homework.hellogwthibernate.client.Period;
import ua.demitt.homework.hellogwthibernate.client.i18n.Constants;
import ua.demitt.homework.hellogwthibernate.shared.Const;

import java.util.Date;

public class GeneralPage extends Composite {
    interface GeneralPageUiBinder extends UiBinder<HTMLPanel, GeneralPage> {   }
    private static GeneralPageUiBinder uiBinder = GWT.create(GeneralPageUiBinder.class);

    private Constants constants = GWT.create(Constants.class);

    @UiField
    SpanElement greeting;

    Widget loginForm;

    public GeneralPage(String userName, Widget loginForm) {
        initWidget(uiBinder.createAndBindUi(this));

        Period period = Period.getPeriod(new Date().getHours());
        String greeting = createGreeting(userName, period);

        this.greeting.setInnerText(greeting);
        this.loginForm = loginForm;
    }


    @UiHandler("logoutLink")
    void logout(ClickEvent event) {
        RootPanel content = RootPanel.get(Const.CONTENT_ID);
        content.clear();
        content.add(this.loginForm);
    }


    private String createGreeting(String userName, Period period) {
        String greeting;
        switch (period) {
            case MORNING:
                greeting = constants.greetingMorning();
                break;
            case DAY:
                greeting = constants.greetingDay();
                break;
            case EVENING:
                greeting = constants.greetingEvening();
                break;
            case NIGHT:
                greeting = constants.greetingNight();
                break;
            default:
                throw new IllegalArgumentException("Unknown period");
        }
        greeting += ", " + userName + ".";
        return greeting;
    }
}