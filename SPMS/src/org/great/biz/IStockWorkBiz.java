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

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.InventoryBean;
import org.great.bean.StoDrugBean;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: IStockWorkBiz 
 * @Description: 库存工作业务接口类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月18日 下午8:48:15  
 */
public interface IStockWorkBiz {
	public String purchaseApply(HttpServletRequest request ,DrugApplyBean drugApply);
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean);//获取药品信息列表
	public ModelAndView drugApplyAudit(CondiBean condiBean);//获取药品申请审核列表
	public ModelAndView purchaseApplyLook(CondiBean condiBean);//获取药品采购申请记录列表
	public String purchaseAuditFail(DrugApplyBean drugApplyBean);//药品采购申请审核不通过
	public ModelAndView pdInstorage(CondiBean condiBean);//获取需购买的药品列表
	public ModelAndView pdInstorageConfirm(DrugApplyBean drugApplyBean);//药品采购确认，修改购买状态
	public String pdInstorageSuccess(BatchDetailBean batchDetailBean);//采购入库成功
}
