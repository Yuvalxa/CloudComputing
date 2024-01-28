package il.ac.afeka.usersservice.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCheckerTests {

    @Test
    public void acceptsValidUserNames() {
        // given valid names
        String[] strings = {
                "Prince",
                "Michal Jackson",
                "Leonard Norman Cohen"
        };
        // when strings are evaluated
        Stream<Boolean> resultStream = Arrays.stream(strings).map(StringChecker::isValidUserName);
        // then strings are accepted
        assertThat(resultStream).containsOnly(true);
    }

    @Test
    public void rejectsInvalidUserNames() {
        // given strings with  numbers
        String[] strings = {
                "U2",
                "50 cent",
                "5 Seconds of Summer",
                "",
        };
        // when strings are evaluated
        Stream<Boolean> resultStream = Arrays.stream(strings).map(StringChecker::isValidUserName);
        // then strings are rejected
        assertThat(resultStream).containsOnly(false);
    }

    @Test
    public void acceptsValidDepartmentNames() {
        // given valid names
        String[] strings = {
                "R&D",
                "Marketing",
                "HR",
                "Software Development"
        };
        // when strings are evaluated
        Stream<Boolean> resultStream = Arrays.stream(strings).map(StringChecker::isValidDepartmentName);
        // then strings are accepted
        assertThat(resultStream).containsOnly(true);
    }

    @Test
    public void rejectsInvalidDepartmentNames() {
        // given strings with  numbers
        String[] strings = {
                "Customer Success!",
                "& Accounting",
                "",
        };
        // when strings are evaluated
        Stream<Boolean> resultStream = Arrays.stream(strings).map(StringChecker::isValidDepartmentName);
        // then strings are rejected
        assertThat(resultStream).containsOnly(false);
    }
}
