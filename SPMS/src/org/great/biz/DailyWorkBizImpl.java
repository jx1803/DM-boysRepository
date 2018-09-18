package org.great.biz;

import javax.annotation.Resource;

import org.great.bean.DrugApplyBean;
import org.great.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: DailyWorkBizImpl 
 * @Description: TODO
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午2:58:48
 */
@Service
public class DailyWorkBizImpl implements IDailyWorkBiz {
	@Resource
	private UserMapper userMapper;
	
	@Override
	public String breakCheck(DrugApplyBean drugApplyBean) {
		//如果为审核通过
		if(drugApplyBean.getApplyTypeId()==6) {
			
		}
		//如果为审核不通过
		if(drugApplyBean.getApplyTypeId()==8) {
			
		}
		return null;
	}

	@Override
	public String breakApply(DrugApplyBean drugApplyBean) {
		
		return null;
	}
	

}
