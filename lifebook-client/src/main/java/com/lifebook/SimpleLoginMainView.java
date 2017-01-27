package com.lifebook;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 * Created by monju on 27-Jan-17.
 */
@SpringComponent
@UIScope
public class SimpleLoginMainView extends CustomComponent implements View {

    public static final String NAME="";

    Label text = new Label();

    Button logout = new Button("Logout", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            //logout the user
            getSession().setAttribute("user",null);
            getUI().getNavigator().navigateTo(SimpleLoginView.NAME);
        }
    });

    public SimpleLoginMainView() {
        setCompositionRoot(new CssLayout(text, logout));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //get the username from the session
        String username = String.valueOf(getSession().getAttribute("user"));

        //And show the username
        text.setValue("Hello "+username);
    }
}
