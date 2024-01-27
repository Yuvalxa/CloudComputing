package il.ac.afeka.usersservice.boundaries;

import il.ac.afeka.usersservice.util.StringChecker;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;

public class NameBoundary {
    private String first;
    private String last;

    public NameBoundary() {
    }

    public NameBoundary(String firstname, String lastname) {
        this.first = firstname;
        this.last = lastname;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        if (StringChecker.isValidUserName(first))
            this.first = first;
        else
            throw new InvalidInputException("Invalid first name");
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        if (StringChecker.isValidUserName(last))
            this.last = last;
        else
            throw new InvalidInputException("Invalid last name");
    }

    @Override
    public String toString() {
        return "NameBoundary {" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
