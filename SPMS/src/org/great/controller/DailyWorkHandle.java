package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.great.biz.IDailyWorkBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	//报损申请
	@RequestMapping("/breakApply.action")
	public String breakApply(DrugApplyBean drugApplyBean) {
//		DrugApplyBean da=new DrugApplyBean(1, 1001, 1, 100,  5, 12, "破损");
//		dailyWorkBizImpl.breakApply(da);
		
		return "pharmacy/breakApply";
	}


	// 到主界面(lp)
	@RequestMapping("/toMain.action")
	public ModelAndView toPharmacyMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/index");
		return mav;
	}

	// 到药品请领界面(lp)
	@RequestMapping("/toTakeDA.action")
	public ModelAndView toTakeDA() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/takeDrugAppleFor");
		return mav;
	}

	// 到选择药品界面(lp)
	@RequestMapping("/toChooseDrug.action")
	public ModelAndView toChooseDrug() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/ChooseDrug");
		return mav;
	}

	// 退库记录查询(lp)
	@RequestMapping("/toCancellingApplyList.action")
	public ModelAndView selectCancellingApply(CondiBean condiBean) {

		return dailyWorkBizImpl.selectCancellingApply(condiBean);
	}

	// 通过ajax拼音码/编号/名称查找药品信息(lp)
	@RequestMapping(value = "/selDrug.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<StoDrugBean> selectDrug(@RequestBody StoDrugBean stoDrugBean) {
		System.out.println(stoDrugBean.getDrugName());
		return null;
	}
	// 更新
}
