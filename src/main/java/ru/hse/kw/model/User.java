package ru.hse.kw.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=3, max=50)
    @Column(name = "login", nullable = false)
    private String login;

    @Size(min=3, max=50)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(min=3, max=50)
    @Column(name = "info", nullable = false)
    private String info;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {}

    public User(int id, String login, String email, String info, String password) {
        this.setId(id);
        this.setLogin(login);
        this.setEmail(email);
        this.setInfo(info);
        this.setPassword(password);
    }
    public User(String login,String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
