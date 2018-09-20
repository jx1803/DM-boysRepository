package org.great.biz;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.mapper.DailyWorkMapper;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: DailyWorkBizImpl 
 * @Description: 日常工作biz实现类
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午2:58:48
 */
@Service
public class DailyWorkBizImpl implements IDailyWorkBiz {
	@Resource
	private  DailyWorkMapper dailyWorkMapper;
	
	//报损审核
	@Override
	public String breakCheck(DrugApplyBean drugApplyBean) {
		int i=dailyWorkMapper.breakCheck(drugApplyBean);
		return null;
	}

	//报损申请
	@Override
	public String breakApply(DrugApplyBean drugApplyBean) {
		int i=dailyWorkMapper.breakApply(drugApplyBean);
		System.out.println(i);
				
		return null;
	}

	//申请记录查询
	@Override
	public ModelAndView selectDrugApply(CondiBean condiBean) {
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		int count=dailyWorkMapper.getDrugApplyCount(condiBean);
		int pageTotal=PageUtil.pageTotal(count);
		ModelAndView mav=new ModelAndView("pharmacy/breakApplyList");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("drugApplyList", list);
		return mav;
	}

}
