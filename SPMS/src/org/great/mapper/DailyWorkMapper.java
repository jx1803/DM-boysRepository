package org.great.mapper;

import java.util.List;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.StoDrugBean;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

@Repository
public interface DailyWorkMapper {
	// 请领申请(lp)
	public void takeDrugAppleFor(DrugApplyBean drugApplyBean);

	// 找1级分类(lp)
	public List<DrugTypeBean> sel1DrugType();

	// 找2级分类(lp)
	public List<DrugTypeBean> sel2DrugType(int id);

	// 找2级分类下药品(lp)
	public List<StoDrugBean> selDrugFrom2(int id);

	// 找到药品后查找库存(lp)
	public int selDrugNumBydDrugId(int id);

	// 查找退库列表(lp)
	public List<DrugApplyBean> selectDrugApply(CondiBean condiBean);

	// 查找退库条数(lp)
	public int selectCancellingApplyCount(CondiBean condiBean);

	// 查找药品(lp)
	public List<StoDrugBean> selectDrug(StoDrugBean stoDrugBean);

	// 查找要退库的药品(lp)
	public List<StoDrugBean> chooseCaceDrug(StoDrugBean stoDrugBean);

	// 提交退库申请(lp)
	public void cacellingApply(DrugApplyBean drugApplyBean);
	
	//通过退库审核
	//(1)改变退库申请的状态时间
	public void updayeCaceApply(String drugApplyId);
	public BatchDetailBean selectInfo(BatchDetailBean batchDetailBean);
	public void insetPhaBatch(BatchDetailBean batchDetailBean);//插入详情
	public void insetPhaOut(int adminId);//插入药房出库记录
	public void updatePhaDrugNum(int number,int drugid);//减少药房库存
	public void insetStoIn(String putBatch,int adminId);//插入药库入库记录
	public void updateStoDrugNum(int number,int drugid);
	
}
