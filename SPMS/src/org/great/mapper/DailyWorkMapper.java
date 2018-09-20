package org.great.mapper;

import java.util.List;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.StoDrugBean;
import org.springframework.stereotype.Repository;

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

	// 通过名字寻找药(lp)
	public List<StoDrugBean> selDrugByName(String name);

	// 通过拼音码寻找药(lp)
	public List<StoDrugBean> selDrugBySpell(String name);

	// 找到药品后查找库存(lp)
	public int selDrugNumBydDrugId(int id);

	// 查找退库列表(lp)
	public List<DrugApplyBean> selectDrugApply(CondiBean condiBean);

	// 查找退库条数(lp)
	public int selectCancellingApplyCount(CondiBean condiBean);

}
