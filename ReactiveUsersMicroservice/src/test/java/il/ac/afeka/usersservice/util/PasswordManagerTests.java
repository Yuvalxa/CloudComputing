package il.ac.afeka.usersservice.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PasswordManagerTests {
    private final String PASSWORD = "aVeryS3cretPa55w0rd!";

    @Test
    public void encryptingPassword() {
        // given a password
        // when password is encrypted
        String encrypted = PasswordManager.encrypt(PASSWORD);
        // then an encrypted password is returned
        assertThat(encrypted)
                .isNotNull()
                .isBase64()
                .isNotEqualTo(PASSWORD);
    }

    @Test
    public void encryptingNull() {
        // given a null password
        // when password is encrypted
        String encrypted = PasswordManager.encrypt(null);
        // then null is returned
        assertThat(encrypted).isNull();
    }

    @Test
    public void acceptingCorrectPassword() {
        String encrypted = PasswordManager.encrypt(PASSWORD);
        // given a correct plain password
        // when password is validated
        // then password is correct
        assertTrue(PasswordManager.verify(PASSWORD, encrypted));
    }

    @Test
    public void rejectingWrongPassword() {
        String encrypted = PasswordManager.encrypt(PASSWORD);
        // given an incorrect plain password
        // when password is validated
        // then password is incorrect
        assertFalse(PasswordManager.verify("invalidPassword", encrypted));
    }
}
