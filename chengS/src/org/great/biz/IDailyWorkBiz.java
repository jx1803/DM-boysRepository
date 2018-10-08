package org.great.biz;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.great.bean.AdjustPriceBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TakeStockBean;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @ClassName: IDailyWorkBiz 
 * @Description: TODO
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午3:04:28
 */
public interface IDailyWorkBiz {
	public String breakCheck(HttpServletRequest req,DrugApplyBean drugApplyBean);//报损审核
	public String breakApply(HttpServletRequest req,DrugApplyBean drugApplyBean);//报损申请
	public ModelAndView selectDrugApply(CondiBean condiBean);//获取药品申请列表
	public ModelAndView selectPutDrug(CondiBean condiBean);//获取药品入库批次详情列表
	public ModelAndView selectBreakCheck(CondiBean condiBean);//获取报损审核列表
	public ModelAndView selectAdjustPrice(CondiBean condiBean);//获取价格调节记录
	public ModelAndView adjustLayer(double beforePrice,int drugId);//价格调整弹窗
	public String adjustPrice(AdjustPriceBean adjustPriceBean);//调整药品价格
	public ModelAndView toAdjustPrice(CondiBean condiBean);//药品调价界面
	public String sellDrug(HttpServletRequest req,StoDrugBean stoDrugBean);//卖药
	public ModelAndView sellDrugLayer(CondiBean condiBean);//卖药弹窗
	public ModelAndView toForbidDrug(CondiBean condiBean);//进入药品停用
	public String forbidDrug(StoDrugBean stoDrugBean);//停用药品
	public void checkProfit();//每月一号统计上月药品销售利润
	public void checkStock();//每月一号盘点上月药品盈亏
	public String checkStockData(TakeStockBean takeStockBean);//管理员录入药房药品实际库存
	public ModelAndView toCheckData(CondiBean condiBean);//进入盘点界面
	public ModelAndView showTakeStock(CondiBean condiBean);//查询上月盘点结果
	public ModelAndView selectPhaDrug(CondiBean condiBean);//查询药房药品
	
	/* 蓝鹏 */
	// 请领申请(lp)
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean);

	// 获取药品退库审核(lp)
	public ModelAndView selectCancellingApply(CondiBean condiBean);

	// 获取药品请领列表(lp)
	public ModelAndView totakeApplyList(CondiBean condiBean);

	// 通过拼音码/编号/名称查找药品信息(lp)
	public ModelAndView selectDrug(StoDrugBean stoDrugBean);

	// 通过拼音码/编号/名称//生产日期查找退库药品信息(lp)
	public ModelAndView chooseCaceDrug(StoDrugBean stoDrugBean);

	// 提交退库申请
	public ModelAndView cacellingApply(HttpServletRequest request,DrugApplyBean drugApplyBean);

	// 审核退库申请
	public ModelAndView chickSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean);

	// 不同意退库申请
	public ModelAndView chickError(HttpServletRequest request, String drugApplyId);

	// 不同意请领申请
	public ModelAndView takeError(HttpServletRequest request, String drugApplyId);

	// 所有退库记录
	public ModelAndView toAllCancApply(CondiBean condiBean);

	// 所有请领记录
	public ModelAndView toAllTakeApply(CondiBean condiBean);

	// 同意
	public ModelAndView takeSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean);

	// 消息提醒界面
	public ModelAndView toWarn(HttpServletRequest request);

	// 过期预警/滞销预警
	public void expirationWarn();

	// 到低限预警界面
	public ModelAndView toMinimumWarn(HttpServletRequest request, CondiBean condiBean);

	// 到滞销预警界面
	public ModelAndView toUnsalableWarn(HttpServletRequest request, CondiBean condiBean);

	// 到过期预警
	public ModelAndView toOverdueWarn(HttpServletRequest request, CondiBean condiBean);

	// 确认已处理警告
	public ModelAndView affirmWarn(HttpServletRequest request, int warnId);
}
