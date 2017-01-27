package com.lifebook;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

/**
 * Created by monju on 27-Jan-17.
 */
@SpringUI
@Theme("valo")
public class SimpleLoginUI extends UI{
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        new Navigator(this,this);

        getNavigator().addView(SimpleLoginView.NAME, SimpleLoginView.class);

        getNavigator().addView(SimpleLoginMainView.NAME, SimpleLoginMainView.class);

        getNavigator().addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent viewChangeEvent) {
                // check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user")!=null;
                boolean isLoginView = viewChangeEvent.getNewView() instanceof SimpleLoginView;

                if(!isLoggedIn && !isLoginView){
                    //Redirect to login view always if a user has not yet
                    //logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;
                }else if(isLoggedIn && isLoginView){
                    return false;
                }
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent viewChangeEvent) {

            }
        });

    }
}
