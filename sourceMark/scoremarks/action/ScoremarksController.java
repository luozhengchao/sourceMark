package com.core.main.cloudscore.scoremarks.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.framework.util.Log4jUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.framework.util.Base64Util;
import com.core.main.cloudscore.scoremarks.bo.MarkScoreStudentBo;
import com.core.main.cloudscore.scoremarks.service.ScoremarksService;

/**
 * 赋分模块接口
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/scoremarksController")

public class ScoremarksController {
    @Autowired
    ScoremarksService scoremarksService;


    /**
     *
     * Title: dealSelectTribleFromSix
     * Description: 6选三赋分，目前没有批量更新功能，若多次调用会多次插入
     * GET
     * @param testId
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午15:09:42
     */
    @RequestMapping(value = "/doSelectTribleFromSix")
    public void dealSelectTribleFromSix (HttpServletRequest request, HttpServletResponse rp)throws IOException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success","false");
        rp.setContentType("text/html;charset=utf-8");
        String result =null;
        try{
            String testId  = request.getParameter("testId");
            scoremarksService.saveScoureMarksEtl(testId);
        }catch (Exception e){
            result = jsonObject.toString();
            rp.getWriter().print(result);
            throw new RuntimeException("数据清洗异常");
        }
        jsonObject.put("success","true");
        result = jsonObject.toString();
        rp.getWriter().print(result);
    }

    /**
     *
     * Title: dealSelectTribleFromSix
     * Description: 获取排名信息
     * GET
     * @param jsonstr
     * @return
     * @author  liuxinmale
     * @date 2018-2-28 上午15:09:42
     */
    @RequestMapping(value = "/getPMlist")
    public void getPMList(HttpServletRequest request, HttpServletResponse rp) throws  Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success","false");
        String result =null;
        try {
            rp.setContentType("text/html;charset=utf-8");
            String jsonstr = request.getParameter("jsonstr");
            jsonstr = Base64Util.decode(jsonstr, "UTF-8");
            System.out.println(jsonstr);
            List<MarkScoreStudentBo> list = scoremarksService.getMarksPM(jsonstr);
            if(list != null){
                jsonObject.put("PMList",JSONArray.fromObject(list));
                jsonObject.put("success","true");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        result = Base64Util.encode(jsonObject.toString(), "UTF-8");
        rp.getWriter().print(result);


    }

}

