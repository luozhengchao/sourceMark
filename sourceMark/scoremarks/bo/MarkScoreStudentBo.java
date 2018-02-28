/**
 * 
 */
package com.core.main.cloudscore.scoremarks.bo;

import java.util.ArrayList;
import java.util.List;

import com.core.framework.base.BaseBO;
import com.core.main.cloudscore.scoremarks.dto.MarkStudent;

/**
 * @author ye
 *
 */
public class MarkScoreStudentBo  extends BaseBO{

    public MarkScoreStudentBo(BaseBO baseBo) {
        super.setCategory(baseBo.getCategory());
        super.setCategoryId(baseBo.getCategoryId());
        super.setCount(baseBo.getCount());
        super.setGroupIndex(baseBo.getGroupIndex());
        super.setGroupIndexId(baseBo.getGroupIndexId());
        super.setList(baseBo.getList());
    }
    public MarkScoreStudentBo() {
        
    }

    public static List<MarkScoreStudentBo> cloneSuper(List<BaseBO> superList) {
        List<MarkScoreStudentBo> list = new ArrayList<MarkScoreStudentBo>();
        for (BaseBO tmp : superList) {
            list.add(new MarkScoreStudentBo(tmp));
        }
        return list;
    }
}
