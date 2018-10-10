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
	@Resource
	private DailyWorkMapper dailyWorkMapper;// 映射器（日常工作）

	//麻醉药品入库明细
	@Override
	public ModelAndView specialDrugs(CondiBean condiBean ) {
		// TODO Auto-generated method stub
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
		List<PhaDrugBean> list = dailyWorkMapper.specialdrugstocks(condiBean);
		System.out.println("难道没进来？");
		int count = dailyWorkMapper.specialdrugstocksComm(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/specialDrugStocks");
		mav.addObject("condiBean", condiBean);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("drugList",list);
		return mav;
	}

	
	//特殊药品销售明细
	@Override
	public ModelAndView specialDrugSell(CondiBean condiBean) {
		// TODO Auto-generated method stub
		dailyWorkMapper.specialdrugsell(condiBean);
		return null;
	}

}
