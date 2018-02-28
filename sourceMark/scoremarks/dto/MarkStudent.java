package com.core.main.cloudscore.scoremarks.dto;

import com.core.framework.base.BaseBO;

public class MarkStudent extends BaseBO {
    private int TEST_ID;
    private int STUDENT_ID;
    private int SUBJECT_ID;
    private int SCORE;
    private int PM;
    private String TEST_CN;

    @Override
    public String toString() {
        return "markStudent{" +
                "TEST_ID=" + TEST_ID +
                ", STUDENT_ID=" + STUDENT_ID +
                ", SUBJECT_ID=" + SUBJECT_ID +
                ", SCORE=" + SCORE +
                ", PM=" + PM +
                ", TEST_CN='" + TEST_CN + '\'' +
                '}';
    }

    public int getTEST_ID() {
        return TEST_ID;
    }

    public void setTEST_ID(int TEST_ID) {
        this.TEST_ID = TEST_ID;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public int getSUBJECT_ID() {
        return SUBJECT_ID;
    }

    public void setSUBJECT_ID(int SUBJECT_ID) {
        this.SUBJECT_ID = SUBJECT_ID;
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    public int getPM() {
        return PM;
    }

    public void setPM(int PM) {
        this.PM = PM;
    }

    public String getTEST_CN() {
        return TEST_CN;
    }

    public void setTEST_CN(String TEST_CN) {
        this.TEST_CN = TEST_CN;
    }

    public MarkStudent(int TEST_ID, int STUDENT_ID, int SUBJECT_ID, int SCORE, int PM, String TEST_CN) {
        this.TEST_ID = TEST_ID;
        this.STUDENT_ID = STUDENT_ID;
        this.SUBJECT_ID = SUBJECT_ID;
        this.SCORE = SCORE;
        this.PM = PM;
        this.TEST_CN = TEST_CN;
    }
}
