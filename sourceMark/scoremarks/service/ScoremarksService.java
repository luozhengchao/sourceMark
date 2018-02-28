package com.core.main.cloudscore.scoremarks.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.framework.base.BaseBO;
import com.core.main.cloudscore.scoremarks.bo.MarkScoreStudentBo;
import com.core.main.cloudscore.scoremarks.dao.ScoremarksDao;
import com.core.main.cloudscore.scoremarks.dto.MarkModel;
import com.core.main.cloudscore.scoremarks.dto.MarkModelStudentDto;
import com.core.main.cloudscore.scoremarks.dto.OrignSingleScore;
import com.core.service.util.CommonService;
@Service
public class ScoremarksService   {

    @Autowired
    ScoremarksDao scoremarksDao;
    
    @Autowired
    CommonService commonService;


    private static final int PAGESIZE = 200;
    private static final String RidSubjectId = "16,17,18,-1";



    /**
     *
     * Title: getSetScoreMarksEtl
     * Description: 完成清洗数据
     * @param testId,subjectId,level
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午11:11:15
     */
    private List<OrignSingleScore> getSetScoreMarksEtl(String testId, String subjectId, String level) {
        List<OrignSingleScore> oss = scoremarksDao.getOrignSingleScore(testId, subjectId, level);/*获取某次考试，某科的所有学生成绩排名（不连续可重复排序）*/
        List<MarkModel> mm = scoremarksDao.getMarkModelRule();/*获取赋分规则*/
        if (oss.size() < 1) {
            System.out.println(subjectId + "学科，" + testId + "考试无成绩。");
            return null;
        }
        double lastNumber = Double.parseDouble(oss.get(oss.size() - 1).getRank());/*获取最大排名*/
        double pmrankindex = 1;
        for (OrignSingleScore os1 : oss) {
            os1.setMarkScore(String.valueOf((int) buildMarkScore(lastNumber, Double.parseDouble(os1.getRank()), mm, pmrankindex)));/*执行赋分操作*/
        }
        oss = buildPMRank(oss);/*执行赋分后排名操作*/
        return oss;

    }

    /**
     *
     * Title: buildMarkScore
     * Description: 赋分逻辑实现
     * @param lastNumber,studentRank,mm,pmindex
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午11:28:22
     */
    private double buildMarkScore(double lastNumber, double studentRank, List<MarkModel> mm, double pmindex) {
        for (int i = 0; i < mm.size(); i++) {
            if (i == 0 && (studentRank / lastNumber) <= ((double) mm.get(i).getPercent() / 100)) {
                return mm.get(i).getMarkScore();
            } else if (i > 0 && (studentRank / lastNumber) <= ((double) mm.get(i).getPercent() / 100) && (studentRank / lastNumber) > ((double) mm.get(i - 1).getPercent() / 100)) {
                return mm.get(i).getMarkScore();
            }
        }
        return 0;
    }

    /**
     *
     * Title: buildMarkScore
     * Description: 赋分后排名逻辑实现（不连续可重复）
     * @param ossList
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午11:29:03
     */
    private List<OrignSingleScore> buildPMRank(List<OrignSingleScore> ossList) {
        List<OrignSingleScore> ossListTemp = ossList;
        int PMindex = 1;
        for (int ossindex = 0; ossindex < ossListTemp.size(); ossindex++) {
            if (ossindex == 0) {
                ossListTemp.get(ossindex).setPm(String.valueOf(PMindex));
                continue;
            } else if (ossListTemp.get(ossindex).getMarkScore().equals(ossListTemp.get(ossindex - 1).getMarkScore())) {
                ossListTemp.get(ossindex).setPm(String.valueOf(PMindex));
                continue;
            } else if (!ossListTemp.get(ossindex).getMarkScore().equals(ossListTemp.get(ossindex - 1).getMarkScore())) {
                ossListTemp.get(ossindex).setPm(String.valueOf(ossindex));
                PMindex = ossindex;
                continue;
            }
        }
        return ossListTemp;
    }

