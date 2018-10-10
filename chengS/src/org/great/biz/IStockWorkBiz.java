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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.InventoryBean;
import org.great.bean.StoDrugBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: IStockWorkBiz 
 * @Description: 库存工作业务接口类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月18日 下午8:48:15  
 */
public interface IStockWorkBiz {
	/**药品采购模块*/
	public String purchaseApply(HttpServletRequest request ,DrugApplyBean drugApply);
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean);//获取药品信息列表
	public ModelAndView drugApplyAudit(CondiBean condiBean);//获取药品申请审核列表
	public ModelAndView purchaseApplyLook(CondiBean condiBean);//获取药品采购申请记录列表
	public String purchaseAuditFail(DrugApplyBean drugApplyBean);//药品采购申请审核不通过
	public ModelAndView pdInstorage(CondiBean condiBean);//获取需购买的药品列表
	public ModelAndView pdInstorageConfirm(DrugApplyBean drugApplyBean);//药品采购确认，修改购买状态
	public String pdInstorageSuccess(BatchDetailBean batchDetailBean);//采购入库成功
	
	/**药品退还模块*/
	public ModelAndView getInventoryBatchList(CondiBean condiBean);//获取出入库批次详情列表
	public ModelAndView returnManuApply(DrugApplyBean drugApplyBean);//提交退还厂家申请
	public ModelAndView toAllReturnManuApply(CondiBean condiBean);//退还厂家记录查询
	public ModelAndView toReturnManuAuditList(CondiBean condiBean);//退还厂家未审核记录查询
	public String returnManuAudit(HttpSession session,DrugApplyBean drugApplyBean);//退还审核
	
	/**导出Excel文件
	 * @throws Exception */
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,CondiBean condibean) throws Exception;//导出Excel文件
	
}
