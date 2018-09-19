package org.great.mapper;

import java.util.List;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: DailyWorkMapper 
 * @Description: 日常工作持久层映射器
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午3:13:44
 */
@Repository
public interface DailyWorkMapper {
/**
 * @Description: 日常工作
 * @author: JX180327_陈文炽(wc)  
 * @date 2018年9月18日下午8:56:06
 */
	public int breakCheck(DrugApplyBean drugApplyBean);//报损审核
	public int breakApply(DrugApplyBean drugApplyBean);//报损申请
	public List<DrugApplyBean> selectDrugApply(CondiBean condiBean);//查询申请记录
}
