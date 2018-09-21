/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.mapper
 * 文件名称：StockWorkMapper.java
 * 
 * 创建时间：2018年9月18日 下午8:43:37
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.mapper;

import java.util.List;


import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.springframework.stereotype.Repository;

/** 
 * @ClassName: StockWorkMapper 
 * @Description: 库存工作Mapper接口类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月18日 下午8:43:37  
 */
@Repository
public interface StockWorkMapper {
	public int purchaseApply(DrugApplyBean drugApplyBean);
	public int purchaseAudit(DrugApplyBean drugApplyBean);
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean);
	

}
