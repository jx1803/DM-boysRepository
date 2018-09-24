package org.great.biz;

import javax.servlet.http.HttpServletRequest;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.springframework.web.servlet.ModelAndView;

public interface IDailyWorkBiz {
	// 请领申请(lp)
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean);

	// 获取药品退库列表(lp)
	public ModelAndView selectCancellingApply(CondiBean condiBean);

	// 通过拼音码/编号/名称查找药品信息(lp)
	public ModelAndView selectDrug(StoDrugBean stoDrugBean);

	// 通过拼音码/编号/名称//生产日期查找退库药品信息(lp)
	public ModelAndView chooseCaceDrug(StoDrugBean stoDrugBean);

	// 提交退库申请
	public ModelAndView cacellingApply(DrugApplyBean drugApplyBean);

	// 审核退库申请
	public ModelAndView chickSuccess(HttpServletRequest request,String drugApplyId,BatchDetailBean batchDetailBean);
}
