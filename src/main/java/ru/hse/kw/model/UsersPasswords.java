package ru.hse.kw.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="users_passwords")
public class UsersPasswords {

    @Id
    private int user_id;

    @Column(name = "password")
    private String password;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
