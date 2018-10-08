/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.biz
 * 文件名称：StorStatisticsBizImpl.java
 * 
 * 创建时间：2018年9月26日 下午5:13:36
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.biz;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.mapper.StorStatisticsMapper;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: StorStatisticsBizImpl 
 * @Description: 药库统计业务实现层
 * @author: 周志展(lenovo)  
 * @date: 2018年9月26日 下午5:13:36  
 */
@Service
public class StorStatisticsBizImpl implements IStorStatisticsBiz {
	@Resource
	private StorStatisticsMapper storStatisticsMapper;//药库统计操作Dao

	/**
	 * 药品采购统计
	 * @see org.great.biz.IStorStatisticsBiz#dpStatistics(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView dpStatistics(CondiBean condiBean) {
		condiBean.setCheckId(24);// 审核类型：已购买
		condiBean.setApplyTypeId(13);// 采购类型
		List<BatchDetailBean> list = storStatisticsMapper.getPurchaseList(condiBean);
		int count = storStatisticsMapper.getPurchaseListCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/dpStatistics");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("condiBean", condiBean);
		mav.addObject("drugPurchaseList", list);
		return mav;
	}

	/**
	 * 药品出入库明细统计
	 * @see org.great.biz.IStorStatisticsBiz#drugOutInStatistics(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView drugOutInStatistics(CondiBean condiBean) {
		List<DrugApplyBean> list = storStatisticsMapper.getDrugOutInList(condiBean);
		int count = storStatisticsMapper.getDrugOutInListCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/drugOutInStatistics");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("condiBean", condiBean);
		mav.addObject("outInList", list);
		return mav;
	}

}
