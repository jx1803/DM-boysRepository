package org.great.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.CondiBean;
import org.great.biz.ISpecialDrugsBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pharmacy")
public class SpecialDrugsHandle {
	@Resource 
	private ISpecialDrugsBiz specialdrugsBizImpl;
	
	//特殊药品入库明细
	@RequestMapping("/specialDrugsInStorage.action")
	public ModelAndView specialDrugs(HttpServletRequest req,CondiBean condiBean) {
		return specialdrugsBizImpl.specialDrugs(condiBean);
	}
	
	//特殊药品库存
	@RequestMapping(value="/specialDrugStocks.action")
	public ModelAndView specialDrugStocks(HttpServletRequest req,CondiBean condiBean) {
		return specialdrugsBizImpl.specialStocks(condiBean);
	}
	
	//特殊药品销售明细
	@RequestMapping(value="/specialDrugSell.action")
	public ModelAndView specialDrugSell(HttpServletRequest req,CondiBean condiBean) {
		return specialdrugsBizImpl.specialDrugSell(condiBean);
	}
}
