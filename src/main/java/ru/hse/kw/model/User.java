package ru.hse.kw.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @OneToOne
    @JoinColumn(name = "id")
    private UsersPasswords usersPassword;

    public User() {}

    public User(int id, String login, String email, String info, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.info = info;
    }

    public long getId() {
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


    public UsersPasswords getUsersPassword() {
        return usersPassword;
    }

    public void setUsersPassword(UsersPasswords usersPassword) {
        this.usersPassword = usersPassword;
    }


}
