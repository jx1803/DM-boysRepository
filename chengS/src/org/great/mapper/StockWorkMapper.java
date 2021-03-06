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

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.InventoryBean;
import org.great.bean.OutAndInBean;
import org.great.bean.StoDrugBean;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

/** 
 * @ClassName: StockWorkMapper 
 * @Description: 库存工作Mapper接口类
 * @author: 周志展(lenovo)  
 * @date: 2018年9月18日 下午8:43:37  
 */
@Repository
public interface StockWorkMapper {
	/**药品采购*/
	public int purchaseApply(DrugApplyBean drugApplyBean);//药品申请
	public int purchaseAudit(DrugApplyBean drugApplyBean);//药品审核通过
	public int purchaseAuditFail(DrugApplyBean drugApplyBean);//药品审核不通过
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean);//查询采购申请的药品，返回列表
	public int updatePurState(DrugApplyBean drugApplyBean);//更改确认采购的状态，确认入库
	public StoDrugBean getStoDrugBean(DrugApplyBean drugApplyBean);//根据药品ID获取药品信息
	public int addBatchDetail(BatchDetailBean batchDetailBean);//添加出入库批次详情表
	public int addPurOutAndIn(OutAndInBean outAndInBean);//添加药品出入库记录表
	public int updateInventory(InventoryBean inventoryBean);//药品采购入库，增加药品库存
	public int updatePurPrice(BatchDetailBean batchDetailBean);//修改药库药品进价
	
	/**退还厂家*/
	public List<BatchDetailBean> getStoBatchList(CondiBean condiBean);//获取库存批次详情列表
	public int addReturnApply(DrugApplyBean drugApplyBean);//将退还申请插入申请表中
	public int updateStoBatchNum(BatchDetailBean batchDetailBean);//修改药库批次详情数量
	public int updateInventoryNum(int InventoryNum,int drugId);//修改药库库存数量
	public List<DrugApplyBean> selectDrugApply(CondiBean condiBean);//获取申请类表记录
	public int selReturnManuApplyCount(CondiBean condiBean);//申请退还厂家的所有记录总数
	/**通过退还审核*/
	public int udReturnManuApplyState(DrugApplyBean drugApplyBean);//更新退还审核状态，更改确认时间,更改操作者
	public int insertBatchDetail(BatchDetailBean batchDetailBean);//插入药品出入库批次详情记录
	public int insertToOut(OutAndInBean outAndInBean);//将退还厂家出库记录插入出入库记录表中
	public int reduceInventoryNum(DrugApplyBean drugApplyBean);//退还审核不通过，将减少的库存数量加回
	public int reduceStoPutDrugNum(DrugApplyBean drugApplyBean);//退还审核不通过，将出入库详情表中减少的数量加回去
	public List<BatchDetailBean> getPurchaseList(CondiBean condiBean);//获取已购买表记录
}
