package org.great.biz;

import java.util.*;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @ClassName: IDailyWorkBiz 
 * @Description: TODO
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午3:04:28
 */
public interface IDailyWorkBiz {
	public String breakCheck(DrugApplyBean drugApplyBean);//报损审核
	public String breakApply(DrugApplyBean drugApplyBean);//报损申请
	public ModelAndView selectDrugApply(CondiBean condiBean);//获取药品申请列表

}
