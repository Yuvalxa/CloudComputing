package il.ac.afeka.usersservice.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordManager {
    private static final StrongPasswordEncryptor manager = new StrongPasswordEncryptor();

    public static String encrypt(String password) {
        return manager.encryptPassword(password);
    }

    /**
     * Checks a plain password against an encrypted one to see if they match.
     * @param inputPassword the plain password to check.
     * @param storedPassword the encrypted password to be checked against.
     * @return true if passwords match, false otherwise.
     */
    public static boolean verify(String inputPassword, String storedPassword) {
        return manager.checkPassword(inputPassword, storedPassword);
    }

    /**
     * validates a password for meeting the required password security requirements.
     * @param inputPassword plain password to be validated.
     * @return true if password meets the security requirements, false otherwise.
     */
    public static boolean validate(String inputPassword) {
        return false; // TODO
    }
}
