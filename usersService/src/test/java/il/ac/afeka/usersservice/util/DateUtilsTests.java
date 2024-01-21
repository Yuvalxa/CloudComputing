package il.ac.afeka.usersservice.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTests {

    @Test
    public void acceptsValidDates() {
        // given valid dates
        String[] dates = {
                "31-12-2024",
                "01-01-2024",
        };
        // when date is validated
        Stream<Boolean> resultsStream = Arrays.stream(dates).map(DateUtils::isValidDate);
        // then date accepted
        assertThat(resultsStream).containsOnly(true);
    }

    @Test
    public void rejectsInvalidDates() {
        // given invalid dates
        String[] dates = {
                "32-12-2024",
                "01-00-2024",
                "notadate",
                "",
                "null",
                null
        };
        // when date is validated
        Stream<Boolean> resultsStream = Arrays.stream(dates).map(DateUtils::isValidDate);
        // then date rejected
        assertThat(resultsStream).containsOnly(false);
    }

    @Test
    public void rejectsValidBasedOnFormat() {
        // given valid dates with invalid format
        String[] dates = {
                "31/12/2024",
                "01.01.2024",
                "2024-01-01",
                "01-02-2022 10:30:00"
        };
        // when date is validated
        Stream<Boolean> resultsStream = Arrays.stream(dates).map(DateUtils::isValidDate);
        // then date rejected
        assertThat(resultsStream).containsOnly(false);
    }

    @Test
    public void parsingToCorrectFormat() {
        LocalDate expectedOutput = LocalDate.EPOCH;
        //given valid date string
        String dateToParse = "01-01-1970";
        // when string is parsed to date
        LocalDate outputDate = DateUtils.parseDate(dateToParse);
        // then a correct LocalDate object returned
        assert (outputDate).isEqual(expectedOutput);
    }

    @Test
    public void stringifiesToCorrectFormat() {
        // given a valid date object
        LocalDate givenDate = LocalDate.of(2024, 1, 1);
        // when converted to a string
        String convertedDate = DateUtils.toValidDateString(givenDate);
        // then a correctly formatted date string is returned
        assert (convertedDate).equals("01-01-2024");
    }
}