    /**
     *
     * Title: saveScoureMarksEtl
     * Description: 清洗数据
     * @param testId
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午11:33:56
     */
    @Transactional
    public void saveScoureMarksEtl(String testId) {
//        19 20 21 22 23 33
//        List<String> subjectId = new ArrayList<>();
//        subjectId.add("19");
//        subjectId.add("20");
//        subjectId.add("21");
//        subjectId.add("22");
//        subjectId.add("23");
//        subjectId.add("33");
//        String testId = "5";
//        String stageId = "6";//这是之前赋分

        List<String> subjectId = scoremarksDao.getSubjectListByTestId(RidSubjectId,testId);
        System.out.println(1111222333);
        System.out.println(subjectId.toString());
        List<OrignSingleScore> osstem3 = null;
        for (String index : subjectId) {
            osstem3 = getSetScoreMarksEtl(testId, index, "1");
            saveScoureMarksEtlByPage(osstem3);
        }
        for(int i = 0;i<subjectId.size();i++){
            for(int j = i+1;j<subjectId.size();j++){
                String Idindex = "'"+subjectId.get(i)+"'"+","+"'"+subjectId.get(j)+"'";
                String Nameindex = scoremarksDao.selectSubjectName(subjectId.get(i))+","+scoremarksDao.selectSubjectName(subjectId.get(j));
                osstem3 = scoremarksDao.getDoubleOrTrible(testId, Idindex, "2", Nameindex);
                for(OrignSingleScore osstemsub :osstem3){
                    osstemsub.setSubjectId(osstemsub.getSubjectId().replace("'",""));
                }
                saveScoureMarksEtlByPage(osstem3);
            }
        }
        for(int l = 0;l<subjectId.size();l++){
            for(int m = l+1;m<subjectId.size();m++){
                for(int n = m+1;n<subjectId.size();n++){
                    String Idindex = "'"+subjectId.get(l)+"'"+","+"'"+subjectId.get(m)+"'"+","+"'"+subjectId.get(n)+"'";
                    String Nameindex = scoremarksDao.selectSubjectName(subjectId.get(l))+","+scoremarksDao.selectSubjectName(subjectId.get(m))+","+scoremarksDao.selectSubjectName(subjectId.get(n));
                    osstem3 = scoremarksDao.getDoubleOrTrible(testId, Idindex, "3", Nameindex);
                    for(OrignSingleScore osstemsub :osstem3){
                        osstemsub.setSubjectId(osstemsub.getSubjectId().replace("'",""));
                    }
                    saveScoureMarksEtlByPage(osstem3);

                }
            }
        }
    }

    //有stageId的赋分
//    public void saveScoureMarksEtl(List<String> subjectId,String testId,String stageId) {
////        19 20 21 22 23 33
////        List<String> subjectId = new ArrayList<>();
////        subjectId.add("19");
////        subjectId.add("20");
////        subjectId.add("21");
////        subjectId.add("22");
////        subjectId.add("23");
////        subjectId.add("33");
////        String testId = "5";
////        String stageId = "6";//这是之前赋分
//        List<OrignSingleScore> osstem3 = null;
//        for (String index : subjectId) {
//            osstem3 = getSetScoreMarksEtl(testId, index, "1");
//            saveScoureMarksEtlByPage(osstem3);
//        }
//        for(int i = 0;i<subjectId.size();i++){
//            for(int j = i+1;j<subjectId.size();j++){
//                String Idindex = "'"+subjectId.get(i)+"'"+","+"'"+subjectId.get(j)+"'";
//                String Nameindex = scoremarksDao.selectSubjectName(subjectId.get(i),stageId)+","+scoremarksDao.selectSubjectName(subjectId.get(j),stageId);
//                osstem3 = scoremarksDao.getDoubleOrTrible(testId, Idindex, "2", Nameindex);
//                for(OrignSingleScore osstemsub :osstem3){
//                    osstemsub.setSubjectId(osstemsub.getSubjectId().replace("'",""));
//                }
//                saveScoureMarksEtlByPage(osstem3);
//            }
//        }
//        for(int l = 0;l<subjectId.size();l++){
//            for(int m = l+1;m<subjectId.size();m++){
//                for(int n = m+1;n<subjectId.size();n++){
//                    String Idindex = "'"+subjectId.get(l)+"'"+","+"'"+subjectId.get(m)+"'"+","+"'"+subjectId.get(n)+"'";
//                    String Nameindex = scoremarksDao.selectSubjectName(subjectId.get(l),stageId)+","+scoremarksDao.selectSubjectName(subjectId.get(m),stageId)+","+scoremarksDao.selectSubjectName(subjectId.get(n),stageId);
//                    osstem3 = scoremarksDao.getDoubleOrTrible(testId, Idindex, "3", Nameindex);
//                    for(OrignSingleScore osstemsub :osstem3){
//                        osstemsub.setSubjectId(osstemsub.getSubjectId().replace("'",""));
//                    }
//                    saveScoureMarksEtlByPage(osstem3);
//
//                }
//            }
//        }
//    }

