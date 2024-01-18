package il.ac.afeka.usersservice.boundaries;

public class NameBoundary {
    private String first;
    private String last;

    public NameBoundary() {}

    public NameBoundary(String firstname, String lastname) {
        this.first = firstname;
        this.last = lastname;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "NameBoundary {" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
