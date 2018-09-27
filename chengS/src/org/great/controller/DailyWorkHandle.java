package org.great.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.great.bean.AdjustPriceBean;
import org.great.bean.AdminBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.great.biz.IDailyWorkBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	//药品审核
	@RequestMapping("/breakCheck.action")
	public String breakCheck(DrugApplyBean drugApplyBean) {
		
		return dailyWorkBizImpl.breakCheck(drugApplyBean);
	}
	
	//跳转报损审核界面
	@RequestMapping("toBreakCheck.action")
	public ModelAndView toBreakCheck(CondiBean condiBean) {
		return dailyWorkBizImpl.selectBreakCheck(condiBean);
	}
	
	//报损申请界面
	@RequestMapping("/toBreakApply.action")
	public String toBreakApply() {
		return "pharmacy/breakApply";
	}
	
	//获取入库批次详情列表
	@RequestMapping("/selectPutDrug.action")
	public ModelAndView selectPutDrug(CondiBean condiBean){
		
		return dailyWorkBizImpl.selectPutDrug(condiBean);
		
	}

	/*报损申请*/
	@RequestMapping("/breakApply.action")
	public String breakApply(HttpServletRequest req ,DrugApplyBean drugApplyBean) {
		
		return dailyWorkBizImpl.breakApply(drugApplyBean);
	}
	
	
	//查看药品申请列表
	@RequestMapping("/getDrugApply.action")
	public ModelAndView getDrugApply(CondiBean condiBean) {
		return dailyWorkBizImpl.selectDrugApply(condiBean);
	}
	
	
	//调整药品价格
	@RequestMapping("/adjustPrice.action")
	public String adjustPrice(AdjustPriceBean adjustPriceBean) {
		return dailyWorkBizImpl.adjustPrice(adjustPriceBean);
	}
	//获取价格调节记录列表
	@RequestMapping("/selectAdjustPrice.action")
	public ModelAndView selectAdjustPrice(CondiBean condiBean) {
		return dailyWorkBizImpl.selectAdjustPrice(condiBean);
	}
	
	//价格调整弹窗
	@RequestMapping("/adjustLayer.action")
	public ModelAndView adjustLayer(double beforeAdjust,int drugId) {
		return dailyWorkBizImpl.adjustLayer(beforeAdjust, drugId);
	
	}
	
	//查询出药品信息，药品调价入口（未完成）
	@RequestMapping("/toAdjustPrice.action")
	public ModelAndView toAdjustPrice(CondiBean condiBean) {
		
		return dailyWorkBizImpl.toAdjustPrice(condiBean);
	}
	
	//调转药品发药界面
	@RequestMapping("/toSellDrug.action")
	public String toSellDrug() {
		return "pharmacy/sellDrug";
	}
	
	//发药弹窗
	@RequestMapping("/sellDrugLayer.action")
	public ModelAndView sellDrugLayer(CondiBean condiBean) {
		
		return dailyWorkBizImpl.sellDrugLayer(condiBean);
	}
	
	//开始发药
	@RequestMapping("/sellDrug.action")
	public String sellDrug(StoDrugBean stoDrugBean) {
		return dailyWorkBizImpl.sellDrug(stoDrugBean);
	}
	
	@RequestMapping("/toIndex.action")
	public ModelAndView toIndex() {
		return new ModelAndView("pharmacy/index");
	}
	
	
	
	// 到主界面(lp)
		@RequestMapping("/toMain.action")
		public ModelAndView toPharmacyMain(HttpServletRequest requsest) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("pharmacy/index");
			return mav;
		}

		// 到药品请领界面(lp)
		@RequestMapping("/toTakeDA.action")
		public ModelAndView toTakeDA() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("pharmacy/takeDrugApply");
			return mav;
		}

		// 退库审核列表(lp)
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
			System.out.println();
			return dailyWorkBizImpl.chickSuccess(request, drugApplyId, batchDetailBean);

		}

		// 不同意退库申请
		@RequestMapping("/chickError.action")
		public ModelAndView chickError(HttpServletRequest requset,String drugApplyId) {
			return dailyWorkBizImpl.chickError(requset,drugApplyId);

		}

		// 到审核请领界面(lp)
		@RequestMapping("/totakeApplyList.action")
		public ModelAndView totakeApplyList(CondiBean condiBean) {
			return dailyWorkBizImpl.totakeApplyList(condiBean);
		}

		// 不同意请领申请
		@RequestMapping("/takeError.action")
		public ModelAndView takeError(HttpServletRequest requset,String drugApplyId) {
			
			return dailyWorkBizImpl.takeError(requset,drugApplyId);

		}

		// 查看所有退库记录
		@RequestMapping("/toAllCancApply.action")
		public ModelAndView toAllCancApply(CondiBean condiBean) {

			return dailyWorkBizImpl.toAllCancApply(condiBean);
		}

		// 到所有请领记录界面(lp)
		@RequestMapping("/toAllTakeApply.action")
		public ModelAndView toAllTakeApply(CondiBean condiBean) {
			return dailyWorkBizImpl.toAllTakeApply(condiBean);
		}
		// 同意请领申请
		@RequestMapping("/takeSuccess.action")
		public ModelAndView takeSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean) {
			System.out.println("请领数量为"+batchDetailBean.getHandleNum());
			return dailyWorkBizImpl.takeSuccess(request, drugApplyId,batchDetailBean);
		}
		
	

	
}
