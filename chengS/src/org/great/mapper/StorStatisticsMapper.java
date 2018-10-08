/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.mapper
 * 文件名称：StorStatisticsMapper.java
 * 
 * 创建时间：2018年9月26日 下午5:09:25
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.mapper;

import java.util.List;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.OutAndInBean;
import org.springframework.stereotype.Repository;

/** 
 * @ClassName: StorStatisticsMapper 
 * @Description: 药库统计Dao接口
 * @author: 周志展(lenovo)  
 * @date: 2018年9月26日 下午5:09:25  
 */
@Repository
public interface StorStatisticsMapper {
	public List<BatchDetailBean> getPurchaseList(CondiBean condiBean);//获取采购记录列表
	public int getPurchaseListCount(CondiBean condiBean);//获取采购记录总数
	public List<DrugApplyBean> getDrugOutInList(CondiBean condiBean);//获取药品出入库记录列表
	public int getDrugOutInListCount(CondiBean condiBean);//获取药品出入库记录总数
}
