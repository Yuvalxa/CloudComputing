package il.ac.afeka.usersservice.boundaries.user;

import il.ac.afeka.usersservice.data.UserEntity;
import il.ac.afeka.usersservice.util.PasswordManager;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;

public class NewUserBoundary extends UserBoundary {
    private String password;

    public NewUserBoundary() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (PasswordManager.validate(password))
            this.password = PasswordManager.encrypt(password);
        else
            throw new InvalidInputException("Illegal password");
    }

    @Override
    public UserEntity toEntity() {
        UserEntity entity = super.toEntity();
        entity.setPassword(this.getPassword());

        return entity;
    }

    @Override
    public String toString() {
        String userBoundaryString = super.toString();
        return "New" +
                userBoundaryString.substring(0, userBoundaryString.length() - 1) + // removes '}' in parent's toString()
                ", password=" + this.password +
                '}';
    }
}
