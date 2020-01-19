package valeriamoscoso.ioc.hanguldaebak.presentation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Utils class with methots to check user login information
 * @author Valeria Moscoso León
 * */
public class UtilsLogin {

    private static final int MIN_PASSWORD_LENGHT = 8;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);



    /**
     * check if lenght is valid
     * @author Valeria Moscoso León
     * @return boolean
     * */
    public static Boolean isValidLenght(String password) {

        if (password.length() >= MIN_PASSWORD_LENGHT) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if email is empty and if is the right pattern
     * @author Valeria Moscoso León
     * @return boolean indicating if is valid or not
     * */

    public static boolean isValidEmail(String email) {

        return email != null && VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches();


    }

    /**
     * Method to chek if e-mail is valid usig a pattern
     * @author Valeria Moscoso León
     * @return boolean indicating if the email is valid or not
     * */
    public static Boolean isValidPassword(String password){

        Pattern pattern;
        Matcher matcher;

        String PWD_PATTER = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";

        pattern = Pattern.compile(PWD_PATTER);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }



}
