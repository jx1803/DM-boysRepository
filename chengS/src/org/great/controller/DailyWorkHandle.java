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
import org.great.bean.PhaDrugBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TakeStockBean;
import org.great.biz.IDailyWorkBiz;
import org.great.tools.Log;
import org.springframework.scheduling.annotation.Scheduled;
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
	@Log(operationType = "", operationName = "药品报损审核")
	@RequestMapping("/breakCheck.action")
	public String breakCheck(HttpServletRequest req,DrugApplyBean drugApplyBean) {
		
		return dailyWorkBizImpl.breakCheck(req,drugApplyBean);
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
	@Log(operationType = "", operationName = "药品报损申请")
	@RequestMapping("/breakApply.action")
	public String breakApply(HttpServletRequest req ,DrugApplyBean drugApplyBean) {
		
		return dailyWorkBizImpl.breakApply(req,drugApplyBean);
	}
	
	
	//查看药品申请列表
	@RequestMapping("/getDrugApply.action")
	public ModelAndView getDrugApply(CondiBean condiBean) {
		return dailyWorkBizImpl.selectDrugApply(condiBean);
	}
	
	
	//调整药品价格
	@Log(operationType = "", operationName = "调整药品价格")
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
	public ModelAndView adjustLayer(double beforeAdjust,int drugId,double purPrice) {
		return dailyWorkBizImpl.adjustLayer(beforeAdjust, drugId,purPrice);
	
	}
	
	//查询出药品信息，药品调价入口
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
	@Log(operationType = "", operationName = "发药")
	@RequestMapping("/sellDrug.action")
	public String sellDrug(HttpServletRequest req,StoDrugBean stoDrugBean) {
		return dailyWorkBizImpl.sellDrug(req,stoDrugBean);
	}
	
	//进入停用药品界面
	@RequestMapping("/toForbidDrug.action")
	public ModelAndView toForbidDrug(CondiBean condiBean) {
		return dailyWorkBizImpl.toForbidDrug(condiBean);
	}
	
	//停用药品
	@Log(operationType = "", operationName = "药品停用")
	@RequestMapping("/forbidDrug.action")
	public String forbidDrug(PhaDrugBean phaDrugBean) {
		return dailyWorkBizImpl.forbidDrug(phaDrugBean);
	}
	
	//每个月最后一天自动盘点盈亏
	@Scheduled(cron="0 0 0 1 * ?")
//	@Scheduled(cron="10 * * * * ?")
	public void checkTask() {
		System.out.println("开始盘点啦");
		dailyWorkBizImpl.checkProfit();
		dailyWorkBizImpl.checkStock();
		
	}
	
	/*
	 * 管理员录入药房药品实际库存
	 */
	@Log(operationType = "", operationName = "盘点药品")
	@RequestMapping("/checkStockData.action")
	public String checkStockData(TakeStockBean takeStockBean) {
		return dailyWorkBizImpl.checkStockData(takeStockBean);
	}
	
	/*进入到盘点界面*/
	@RequestMapping("/toCheckData.action")
	public ModelAndView toCheckData(CondiBean condiBean) {
		return dailyWorkBizImpl.toCheckData(condiBean);
	}
	
	/*查询盘点结果*/
	@RequestMapping("/showTakeStock.action")
	public ModelAndView showTakeStock(CondiBean condiBean) {
		return dailyWorkBizImpl.showTakeStock(condiBean);
	}
	
	/*查询药房药品库存信息*/
	@RequestMapping("/selectPhaDrug.action")
	public ModelAndView selectPhaDrug(CondiBean condiBean) {
		return dailyWorkBizImpl.selectPhaDrug(condiBean);
	}
	

	
	/*蓝鹏功能*/
	
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
		@Log(operationType = "", operationName = "提交退库申请")
		@RequestMapping("/cacellingApply.action")
		public ModelAndView cacellingApply(HttpServletRequest request, DrugApplyBean drugApplyBean) {
			return dailyWorkBizImpl.cacellingApply(request, drugApplyBean);

		}

		// 同意退库申请
		@Log(operationType = "", operationName = "同意退库申请")
		@RequestMapping("/chickSuccess.action")
		public ModelAndView chickSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean) {
			System.out.println(drugApplyId);
			System.out.println();
			return dailyWorkBizImpl.chickSuccess(request, drugApplyId, batchDetailBean);

		}

		// 不同意退库申请
		@Log(operationType = "", operationName = "不同意退库申请")
		@RequestMapping("/chickError.action")
		public ModelAndView chickError(HttpServletRequest requset, String drugApplyId) {
			return dailyWorkBizImpl.chickError(requset, drugApplyId);

		}

		// 到审核请领界面(lp)
		@RequestMapping("/totakeApplyList.action")
		public ModelAndView totakeApplyList(CondiBean condiBean) {

			return dailyWorkBizImpl.totakeApplyList(condiBean);
		}

		// 不同意请领申请
		@Log(operationType = "", operationName = "不同意请领申请")
		@RequestMapping("/takeError.action")
		public ModelAndView takeError(HttpServletRequest requset, String drugApplyId) {

			return dailyWorkBizImpl.takeError(requset, drugApplyId);

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
		@Log(operationType = "", operationName = "同意请领申请")
		@RequestMapping("/takeSuccess.action")
		public ModelAndView takeSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean) {
			System.out.println("请领数量为" + batchDetailBean.getHandleNum());
			return dailyWorkBizImpl.takeSuccess(request, drugApplyId, batchDetailBean);
		}

		// 到消息提醒界面
		@RequestMapping("/toWarn.action")
		public ModelAndView toWarn(HttpServletRequest request) {
			return dailyWorkBizImpl.toWarn(request);
		}

		// 设置定时时间
		@Scheduled(cron = "1 0 0 * * ?")
		public void expirationWarn() {
			System.out.println("咨询查找过期/滞销业务");
			dailyWorkBizImpl.expirationWarn();

		}

		// 到低限预警界面
		@RequestMapping("toMinimumWarn.action")
		public ModelAndView toMinimumWarn(HttpServletRequest request, CondiBean condiBean) {
			return dailyWorkBizImpl.toMinimumWarn(request, condiBean);

		}

		// 到滞销预警界面
		@RequestMapping("toUnsalableWarn.action")
		public ModelAndView toUnsalableWarn(HttpServletRequest request, CondiBean condiBean) {
			return dailyWorkBizImpl.toUnsalableWarn(request, condiBean);

		}

		// 到过期预警界面
		@RequestMapping("toOverdueWarn.action")
		public ModelAndView toOverdueWarn(HttpServletRequest request, CondiBean condiBean) {
			return dailyWorkBizImpl.toOverdueWarn(request, condiBean);

		}

		// 到过期预警界面
		@RequestMapping("affirmWarn.action")
		public ModelAndView affirmWarn(HttpServletRequest request, int warnId) {
			return dailyWorkBizImpl.affirmWarn(request, warnId);
		}
	}
