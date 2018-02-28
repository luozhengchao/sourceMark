package com.core.main.cloudscore.scoremarks.dto;

import java.io.Serializable;
import java.util.List;

import com.core.framework.base.BaseBO;

public class GetRankModel extends BaseBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4554111335177155292L;
    private String studentId;
    private List<String> subjectIdTrible2;
    private String subTrible2;
    private List<String> subjectIdTrible3;
    private String subTrible3;
    private String testId;
    private String subjectId1;
    private String pm1;
    private String pm2;
    private String pm3;
    public GetRankModel(){
        
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<String> getSubjectIdTrible2() {
        return subjectIdTrible2;
    }

    public void setSubjectIdTrible2(List<String> subjectIdTrible2) {
        this.subjectIdTrible2 = subjectIdTrible2;
    }

    public String getSubTrible2() {
        return subTrible2;
    }

    public void setSubTrible2(String subTrible2) {
        this.subTrible2 = subTrible2;
    }

    public List<String> getSubjectIdTrible3() {
        return subjectIdTrible3;
    }

    public void setSubjectIdTrible3(List<String> subjectIdTrible3) {
        this.subjectIdTrible3 = subjectIdTrible3;
    }

    public String getSubTrible3() {
        return subTrible3;
    }

    public void setSubTrible3(String subTrible3) {
        this.subTrible3 = subTrible3;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getSubjectId1() {
        return subjectId1;
    }

    public void setSubjectId1(String subjectId1) {
        this.subjectId1 = subjectId1;
    }

    public String getPm1() {
        return pm1;
    }

    public void setPm1(String pm1) {
        this.pm1 = pm1;
    }

    public String getPm2() {
        return pm2;
    }

    public void setPm2(String pm2) {
        this.pm2 = pm2;
    }

    public String getPm3() {
        return pm3;
    }

    public void setPm3(String pm3) {
        this.pm3 = pm3;
    }
}
