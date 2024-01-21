package il.ac.afeka.usersservice.boundaries;

import il.ac.afeka.usersservice.data.UserEntity;
import il.ac.afeka.usersservice.util.DateUtils;
import il.ac.afeka.usersservice.util.EmailChecker;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;

import java.util.Arrays;

public class UserBoundary {
    private String email;
    private NameBoundary name;
    private String birthdate;
    private String recruitdate;
    private String[] roles;

    public UserBoundary() {
    }

    public UserBoundary(UserEntity user) {
        this.email = user.getEmail();
        this.name = new NameBoundary(user.getFirstname(), user.getLastname());
        this.birthdate = user.getBirthdate();
        this.recruitdate = user.getRecruitDate();
        this.roles = user.getRoles().toArray(new String[0]);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (EmailChecker.isValidEmail(email))
            this.email = email;
        else
            throw new InvalidInputException("Invalid Email address");
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
        if (DateUtils.isValidDate(birthdate))
            this.birthdate = birthdate;
        else
            throw new InvalidInputException("Invalid birthdate");
    }

    public String getRecruitDate() {
        return recruitdate;
    }

    public void setRecruitDate(String recruitDate) {
        if (DateUtils.isValidDate(recruitDate))
            this.recruitdate = recruitDate;
        else
            throw new InvalidInputException("Invalid recruit date");
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setEmail(this.getEmail());
        entity.setFirstname(this.getName().getFirst());
        entity.setLastname(this.getName().getLast());
        entity.setBirthdate(this.getBirthDate());
        entity.setRecruitDate(this.getRecruitDate());
        entity.setRoles(this.getRoles());

        return entity;
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
