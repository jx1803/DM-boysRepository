/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.biz
 * 文件名称：IStockWorkBiz.java
 * 
 * 创建时间：2018年9月18日 下午8:48:15
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;

/** 
 * @ClassName: IStockWorkBiz 
 * @Description: 库存工作业务接口类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月18日 下午8:48:15  
 */
public interface IStockWorkBiz {
	public String purchaseApply(HttpServletRequest request ,DrugApplyBean drugApply);
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean);
}
