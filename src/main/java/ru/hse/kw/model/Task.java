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
    private long user_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "progress", nullable = false)
    private long progress;

    @Column(name = "goal", nullable = false)
    private long goal;

    @Column(name = "tags", nullable = false)
    private String tags;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUser_id() {
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
    public Task(long userId, String name, Date date, String description, String tags, long progress, long goal){
        this.user_id = userId;
        this.name = name;
        this.tags = tags;
        this.description = description;
        this.date = date;
        this.progress = progress;
        this.goal = goal;
    }
    public Task(int id, String name, long progress, long goal, String tags) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.goal = goal;
        this.tags = tags;
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

    public long getProgress() {
        return progress;
    }

    public long getGoal() {
        return goal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public void setGoal(long goal) {
        this.goal = goal;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}
