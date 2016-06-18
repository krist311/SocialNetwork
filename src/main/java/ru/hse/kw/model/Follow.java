package ru.hse.kw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "followers")
public class Follow {
    @Column(name = "user_id_who", nullable = false)
    private long user_id_who;

    @Column(name = "user_id_on_who", nullable = false)
    private long user_id_on_whom;

    public long getUser_id_who() {
        return user_id_who;
    }

    public void setUser_id_who(long user_id_who) {
        this.user_id_who = user_id_who;
    }

    public long getUser_id_on_whom() {
        return user_id_on_whom;
    }

    public void setUser_id_on_whom(long user_id_on_whom) {
        this.user_id_on_whom = user_id_on_whom;
    }
}