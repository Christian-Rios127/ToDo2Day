package edu.miracosta.cs134.todo2day.model;

import java.util.Objects;

public class Task {

    private int mID;
    private String mDescription;
    private boolean mIsDone;

    public Task(int iD, String description, boolean isDone) {
        mID = iD;
        mDescription = description;
        mIsDone = isDone;
    }
    public Task(String description,boolean isDone){
        this(-1,description, isDone);
    }
    public Task (String description){
        this(-1,description,false);
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return mID == task.mID &&
                mIsDone == task.mIsDone &&
                mDescription.equals(task.mDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mID, mDescription, mIsDone);
    }

    @Override
    public String toString() {
        return "Task{" +
                " iD=" + mID +
                ", description " + mDescription +
                ", is Done " + mIsDone +
                '}';
    }
}

