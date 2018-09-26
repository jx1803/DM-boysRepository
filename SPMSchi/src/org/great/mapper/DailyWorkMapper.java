package org.great.mapper;

import java.util.List;

import org.great.bean.AdjustPriceBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.OutAndInBean;
import org.great.bean.ParamBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
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
/**
 * @Description: 日常工作
 * @author: JX180327_陈文炽(wc)  
 * @date 2018年9月18日下午8:56:06
 */
	public int breakCheck(DrugApplyBean drugApplyBean);//报损审核
	public int breakApply(DrugApplyBean drugApplyBean);//报损申请
	public List<DrugApplyBean> selectDrugApply(CondiBean condiBean);//查询申请记录
	public int getDrugApplyCount(CondiBean condiBean);//获取药品申请记录数
	public List<BatchDetailBean> selectPutDrug(CondiBean condiBean);//获取入库批次详情列表
	public List<AdjustPriceBean> selectAdjustPrice(CondiBean condiBean);//获取调价记录列表
	public int insertAdjustPrice(AdjustPriceBean adjustPriceBean);//插入调价记录
	public int getAdjustCount(CondiBean condiBean);//获取调价记录数
	public ParamBean getParam(int paramId);//获取参数值
	public int adjustPrice(AdjustPriceBean adjustPriceBean);//调整药品价格
	public int insertSell(SellBean sellBean);//插入销售记录
	public int insertDrugOut(OutAndInBean outAndInBean);//插入药房出库记录
	public int insertBatchDetail(BatchDetailBean batchDetailBean);//插入药品出入库批次详情记录
	public int reducePhaDrugNum(SellBean sellBean);//减少药房的药品库存
	public int reducePhaDrugNum2(DrugApplyBean drugApplyBean);//减少药房的药品库存
	public List<PhaDrugBean> selectPhaDrug(CondiBean condiBean);//查询药房药品列表
	public PhaDrugBean selectOnePhaDrug(int drugId);//查询药房药品信息
	public int reducePhaPutDrugNum(SellBean sellBean);//减少药房入库对应的批次详情的药品数量
	/*蓝鹏鹏功能*/
	public List<StoDrugBean> selectCanSellDrug(CondiBean condiBean);//查询可出售药品
}
