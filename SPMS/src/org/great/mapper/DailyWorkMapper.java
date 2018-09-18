package org.great.mapper;

import org.great.bean.DrugApplyBean;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: DailyWorkMapper 
 * @Description: 日常工作持久层映射器
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午3:13:44
 */
@Repository
public interface DailyWorkMapper {
	public int breakCheck(DrugApplyBean drugApplyBean);
}
