package org.great.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.great.bean.AdminBean;
import org.great.bean.BatchDetailBean;
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

		for (int i = 0; i < drugApplyBean.getApplyList().size(); i++) {
			drugApplyBean.setAdminId(drugApplyBean.getApplyList().get(i).getAdminId());
			drugApplyBean.setDrugId(drugApplyBean.getApplyList().get(i).getDrugId());
			drugApplyBean.setApplyNum(drugApplyBean.getApplyList().get(i).getApplyNum());
			drugApplyBean.setApplyReason(drugApplyBean.getApplyList().get(i).getApplyReason());
			dailyWorkMapper.takeDrugAppleFor(drugApplyBean);
		}

		ModelAndView mav = new ModelAndView("pharmacy/takeDrugApple");
		return mav;

	}

	// 退库记录查询(lp)
	@Override
	public ModelAndView selectCancellingApply(CondiBean condiBean) {
		condiBean.setApplyTypeId(11);
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		for (int i = 0; i < list.size(); i++) {

			System.out.println("chickid=" + list.get(i).getCheckId());
		}
		int count = dailyWorkMapper.selectCancellingApplyCount(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/cancellingApplyList");
		mav.addObject("drugApplyList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}

	// 查找药品(lp)
	@Override
	public ModelAndView selectDrug(StoDrugBean stoDrugBean) {
		List<StoDrugBean> drugList = dailyWorkMapper.selectDrug(stoDrugBean);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/chooseDrug");
		mav.addObject("drugList", drugList);
		return mav;
	}

	// 查找要退库的药
	@Override
	public ModelAndView chooseCaceDrug(StoDrugBean stoDrugBean) {
		List<StoDrugBean> drugList = dailyWorkMapper.chooseCaceDrug(stoDrugBean);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/chooseCaceDrug");
		mav.addObject("drugList", drugList);
		return mav;
	}

	@Override
	// 提交退库申请
	public ModelAndView cacellingApply(DrugApplyBean drugApplyBean) {

		for (int i = 0; i < drugApplyBean.getApplyList().size(); i++) {
			drugApplyBean.setAdminId(drugApplyBean.getApplyList().get(i).getAdminId());
			drugApplyBean.setDrugId(drugApplyBean.getApplyList().get(i).getDrugId());
			drugApplyBean.setApplyNum(drugApplyBean.getApplyList().get(i).getApplyNum());
			drugApplyBean.setApplyReason(drugApplyBean.getApplyList().get(i).getApplyReason());
			drugApplyBean.setManuBatch(drugApplyBean.getApplyList().get(i).getManuBatch());
			drugApplyBean.setPutBatch(drugApplyBean.getApplyList().get(i).getPutBatch());
			dailyWorkMapper.cacellingApply(drugApplyBean);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/cacellingApply");
		return mav;

	}

	// 审核退库申请
	@Override
	public ModelAndView chickSuccess(HttpServletRequest request,String drugApplyId,BatchDetailBean batchDetailBean) {
		HttpSession session= request.getSession();
		AdminBean admin=(AdminBean) session.getAttribute("admin");
		dailyWorkMapper.updayeCaceApply(drugApplyId);
		System.out.println(1);
		BatchDetailBean bdBean=dailyWorkMapper.selectInfo(batchDetailBean);
		
		System.out.println(2);
		bdBean.setHandleNum(batchDetailBean.getHandleNum());
		bdBean.setTotalMoney(batchDetailBean.getHandleNum()*bdBean.getPurPrice());
		dailyWorkMapper.insetPhaBatch(bdBean);
		
		System.out.println(3);
		dailyWorkMapper.insetPhaOut(admin.getAdminId());
		System.out.println(4);
		dailyWorkMapper.updatePhaDrugNum(batchDetailBean.getHandleNum(),bdBean.getDrugId());
		System.out.println(5);
		
		System.out.println(6);
		dailyWorkMapper.insetStoIn(bdBean.getPutBatch(),admin.getAdminId());
		System.out.println(7);
		dailyWorkMapper.updateStoDrugNum(batchDetailBean.getHandleNum(),bdBean.getDrugId());
		System.out.println(8);
		return selectCancellingApply(new CondiBean());
		
	};

}
