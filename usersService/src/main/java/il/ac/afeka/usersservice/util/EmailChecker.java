package il.ac.afeka.usersservice.util;

import java.util.regex.*;

public class EmailChecker {
    public static boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        try {
            return Pattern.matches(EMAIL_PATTERN, email);
        } catch (Exception e) {
            return false;
        }
    }
}
