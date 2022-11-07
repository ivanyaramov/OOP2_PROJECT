package sample.validation;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

public class ValidationUtil {
   private static UserService userService = new UserServiceImpl();

    public static boolean validatePassword(PasswordField passwordFXML, PasswordField passwordRepeatFXML, Label passwordRepeatLabelFXML){
        if(passwordFXML.getText().trim().length()<6)
        {
            passwordRepeatLabelFXML.setText("Password is less than 6 symbols, please change it");
            return false;
        }
        if(!passwordFXML.getText().equals(passwordRepeatFXML.getText()))
        {
            passwordRepeatLabelFXML.setText("Passwords don't match \r\nPlease correct the password to match");
            return false;
        }
        return true;
    }

    public static boolean validateUserNameNotTaken(String username, Label errorFXML){
        if(userService.personExistsByUsername(username)) {
            errorFXML.setText("Username is already taken.");
            return false;
        }
        return true;
    }
}
