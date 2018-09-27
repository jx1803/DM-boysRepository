/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.controller
 * 文件名称：StockWorkHandle.java
 * 
 * 创建时间：2018年9月18日 下午8:47:34
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.InventoryBean;
import org.great.bean.StoDrugBean;
import org.great.biz.IStockWorkBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: StockWorkHandle
 * @Description: 库存工作控制类
 * @author: 周志展(lenovo)
 * @date: 2018年9月18日 下午8:47:34
 */
@Controller
@RequestMapping("/storage")
public class StockWorkHandle {
	@Resource
	private IStockWorkBiz stockWorkBizImpl;
	private List<StoDrugBean> stoDrugList;

	/**
	 * 进入主页面
	 */
	@RequestMapping("/toMain.action")
	public ModelAndView toMain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("storage/index");
		return modelAndView;
	}

	/*****************************************************
	 * @Description 药品采购模块
	 * 
	 */
	/**
	 * 药品采购申请页面显示
	 */
	@RequestMapping("/purchaseApplyShow.action")
	public ModelAndView purchaseApplyShow() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("storage/purchaseApply");
		return mav;
	}

	/** 药品选择页面显示,根据药品编码/拼音码/药品名称获取药品信息列表 */
	@RequestMapping("/drugSelectShow.action")
	public ModelAndView drugSelectShow(StoDrugBean stoDrugBean) {
		ModelAndView mav = new ModelAndView();
		stoDrugList = stockWorkBizImpl.stoDrugSearch(stoDrugBean);
		mav.addObject("stoDrugList", stoDrugList);
		mav.setViewName("storage/drugSelectList");
		return mav;
	}


	/** 药品采购申请，插入申请记录 */
	@RequestMapping(value = "/purchaseApply.action")
	public String purchaseApply(HttpServletRequest request, DrugApplyBean drugApply) {
		System.out.println("插入数据");
		return stockWorkBizImpl.purchaseApply(request, drugApply);

	}

	/** 查看药品申请审核列表 */
	@RequestMapping("/drugApplyAudit.action")
	public ModelAndView drugApplyAudit(CondiBean condiBean) {
		return stockWorkBizImpl.drugApplyAudit(condiBean);
	}

	/** 查看药品采购申请列表记录 */
	@RequestMapping("/purchaseApplyLook.action")
	public ModelAndView purchaseApplyLook(CondiBean condiBean) {
		return stockWorkBizImpl.purchaseApplyLook(condiBean);
	}

	/** 药品采购申请审核不通过 */
	@RequestMapping(value = "/purchaseAuditFail.action")
	public String purchaseAuditFail(DrugApplyBean drugApplyBean) {
		System.out.println("进入审核不通过");
		return stockWorkBizImpl.purchaseAuditFail(drugApplyBean);
	}

	/** 获取药品待购买记录 */
	@RequestMapping(value = "/pdInstorage.action")
	public ModelAndView pdInstorage(CondiBean condiBean) {
		return stockWorkBizImpl.pdInstorage(condiBean);
	}

	/** 显示药品确认购买界面 */
	@RequestMapping(value = "/pdInstorageConfirm.action")
	public ModelAndView pdInstorageConfirm(DrugApplyBean drugApplyBean) {
		return stockWorkBizImpl.pdInstorageConfirm(drugApplyBean);
	}
	
	/**药品采购入库成功*/
	@RequestMapping(value="/pdInstorageSuccess.action")
	public String pdInstorageSuccess(BatchDetailBean batchDetailBean) {
		return stockWorkBizImpl.pdInstorageSuccess(batchDetailBean);
	}
}
