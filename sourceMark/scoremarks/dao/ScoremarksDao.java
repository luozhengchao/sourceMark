package com.core.main.cloudscore.scoremarks.dao;

import java.util.List;

import javax.annotation.Resource;

import com.core.main.cloudscore.scoremarks.dto.*;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.core.framework.dao.jdbc.BaseJdbcDao;
import com.core.main.cloudscore.scoremarks.bo.MarkScoreStudentBo;

@Repository
public class ScoremarksDao extends BaseJdbcDao {
//    public List<MarkStudent> getTestScoreDesc(int test_id){
//        StringBuffer sql = new StringBuffer("");
//        sql.append("SELECT ");
//            sql.append(" TEST_ID,STUDENT_ID,SUBJECT_ID,SCORE,TEST_CN");
//            sql.append("");
//            sql.append("");
//            sql.append("");
//            sql.append("");
//            sql.append("");
//            sql.append("");
//            sql.append("");
//
//        return queryForList(sql.toString(),MarkStudent.class);
//    }
    @Resource
    public SqlSessionTemplate sqlSession;
    public final String GET_SCORE_LIST="getOrignSingleScore";
    public final String GET_SCORERULE_LIST="getScoreRuleList";
    public final String PUT_MARKEDDATA ="putMarkedData";
    public final String GET_SUBJECTNAME= "selectSubjectName";
    public final String GET_DOUBLESCORE = "getOrignDoubleScore";
    public final String GET_RANKLIST = "getStudentRankList";
    public final String GET_SUBJECTLIST = "getSubjectListByTestId";

    public List<OrignSingleScore> getOrignSingleScore(String testId, String subjectId, String level){
        OrignSingleScore oss = new OrignSingleScore();
        oss.setTestId(testId);
        oss.setSubjectId(subjectId);
        oss.setLevel(level);
        return sqlSession.selectList(GET_SCORE_LIST,oss);
    }
    public List<OrignSingleScore> getDoubleOrTrible(String testId,String subjectId,String level,String subjectName){
        OrignSingleScore ossdouble = new OrignSingleScore();
        ossdouble.setTestId(testId);
        ossdouble.setSubjectId(subjectId);
        ossdouble.setLevel(level);
        ossdouble.setSubjectName(subjectName);
        return sqlSession.selectList(GET_DOUBLESCORE,ossdouble);
    }

    public List<MarkModel> getMarkModelRule(){
        return sqlSession.selectList(GET_SCORERULE_LIST);
    }

    public void insertAfterMark(@Param(value = "list") List<OrignSingleScore> osslist){
        sqlSession.insert(PUT_MARKEDDATA,osslist);
    }
    public String selectSubjectName(String subjectId){
        OriginSubject os = new OriginSubject();
        os.setSubjectId(subjectId);
//        os.setStageId(stageId);
        return sqlSession.selectOne(GET_SUBJECTNAME,os);
    }

    public List<MarkScoreStudentBo> getStudentRankList(@Param(value="list") List<MarkModelStudentDto> ranklist){
        System.out.println(ranklist.size()+"asdadsadsa");
        try {
            List<MarkScoreStudentBo> grl = sqlSession.selectList(GET_RANKLIST, ranklist);
            return grl;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询失败，请检查查询字段的格式。");
        }
    }

    public List<String> getSubjectListByTestId(String ridSubjectId,String testId){
        GetRankModel grm = new GetRankModel();
        grm.setTestId(testId);
        grm.setSubjectId1(ridSubjectId);
        return sqlSession.selectList(GET_SUBJECTLIST,grm);
    }

}
