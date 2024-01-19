package il.ac.afeka.usersservice.boundaries;

public class NewUserBoundary {
    private UserBoundary user;
    private String password;

    public NewUserBoundary() {}

    public void setUser(UserBoundary user) {
        this.user = user;
    }

    public NameBoundary getName() {
        return user.getName();
    }

    public void setName(NameBoundary name) {
        this.user.setName(name);
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public String getBirthdate() {
        return this.user.getBirthDate();
    }

    public void setBirthdate(String birthdate) {
        this.user.setBirthDate(birthdate);
    }

    public String getRecruitDate() {
        return this.user.getRecruitDate();
    }

    public void setRecruitDate(String recruitDate) {
        this.user.setRecruitDate(recruitDate);
    }

    public String[] getRoles() {
        return this.user.getRoles();
    }

    public void setRoles(String[] roles) {
        this.user.setRoles(roles);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUserBoundary {" +
                "user=" + user +
                ", password='" + password + '\'' +
                '}';
    }
}
