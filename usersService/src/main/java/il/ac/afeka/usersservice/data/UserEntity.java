package il.ac.afeka.usersservice.data;

import il.ac.afeka.usersservice.util.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class UserEntity {
    @Id
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String birthdate;
    private String recruitdate;
    private Set<String> roles;

    public UserEntity() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRecruitDate() {
        return recruitdate;
    }

    public void setRecruitDate(String recruitdate) {
        this.recruitdate = recruitdate;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = new HashSet<>(Arrays.stream(roles).toList());
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    public int calculateAge() {
        return Long.valueOf(ChronoUnit.YEARS.between(DateUtils.parseDate(birthdate), LocalDate.now())).intValue();
    }

    @Override
    public String toString() {
        return "UserEntity {" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", recruitdate='" + recruitdate + '\'' +
                ", roles=" + roles +
                '}';
    }
}
