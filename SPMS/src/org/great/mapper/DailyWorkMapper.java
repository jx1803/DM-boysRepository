package org.great.mapper;

import java.util.List;

import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;

import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: DailyWorkMapper 
 * @Description: 日常工作持久层映射器
 * @author: JX180327_陈文炽(wc)  
 * @date: 2018年9月18日 下午3:13:44
 */

import org.great.bean.DrugTypeBean;
import org.great.bean.StoDrugBean;
import org.springframework.stereotype.Repository;


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

	
	/**
	 * @Description: TODO 
	 * @author: 蓝鹏 
	 * @date 2018年9月20日下午8:47:56
	 */
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


	// 查找退库条数(lp)
	public int selectCancellingApplyCount(CondiBean condiBean);


}
