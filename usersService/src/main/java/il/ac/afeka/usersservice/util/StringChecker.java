package il.ac.afeka.usersservice.util;

import java.util.regex.Pattern;

public class StringChecker {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
    private static final Pattern DEPARTMENT_PATTERN = Pattern
            .compile("^(?!#&]}\\s\\$\\^\\*\\(\\)\\[\\{)[a-zA-Z][a-zA-Z&\\s]*$");

    public static boolean isValidUserName(String inputString) {
        return NAME_PATTERN.matcher(inputString).matches();
    }

    public static boolean isValidDepartmentName(String inputString) {
        return DEPARTMENT_PATTERN.matcher(inputString).matches();
    }
}

