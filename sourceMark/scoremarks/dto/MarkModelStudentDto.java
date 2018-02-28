/**
 * 
 */
package com.core.main.cloudscore.scoremarks.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ye
 *
 */
public class MarkModelStudentDto{

    private String testId;
    private String studentId;
    private String testCn;
    private String subjectId;
    private List<String> subjectIdList = new ArrayList<String>();
    public String getTestId() {
        return testId;
    }
    public void setTestId(String testId) {
        this.testId = testId;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getTestCn() {
        return testCn;
    }
    public void setTestCn(String testCn) {
        this.testCn = testCn;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    public List<String> getSubjectIdList() {
        return subjectIdList;
    }
    public void setSubjectIdList(List<String> subjectIdList) {
        this.subjectIdList = subjectIdList;
    }
    
    
}
