package com.lifebook;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by monju on 27-Jan-17.
 */
@SpringComponent
@UIScope
public class SimpleLoginView extends CustomComponent implements View, Button.ClickListener {

    public static final String NAME="login";

    private final TextField user;

    private final PasswordField password;

    private final Button loginButton;

    public SimpleLoginView() {

        setSizeFull();
        //create the user input field
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username (eg. joe@email.com)");
        user.addValidator(new EmailValidator("Username must be an email address"));
        user.setInvalidAllowed(false);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");
        password.setNullSettingAllowed(false);

        loginButton = new Button("Login", this);

        //Add both to a panel
        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        String captionText="";
        //fields.setHeight("100%");
        fields.setCaption(captionText);
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.setSizeUndefined();


        //the view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setHeight("100%");
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //focus the username field when user arrives to the login view
        user.focus();
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        /*
        * Validate the fields using the navigator.
        * By using validators for the fields we reduce the amount of queries we have to use to the database
        * for wrongly entered passwords
        * */

        if(!user.isValid() || !password.isValid()){
            return;
        }

        String username = user.getValue();
        String password = this.password.getValue();

        /*
        * Validate username and password with database here.
        * For examples sake, I use a dummy username and password
        * */

        boolean isValid = username.equals("x@gmail.com") && password.equals("passw0rd");

        if(isValid){
            getSession().setAttribute("user", username);
            getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);
        }else{
            this.password.setValue(null);
            this.password.focus();
        }
    }
}
