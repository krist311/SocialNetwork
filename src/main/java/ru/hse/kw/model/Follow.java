package ru.hse.kw.model;

import javax.persistence.*;


@Entity
@Table(name = "followers")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id_who", nullable = false)
    private int user_id_who;

    @Column(name = "user_id_on_whom", nullable = false)
    private int user_id_on_whom;

    public int getUser_id_who() {
        return user_id_who;
    }

    public void setUser_id_who(int user_id_who) {
        this.user_id_who = user_id_who;
    }

    public int getUser_id_on_whom() {
        return user_id_on_whom;
    }

    public void setUser_id_on_whom(int user_id_on_whom) {
        this.user_id_on_whom = user_id_on_whom;
    }
}