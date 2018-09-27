package org.great.biz;

import java.util.*;

import org.great.bean.AdjustPriceBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
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
	public String breakCheck(DrugApplyBean drugApplyBean);//报损审核
	public String breakApply(DrugApplyBean drugApplyBean);//报损申请
	public ModelAndView selectDrugApply(CondiBean condiBean);//获取药品申请列表
	public ModelAndView selectPutDrug(CondiBean condiBean);//获取药品入库批次详情列表
	public ModelAndView selectBreakCheck(CondiBean condiBean);//获取报损审核列表
	public ModelAndView selectAdjustPrice(CondiBean condiBean);//获取价格调节记录
	public ModelAndView adjustLayer(double beforePrice,int drugId);//价格调整弹窗
	public String adjustPrice(AdjustPriceBean adjustPriceBean);//调整药品价格
	public ModelAndView toAdjustPrice(CondiBean condiBean);//药品调价界面
	public String sellDrug(StoDrugBean stoDrugBean);//卖药
	public ModelAndView sellDrugLayer(CondiBean condiBean);//卖药弹窗
}
