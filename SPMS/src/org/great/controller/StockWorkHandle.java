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

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
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

	/**
	 * 进入主页面
	 * */
	@RequestMapping("/toMain.action")
	public ModelAndView toMain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("storage/index");
		return modelAndView;
	}
	
	
	
	/**
	 * 药品采购申请页面显示
	 * */
	@RequestMapping("/purchaseApplyShow.action")
	public ModelAndView purchaseApplyShow() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("storage/purchaseApply");
		return mav;
	} 
	
	/**
	 * 根据药品编码/拼音码/药品名称获取药品信息列表
	 * */
	@RequestMapping(value="/stoDrugSearch.action",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public List<StoDrugBean> stoDrugSearch(@RequestBody StoDrugBean stoDrugBean){
		System.out.println("hhhh"+stoDrugBean.getDrugId());
		System.out.println("hhhh"+stoDrugBean.getDrugName());
		return stockWorkBizImpl.stoDrugSearch(stoDrugBean);
	}
	
	/**
	 * 药品采购申请
	 */
	@RequestMapping(value = "/purchaseApply.action")
	public String purchaseApply(HttpServletRequest request, DrugApplyBean drugApply) {
		return null;

	}

}
