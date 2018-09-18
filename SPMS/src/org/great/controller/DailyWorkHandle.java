package org.great.controller;

import javax.annotation.Resource;

import org.great.bean.AdminBean;
import org.great.bean.DrugApplyBean;
import org.great.biz.IDailyWorkBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @ClassName: DailyWorkHandle 
 * @Description: TODO
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午2:16:54
 */

@Controller
@RequestMapping("/pharmacy")
public class DailyWorkHandle {
	
	@Resource
	private IDailyWorkBiz dailyWorkBizImpl;

	/**
	 * 
	 * @Title: breakCheck 
	 * @Description: 报损审核
	 * @return ModelAndView
	 * @author: JX180327_陈文炽(wc)  
	 * @date 2018年9月18日下午2:47:54
	 */
	@RequestMapping("/breakCheck.action")
	public String breakCheck(DrugApplyBean drugApplyBean) {
		
		return null;
	}
	
	@RequestMapping("/breakApply.action")
	public String breakApply(DrugApplyBean drugApplyBean) {
		
		return null;
	}
	
	@RequestMapping("/toIndex.action")
	public ModelAndView toIndex() {
		return new ModelAndView("pharmacy/index");
	}
	
	
	

	
}
