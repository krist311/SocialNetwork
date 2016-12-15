package ru.hse.kw.model;

import javax.persistence.*;

@Entity
@Table(name="users_passwords")
public class UsersPasswords {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "password")
    private String password;

    public UsersPasswords(){}

    public UsersPasswords(int userId, String password){
        this.setUserId(userId);
        this.setPassword(password);
    }

    public UsersPasswords(String password){
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
