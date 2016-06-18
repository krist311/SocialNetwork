package ru.hse.kw.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "progress", nullable = false)
    private int progress;

    @Column(name = "goal", nullable = false)
    private int goal;

    @Column(name = "tag", nullable = false)
    private String tag;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Column(name ="date", nullable = false)
    private Date date;

    @Override
    public String toString(){
        return this.name + this.user_id;
    }
    public Task() {
    }
    public Task(String name, Date date, String description, String tags){
        this.name = name;
        this.tag = tags;
        this.description = description;
        this.date = date;
    }
    public Task(int id, String name, int progress, int goal, String tag) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.goal = goal;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public int getGoal() {
        return goal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
