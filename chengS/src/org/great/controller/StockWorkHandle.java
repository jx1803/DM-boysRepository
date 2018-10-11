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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.InventoryBean;
import org.great.bean.StoDrugBean;
import org.great.biz.IStockWorkBiz;
import org.great.tools.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/* 药品采购模块 */
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
	@Log(operationType = "", operationName = "采购申请")
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
	@Log(operationType = "", operationName = "不通过采购申请")
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

	/** 药品采购入库成功 */
	@Log(operationType = "", operationName = "采购药品入库")
	@RequestMapping(value = "/pdInstorageSuccess.action")
	public String pdInstorageSuccess(BatchDetailBean batchDetailBean) {
		return stockWorkBizImpl.pdInstorageSuccess(batchDetailBean);
	}

	/* 药品退还厂家模块 */
	/** 退还厂家界面显示 */
	@RequestMapping(value = "/toReturnManuApply.action")
	public ModelAndView toReturnManuApply() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("storage/returnManuApply");
		return mav;
	}

	/** 入库批次详情浏览 */
	@RequestMapping(value = "/getInventoryBatchList.action")
	public ModelAndView getInventoryBatchList(CondiBean condiBean) {
		return stockWorkBizImpl.getInventoryBatchList(condiBean);
	}

	/** 提交退还申请 */
	@Log(operationType = "", operationName = "提交退还申请")
	@RequestMapping(value = "/returnManuApply.action")
	public ModelAndView returnManuApply(DrugApplyBean drugApplyBean) {
		return stockWorkBizImpl.returnManuApply(drugApplyBean);
	}

	/** 所有退还审核记录列表 */
	@RequestMapping(value = "/toAllReturnManuApply.action")
	public ModelAndView toAllReturnManuApply(CondiBean condiBean) {
		return stockWorkBizImpl.toAllReturnManuApply(condiBean);
	}

	/** 未审核退还记录 */
	@RequestMapping(value = "/toReturnManuAuditList.action")
	public ModelAndView toReturnManuAuditList(CondiBean condiBean) {
		return stockWorkBizImpl.toReturnManuAuditList(condiBean);
	}

	/** 退还厂家审核 */
	@Log(operationType = "", operationName = "退还审核")
	@RequestMapping(value = "/returnManuAudit.action")
	public String returnManuAudit(HttpSession session,DrugApplyBean drugApplyBean) {
		return stockWorkBizImpl.returnManuAudit(session,drugApplyBean);
	}

	/**
	 * 导出Excel文件
	 * @throws Exception
	 */
	@Log(operationType = "", operationName = "导出已采购药品清单")
	@RequestMapping("/exportExcel.action")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
			CondiBean condibean) throws Exception {
		stockWorkBizImpl.exportExcel(request, response, condibean);
	}

}