    /**
     *
     * Title: saveScoureMarksEtl
     * Description: 将洗完的数据存到数据库，批量存，快
     * @param list
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午11:33:56
     */
    private void saveScoureMarksEtlByPage(List<OrignSingleScore> list) {
        //分页处理
        int tempindex2;
        if (list != null) {
            /*这里数据有丢失*/
            /*应该没有，测一下*/
            for (int tempindex = 0; tempindex < list.size(); tempindex += PAGESIZE) {
                tempindex2 = tempindex + PAGESIZE;
                if (tempindex2 >= list.size()) {
                    tempindex2 = list.size() ;
                }
                List<OrignSingleScore> listtemp = list.subList(tempindex, tempindex2);
                scoremarksDao.insertAfterMark(listtemp);
            }
        }
    }
    /**
     * 
    * Title: getMarksPM
    * Description: 获取学生赋分排名
    * @param jsonstr
    * @return
    * @author  yexiangdong
    * @date 2018-2-11 上午11:17:41
     */
    @Transactional
    public List<MarkScoreStudentBo> getMarksPM(String jsonstr) {
        List<MarkModelStudentDto> ranklist = parseGetMarksPmPara(jsonstr);
        if(ranklist == null){
            return null;
        }
        return getMarksPM(ranklist);
    }
    /**
     * 
    * Title: parseGetMarksPmPara
    * Description: 查询学生赋分排名参数解析
    * @param jsonstr
    * @return
    * @author  yexiangdong
    * @date 2018-2-11 上午11:16:46
     */
    public List<MarkModelStudentDto> parseGetMarksPmPara(String jsonstr){
        //      String jsonstr = "{\"studentList\":[{\"studentId\":\"\",\"testId\":\"1\",\"subjectId1\":\"16\",\"subjectIdTrible2\":\"'16','17','18'\",\"subjectIdTrible3\":\"'16','17','22'\"},{\"studentId\":\"86216670\",\"testId\":\"1\",\"subjectId1\":\"16\",\"subjectIdTrible2\":\"'16','33','18'\",\"subjectIdTrible3\":\"'16','17','22'\"}]}";
        List<MarkModelStudentDto> ranklist = new ArrayList<>();
        try {
            JSONArray jsonArray = JSONObject.fromObject(jsonstr).getJSONArray("studentList");
            int jsonindex=0;
            for(;jsonindex<jsonArray.size();jsonindex++){
                JSONObject jsonObject = jsonArray.getJSONObject(jsonindex);
                if(jsonObject!=null){
                    MarkModelStudentDto mm = new MarkModelStudentDto();
                    mm.setStudentId(String.valueOf(jsonObject.get("studentId")!=null?jsonObject.get("studentId"):"" ));
                    mm.setTestId(String.valueOf(jsonObject.get("testId")!=null?jsonObject.get("testId"):"" ));
                    /*年后让姜旭改成按list传参*/
                    if(jsonObject.has("subjectId1")){
                        mm.getSubjectIdList().add(jsonObject.get("subjectId1").toString());
                    }
                    if(jsonObject.has("subjectIdTrible2")){
                        mm.getSubjectIdList().add(jsonObject.get("subjectIdTrible2").toString());
                    }
                    if(jsonObject.has("subjectIdTrible3")){
                        mm.getSubjectIdList().add(jsonObject.get("subjectIdTrible3").toString());
                    }
                    ranklist.add(mm);
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
            ranklist = null;
        }
        return ranklist;
    }
    /**
     * 
    * Title: getPMByPage
    * Description: 分页查询学生赋分排名
    * @param list
    * @return
    * @throws Exception
    * @author  yexiangdong
    * @date 2018-2-11 上午9:54:48
     */
    private List<MarkScoreStudentBo> getMarksPM(List<MarkModelStudentDto> list) {
        //分页处理
        List<MarkScoreStudentBo> grk = new ArrayList<MarkScoreStudentBo>();
        if (list != null) {
            List<MarkModelStudentDto> listtemp;
            while(list.size()>0){
                if(list.size()>PAGESIZE){
                    listtemp = list.subList(0, PAGESIZE);
                }else{
                    listtemp = list.subList(0, list.size());
                }
                List<BaseBO> list1= commonService.generalGroupList(scoremarksDao.getStudentRankList(listtemp));
                list.removeAll(listtemp);
                grk.addAll(MarkScoreStudentBo.cloneSuper(list1));
            }
        }
        return grk;
    }

}
