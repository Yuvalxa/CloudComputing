package il.ac.afeka.usersservice.boundaries;

public class NewUserBoundary extends UserBoundary {
    private String password;

    public NewUserBoundary() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String userBoundaryString = super.toString();
        return "New" +
                userBoundaryString.substring(0, userBoundaryString.length() - 1)  + // removes '}' in parent's toString()
                ", password=" + this.password +
                '}';
    }
}
