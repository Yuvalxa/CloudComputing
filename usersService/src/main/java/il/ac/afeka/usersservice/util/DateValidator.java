package il.ac.afeka.usersservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateValidator {
    public static boolean isValidDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // set strict parsing

        try {
            // Parse the input date and Check if the parsed date is the same as the input date.
            // This helps to catch cases where the input date is valid, but contains additional information
            // (e.g., "31-12-2022 10:30:00" would be invalid in this case)
            Date date = dateFormat.parse(inputDate);
            return inputDate.equals(dateFormat.format(date));
        } catch (ParseException | NullPointerException e) {
            return false;
        }
    }

    public static LocalDate dateParser(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}