package com.stamford.scheduling;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public abstract class Job implements Comparable<Job> {

    private int id;
    private int weight;
    private int length;

    public Job(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    public Job(int id, int weight, int length) {
        this.id = id;
        this.weight = weight;
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Job)) {
            return false;
        }
        Job other = (Job) obj;
        return this.id == other.id;
    }

    abstract public Integer calcPriority();
}
