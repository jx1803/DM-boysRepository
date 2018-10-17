package org.great.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.great.bean.AdjustPriceBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CheckBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.OutAndInBean;
import org.great.bean.ParamBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.RoleBean;
import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TakeStockBean;
import org.great.bean.WarnBean;
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
	public int getPhaDrugCount(CondiBean condiBean);//获取药房药品记录数
	public PhaDrugBean selectOnePhaDrug(int drugId);//查询药房药品信息
	public int reducePhaPutDrugNum(SellBean sellBean);//减少药房入库对应的批次详情的药品数量
	public List<StoDrugBean> findDrugInfo(CondiBean condi);//查询药品信息
	public int findDrugCount(CondiBean condi);//查询药品数量

	public int forbidDrug(PhaDrugBean phaDrugBean);//停用/启用药房药品
	public List<SellBean> selectSellDrug(CondiBean condiBean);//查询销售记录
	public int insertCheckProfit(CheckBean checkBean);//插入盘点记录
	public int getBreakDrugNum(CondiBean condiBean);//获取药品破损数量
	public Integer getDrugCurrentNum(CondiBean condiBean);//获取药房药品流动数量
	public int insertCheckStock(TakeStockBean takeStockBean);//插入药品盘点盈亏记录
	public int checkStockData(TakeStockBean takeStockBean);//核对药房药品库存
	public List<TakeStockBean> selectTakeStockList(CondiBean condiBean);//获取药房盘点盈亏记录
	public int getTakeStockCount(CondiBean condiBean);//获取药房盘点记录数
	public List<TakeStockBean>  getTakeStockList(CondiBean condiBean);//获取药房盘点盈亏记录

	
	
	/*蓝鹏鹏功能*/
	public List<BatchDetailBean> selectCanSellDrug(CondiBean condiBean);// 查询可出售药品
	// 请领申请(lp)

	public void takeDrugAppleFor(DrugApplyBean drugApplyBean);

	// 找到药品后查找库存(lp)
	public int selDrugNumBydDrugId(int id);

	// 查找退库条数(lp)
	public int selectCancellingApplyCount(CondiBean condiBean);

	// 查找药品(lp)
	public List<StoDrugBean> selectDrug(StoDrugBean stoDrugBean);

	// 查找要退库的药品(lp)
	public List<BatchDetailBean> chooseCaceDrug(StoDrugBean stoDrugBean);

	// 提交退库申请(lp)
	public void cacellingApply(DrugApplyBean drugApplyBean);

	// 通过退库审核
	// (1)改变退库申请的状态时间
	public void updayeCaceApply(String drugApplyId, String adminId);

	public BatchDetailBean selectInfo(BatchDetailBean batchDetailBean);

	public int insetPhaBatch(BatchDetailBean batchDetailBean);// 插入详情

	public void insetPhaOut(int adminId);// 插入药房出库记录

	public void updatePhaDrugNum(int number, int drugid);// 减少药房库存

	public void insetStoIn(String putBatch, String adminId);// 插入药库入库记录

	public void updatePhaBatchNum(BatchDetailBean batchDetailBean);// 增加药库入库批次的数量

	public void updateStoDrugNum(int number, int drugid);// 修改药库库存

	public void updatePutBatchNum(int id, int number, int drugId);// 减少药房对应批次数量
	// 通过退库审核

	// 不同意退库申请
	public void chickError(String drugApplyId, String adminId);

	public DrugApplyBean selApply(String drugApplyId);

	public BatchDetailBean selectPhaInfo(BatchDetailBean batchDetailBean);

	// 同意请领申请
	public List<BatchDetailBean> selectStoIn(int drugId);// 查找药库采购信息找到该要详细信息

	public void updateTakeApply(String applyId, String adminId, String manuBatch);// 修改申请信息

	public void insetStoOut(String putBatch, String adminId);// 插入药库出库库记录

	public void insetPhaInt(int bacthId, int adminId);// 插入药房入库记录

	public void reduceStoDrugNum(int number, int drugid);// 减少药库库存

	public void addPhaDrugNum(int number, int drugid);// 增加药房库存

	public String selSpecific(int drugId);// 找到规格

	public List<BatchDetailBean> selBatchBean(String drugId, String manuBatch);

	// 低限预警
	public PhaDrugBean selPhaMinimum(int drugId);// 查找药房中该药品的当前数量,和低限数量

	public void insertPhaMinWarn(String event);// 插入药房低限记录

	public List<WarnBean> selWarn(@Param("belongId") int belongId);// 通过登录角色来查找所有提醒

	public StoDrugBean selStoMinimum(int drugId);// 查找药库中该药品的当前数量,和低限数量

	public void insertStoMinWarn(String event, String eventId, String belongId); // 插入药库低限记录

	public List<StoDrugBean> selAllStoDrug();// 查找所有药

	// 查找滞销的药品
	public List<StoDrugBean> unsalableDrug(String time);

	public List<WarnBean> selAllWarn(CondiBean condiBean);

	public int selAllWarnCount(CondiBean condiBean);

	public void affirmWarn(int warnId);
	
	public RoleBean selRole(String adminId);//查找用户角色
	

	//麻醉药品入库明细
	public List<PhaDrugBean> selectSpecidlDrug(CondiBean condiBean);
	//特殊药品入库条数
	public int selectSecialDrugComm(CondiBean condiBean);//
	//特殊药品库存查询
	public List<PhaDrugBean> specialdrugstocks(CondiBean condiBean);
	//特殊药品库存条数
	public int specialdrugstocksComm(CondiBean condiBean);

	//特殊药品销售明细
	public List<PhaDrugBean> specialdrugsell(CondiBean condiBean);
	//特殊药品条数
	public int specialsellComm(CondiBean condiBean);
	//找出特殊药品分类
	public List<DrugTypeBean> speciadrugType(CondiBean condiBean);

}
