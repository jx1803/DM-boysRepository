package org.great.biz;



import org.great.bean.CondiBean;

import org.springframework.web.servlet.ModelAndView;

public interface ISpecialDrugsBiz {
	public ModelAndView specialDrugs(CondiBean cond);//麻醉药品入库信息
	public ModelAndView specialStocks(CondiBean condiBean);//药房特殊药品库存信息
	public ModelAndView specialDrugSell(CondiBean condiBean);//特殊药品销售明细
	
}
