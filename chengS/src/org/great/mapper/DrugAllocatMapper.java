package org.great.mapper;

import java.util.List;

import org.great.bean.AdjustPriceBean;
import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.InventoryBean;
import org.great.bean.LogBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TabuBean;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName:  药品配置mapper接口  
 * @Description:供mapping的xml文件调用  ，做数据库操作
 * @author: 程帅 
 * @date:  2018年9月18日 下午3:21:30   
 *    
 * @Copyright: 2018 www.cykj.com Inc. All rights reserved. 
 * 注意：本内容仅限于传一科技内部传阅，禁止外泄以及用于其他的商业目
 */
@Repository("damapper")
public interface DrugAllocatMapper {
	
	/***************************药品剂型增删改查*****************************/
	public int addDf(DfBean df);
	public List<DfBean> findDf(CondiBean condi);
	public int delDf(DfBean df);
	public int updDf(DfBean df);
	public int findDfCount(CondiBean condi);
	public List<DfBean> findAllDf();
	
	/***************************药品类型增删改查*****************************/
	public List<DrugTypeBean> findTopType(DrugTypeBean dt);
	public int addDrugType(DrugTypeBean dt);
	public List<DrugTypeBean> findDrugTypeInfo(CondiBean condi);
	public int delDrugType(DrugTypeBean dt);
	public int findDtCount(CondiBean condi);
	public int updDrugType(DrugTypeBean dt);
	public List<DrugTypeBean> findSecondType(DrugTypeBean dt);
	public List<DrugTypeBean> findAllSecondType();
	
	/***************************药品信息增删改查*****************************/
	public int addDrugInfo(StoDrugBean sd);
	public List<StoDrugBean> findDrugInfo(CondiBean condi);
	public int delDrugInfo(StoDrugBean sd);
	public int findDrugCount(CondiBean condi);
	public int updDrugInfo(StoDrugBean sd);
	public int addInventory(InventoryBean it);//添加库存信息
	public int addPhaDrug(PhaDrugBean pd);//添加药房信息
	public int checkInfo(StoDrugBean sd);//查重
	/***************************配伍禁忌增删改查*****************************/
	public int addCompTaboo(TabuBean tb);
	public int updCompTaboo(TabuBean tb);
	public int delCompTaboo(TabuBean tb);
	public List<TabuBean> findCompTaboo(CondiBean condi);
	public int findCompTabooCount(CondiBean condi);
	//匹配药品名是否存在
	public int findfirstName(TabuBean tb);
	public int findSecondName(TabuBean tb);
	
	/***************************药品库存修改*****************************/
	public List<InventoryBean> findDrugInventory(CondiBean condi);
	public int findDrugInventoryCount(CondiBean condi);
	public int updDrugInventory(InventoryBean inventory);
	
	/***************************药房库存修改*****************************/
	public List<PhaDrugBean> findPharmacyStock(CondiBean condi);
	public int findPharmacyStockCount(CondiBean condi);
	public int updPharmacyStock(PhaDrugBean phaDrug);
	
	/***************************药品调价统计*****************************/
	
	public List<AdjustPriceBean> priceData(AdjustPriceBean adjustPrice);
	public int priceDataSum(AdjustPriceBean adjustPrice);
	
	/***************************添加日志*****************************/
	public int addLog(LogBean logBean);
}
