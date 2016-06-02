package ru.hse.kw.model;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "progress", nullable = false)
    private int progress;

    @Column(name = "goal", nullable = false)
    private int goal;

    @Column(name = "tag", nullable = false)
    private String tag;

    public Task() {
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

    public void setName(String name) {
        this.name = name;
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
