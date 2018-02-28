package com.core.main.cloudscore.scoremarks.dto;

import java.io.Serializable;

public class OrignSingleScore implements Serializable {
    private String testId;
    private String subjectId;
    private String studentId;
    private String score;
    private String pm;
    private String testCN;
    private String rank;
    private String markScore;
    private String subjectName;
    private String level;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMarkScore() {
        return markScore;
    }

    public void setMarkScore(String markScore) {
        this.markScore = markScore;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getTestCN() {
        return testCN;
    }

    public void setTestCN(String testCN) {
        this.testCN = testCN;
    }
}
