package org.great.biz;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.CondiBean;
import org.great.bean.PhaDrugBean;
import org.great.mapper.DailyWorkMapper;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
@Service
public class SpecialDrugsBizImpl implements ISpecialDrugsBiz {
	private List list;
	private List ulist;
	@Resource
	private DailyWorkMapper dailyWorkMapper;// 映射器（日常工作）

	//麻醉药品入库明细
	@Override
	public ModelAndView specialDrugs(CondiBean condiBean ) {
		// TODO Auto-generated method stub
		System.out.println("页码"+condiBean.getPage());
		condiBean.setPageup(condiBean.getPage()*5);
		condiBean.setPagedown((condiBean.getPage()*5-5)+1);
		list = dailyWorkMapper.selectSpecidlDrug(condiBean);//特殊药品入库明细
		int count = dailyWorkMapper.selectSecialDrugComm(condiBean);//特殊药品总数
		int pageTotal=PageUtil.pageTotal(dailyWorkMapper.selectSecialDrugComm(condiBean));
		ModelAndView mav = new ModelAndView("pharmacy/specialDrugsInStorage");
		mav.addObject("outInList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", count);
		mav.addObject("pageTotal", pageTotal);
		return mav;
	}

	//特殊药品库存明细
	@Override
	public ModelAndView specialStocks(CondiBean condiBean) {
		// TODO Auto-generated method stub
		System.out.println("页码"+condiBean.getPage());
		condiBean.setPageup(condiBean.getPage()*5);
		condiBean.setPagedown((condiBean.getPage()*5-5)+1);
		List<PhaDrugBean> list = dailyWorkMapper.specialdrugstocks(condiBean);
		int count = dailyWorkMapper.specialdrugstocksComm(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		//特殊药品分类
		ulist=dailyWorkMapper.speciadrugType(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/specialDrugStocks");
		mav.addObject("condiBean", condiBean);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("ulist",ulist);
		mav.addObject("count", count);
		mav.addObject("drugList",list);
		return mav;
	}

	
	//特殊药品销售明细
	@Override
	public ModelAndView specialDrugSell(CondiBean condiBean) {
		// TODO Auto-generated method stub
		System.out.println("页码"+condiBean.getPage());
		condiBean.setPageup(condiBean.getPage()*5);
		condiBean.setPagedown((condiBean.getPage()*5-5)+1);
		list=dailyWorkMapper.specialdrugsell(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/specialDrugSell");
		//特殊药品分类
		ulist=dailyWorkMapper.speciadrugType(condiBean);
		int count = dailyWorkMapper.specialsellComm(condiBean); 
		int pageTotal = PageUtil.pageTotal(count);
		mav.addObject("ulist",ulist);
		mav.addObject("list",list);
		mav.addObject("pageTotal",pageTotal);
		mav.addObject("condiBean", condiBean);
		return mav;
	}

}
