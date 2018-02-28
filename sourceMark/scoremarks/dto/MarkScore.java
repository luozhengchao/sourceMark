package com.core.main.cloudscore.scoremarks.dto;

public class MarkScore {
    private int subjectID;
    private String subjectName;

    public MarkScore(int subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "markScore{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
