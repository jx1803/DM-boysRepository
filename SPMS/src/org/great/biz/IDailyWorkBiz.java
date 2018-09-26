package org.great.biz;

import java.util.List;

import org.great.bean.AdjustPriceBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;

import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
import org.springframework.ui.Model;

import org.great.bean.StoDrugBean;

import org.springframework.web.servlet.ModelAndView;

public interface IDailyWorkBiz {

	public String breakCheck(DrugApplyBean drugApplyBean);//报损审核
	public String breakApply(DrugApplyBean drugApplyBean);//报损申请
	public ModelAndView selectDrugApply(CondiBean condiBean);//获取药品申请列表
	public ModelAndView selectPutDrug(CondiBean condiBean);//获取药品入库批次详情列表
	public ModelAndView selectBreakCheck(CondiBean condiBean);//获取报损审核列表
	public ModelAndView selectAdjustPrice(CondiBean condiBean);//获取价格调节记录
	public ModelAndView adjustLayer(double beforePrice,int drugId);//价格调整弹窗
	public String adjustPrice(AdjustPriceBean adjustPriceBean);//调整药品价格
	public String sellDrug(StoDrugBean stoDrugBean);//卖药
	public ModelAndView sellDrugLayer(CondiBean condiBean);//卖药弹窗

	// 请领申请(lp)
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean);

	// 获取药品退库列表(lp)
	public ModelAndView selectCancellingApply(CondiBean condiBean);

	// 通过ajax拼音码/编号/名称查找药品信息(lp)
	public List<StoDrugBean> selectDrug(StoDrugBean stoDrugBean);


}
