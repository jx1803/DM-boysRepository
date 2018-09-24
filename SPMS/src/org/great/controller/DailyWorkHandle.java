package org.great.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.great.bean.AdminBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.great.biz.IDailyWorkBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pharmacy")
public class DailyWorkHandle {
	@Resource
	private IDailyWorkBiz dailyWorkBizImpl;

	// 到主界面(lp)
	@RequestMapping("/toMain.action")
	public ModelAndView toPharmacyMain(HttpServletRequest requsest) {
		HttpSession session = requsest.getSession();
		session.setAttribute("admin", new AdminBean(1001, "admin"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/index");
		return mav;
	}

	// 到药品请领界面(lp)
	@RequestMapping("/toTakeDA.action")
	public ModelAndView toTakeDA() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/takeDrugApple");
		return mav;
	}

	// 到选择药品界面(lp)
	@RequestMapping("/toChooseDrug.action")
	public ModelAndView toChooseDrug() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/chooseDrug");
		return mav;
	}

	// 退库记录查询(lp)
	@RequestMapping("/toCancellingApplyList.action")
	public ModelAndView selectCancellingApply(CondiBean condiBean) {

		return dailyWorkBizImpl.selectCancellingApply(condiBean);
	}

	// 通过拼音码/编号/名称查找药品信息(lp)
	@RequestMapping("/selDrug.action")
	public ModelAndView selectDrug(StoDrugBean stoDrugBean) {
		return dailyWorkBizImpl.selectDrug(stoDrugBean);
	}

	// 提交药品请领申请
	@RequestMapping("/takeDrugAppleFor.action")
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean) {

		return dailyWorkBizImpl.takeDrugAppleFor(drugApplyBean);
	}

	// 到退库申请界面(lp)
	@RequestMapping("/toCaceApply.action")
	public ModelAndView toCaceApply() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/cacellingApply");
		return mav;

	}

	// 到选择药品退库界面(lp)
	@RequestMapping("/toChooseCaceDrug.action")
	public ModelAndView toChooseCaceDrug() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/chooseCaceDrug");
		return mav;
	}

	// 退库选择药品
	@RequestMapping("/chooseCaceDrug.action")
	public ModelAndView chooseCaceDrug(StoDrugBean stoDrugBean) {

		return dailyWorkBizImpl.chooseCaceDrug(stoDrugBean);
	}

	// 提交退库申请
	@RequestMapping("/cacellingApply.action")
	public ModelAndView cacellingApply(DrugApplyBean drugApplyBean) {
		return dailyWorkBizImpl.cacellingApply(drugApplyBean);

	}

	// 同意退库申请
	@RequestMapping("/chickSuccess.action")
	public ModelAndView chickSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean) {
		System.out.println(drugApplyId);
		return dailyWorkBizImpl.chickSuccess(request,drugApplyId, batchDetailBean);

	}

}
