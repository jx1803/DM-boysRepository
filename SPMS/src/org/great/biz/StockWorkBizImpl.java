/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.biz
 * 文件名称：StockWorkBizImpl.java
 * 
 * 创建时间：2018年9月18日 下午8:49:18
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.StoDrugBean;
import org.great.mapper.StockWorkMapper;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: StockWorkBizImpl 
 * @Description: 库存工作业务实现类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月18日 下午8:49:18  
 */
@Service
public class StockWorkBizImpl implements IStockWorkBiz {
	@Resource
	private StockWorkMapper stockWorkMapper;

	/***
	 *   药品采购申请
	 * @see org.great.biz.IStockWorkBiz#purchaseApply(org.great.bean.DrugApplyBean)
	 */
	@Override
	public String purchaseApply(HttpServletRequest request ,DrugApplyBean drugApplyBean) {
		// TODO Auto-generated method stub
		//drugApplyBean.setAdminId(adminId);
		drugApplyBean.setCheckId(7);
		drugApplyBean.setApplyTypeId(10);
		
		String[] ids = request.getParameterValues("drugId");
		String[] applyNum = request .getParameterValues("applyNum");
		String[] applyReason = request.getParameterValues("applyReason");
		for(int i=0,len=ids.length;i<len;i++) {
			drugApplyBean.setDrugApplyId(Integer.valueOf(ids[i]));
			drugApplyBean.setApplyNum(Integer.valueOf(applyNum[i]));
			drugApplyBean.setApplyReason(applyReason[i]);
			stockWorkMapper.purchaseApply(drugApplyBean);
		}
		return null;
	}

	/**
	 * 通过拼音码/药品编号/药品名称查找药品
	 * @see org.great.biz.IStockWorkBiz#stoDrugSearch(org.great.bean.StoDrugBean)
	 */
	@Override
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean) {
		// TODO Auto-generated method stub
		return stockWorkMapper.stoDrugSearch(stoDrugBean);
	}
	

}
