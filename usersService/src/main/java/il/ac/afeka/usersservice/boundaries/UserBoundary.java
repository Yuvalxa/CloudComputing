package il.ac.afeka.usersservice.boundaries;

import il.ac.afeka.usersservice.data.UserEntity;

import java.util.Arrays;

public class UserBoundary {
    private String email;
    private NameBoundary name;
    private String birthdate;
    private String recruitdate;
    private String[] roles;

    public UserBoundary() {}

    public UserBoundary(UserEntity user) {
        this.email = user.getEmail();
        this.name = new NameBoundary(user.getFirstname(), user.getLastname());
        this.birthdate = user.getBirthdate();
        this.recruitdate = user.getRecruitDate();
        this.roles = user.getRoles();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NameBoundary getName() {
        return name;
    }

    public void setName(NameBoundary name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRecruitDate() {
        return recruitdate;
    }

    public void setRecruitDate(String recruitDate) {
        this.recruitdate = recruitDate;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserBoundary {" +
                "email='" + email + '\'' +
                ", name=" + name +
                ", birthdate='" + birthdate + '\'' +
                ", recruitdate='" + recruitdate + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
