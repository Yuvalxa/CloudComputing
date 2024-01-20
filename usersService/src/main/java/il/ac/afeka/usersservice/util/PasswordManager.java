package il.ac.afeka.usersservice.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordManager {
    private static final StrongPasswordEncryptor manager = new StrongPasswordEncryptor();

    public static String encryptPassword(String password) {
        return manager.encryptPassword(password);
    }

    public static boolean validatePassword(String inputPassword, String storedPassword) {
        return manager.checkPassword(inputPassword, storedPassword);
    }
}
