package com.lifebook;

import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by monju on 27-Jan-17.
 */
public class PasswordValidator extends AbstractValidator<String> {

    public PasswordValidator() {
        super("The password provided is not valid");
    }

    @Override
    protected boolean isValidValue(String s) {
        /*
        * Password must be at least 8 characters long and contain at
        * least one number
        * */

        if(s!=null && (s.length()<8 || !s.matches(".*\\d.*"))){
            return false;
        }
        return true;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
