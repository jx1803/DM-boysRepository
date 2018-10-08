/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.biz
 * 文件名称：IStorStatisticsBiz.java
 * 
 * 创建时间：2018年9月26日 下午5:12:26
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.biz;

import org.great.bean.CondiBean;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: IStorStatisticsBiz 
 * @Description: 药库统计业务层接口
 * @author: 周志展(lenovo)  
 * @date: 2018年9月26日 下午5:12:26  
 */
public interface IStorStatisticsBiz {
	public ModelAndView dpStatistics(CondiBean condiBean);//药品采购统计
	public ModelAndView drugOutInStatistics(CondiBean condiBean);//药品出入库明细统计
}
