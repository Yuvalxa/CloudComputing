package il.ac.afeka.usersservice.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class EmailCheckerTests {

    @Test
    public void acceptsValidEmail() {
        // given valid emails
        String[] emails = {
            "user@example.com",
            "test.email@domain.co",
            "demo_mail@prefix.domain.il"
        };
        // when email is validated
        Stream<Boolean> resultStream = Arrays.stream(emails).map(EmailChecker::isValidEmail);
        // then email accepted
        assertThat(resultStream).containsOnly(true);
    }

    @Test
    public void rejectsInvalidEmail() {
        // given invalid emails
        String[] emails = {
                "invalid_email.com",
                "demo.mail.domain.il",
                "user@.com",
                "user@domain.",
                "user@domain_com",
        };
        // when email is validated
        Stream<Boolean> resultStream = Arrays.stream(emails).map(EmailChecker::isValidEmail);
        // then email rejected
        assertThat(resultStream).containsOnly(false);
    }

    @Test
    public void rejectsNullEmail() {
        // given null input
        String email = null;
        // when email is validated
        boolean result = EmailChecker.isValidEmail(null);
        // then email rejected
        assertThat(result).isFalse();
    }

    @Test
    public void rejectsEmptyEmail() {
        // given empty email string
        String email = "";
        //when email is validated
        boolean result = EmailChecker.isValidEmail(email);
        // then email rejected
        assertThat(result).isFalse();
    }
}
