package org.great.biz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.great.mapper.DailyWorkMapper;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class DailyWorkBizImpl implements IDailyWorkBiz {
	@Resource
	private DailyWorkMapper dailyWorkMapper;

	@Override
	// 请领申请(lp)
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean) {
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(day);
		drugApplyBean.setApplyDate(time);
		drugApplyBean.setCheckId(7);
		drugApplyBean.setApplyTypeId(10);
		dailyWorkMapper.takeDrugAppleFor(drugApplyBean);
		return null;

	}

	// 退库记录查询(lp)
	@Override
	public ModelAndView selectCancellingApply(CondiBean condiBean) {
		condiBean.setApplyTypeId(11);
		List<DrugApplyBean> list = dailyWorkMapper.selectCancellingApply(condiBean);
		int count = dailyWorkMapper.selectCancellingApplyCount(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/cancellingApplyList");
		mav.addObject("drugApplyList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}
	// 查找药品(lp)
	@Override
	public List<StoDrugBean> selectDrug(StoDrugBean stoDrugBean) {
		// TODO Auto-generated method stub
		return null;
	}

}
