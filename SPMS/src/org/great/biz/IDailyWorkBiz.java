package org.great.biz;

import java.util.List;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.springframework.web.servlet.ModelAndView;

public interface IDailyWorkBiz {
	// 请领申请(lp)
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean);

	// 获取药品退库列表(lp)
	public ModelAndView selectCancellingApply(CondiBean condiBean);

	// 通过ajax拼音码/编号/名称查找药品信息(lp)
	public List<StoDrugBean> selectDrug(StoDrugBean stoDrugBean);

}
