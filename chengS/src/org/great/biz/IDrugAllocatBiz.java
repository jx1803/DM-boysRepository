package org.great.biz;

import java.util.List;

import org.great.bean.AdjustPriceBean;
import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.InventoryBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TabuBean;
import org.great.bean.adjPriceStatisBean;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName:  药品配置Biz接口  
 * @Description:供Biz实现类调用,完成业务逻辑的处理   
 * @author: 程帅 
 * @date:  2018年9月18日 下午3:25:00   
 *    
 * @Copyright: 2018 www.cykj.com Inc. All rights reserved. 
 * 注意：本内容仅限于传一科技内部传阅，禁止外泄以及用于其他的商业目
 */
public interface IDrugAllocatBiz {

	/***************************药品剂型增删改查*****************************/
	public String addDf(DfBean df);
	public ModelAndView findDf(CondiBean condi);
	public String delDf(DfBean df);
	public String updDf(DfBean df);
	public List<DfBean> findAllDf();
	
	/***************************药品类型增删改查*****************************/
	public String addDrugType(DrugTypeBean dt);
	public ModelAndView findDrugTypeInfo(CondiBean condi);
	public String delDrugType(DrugTypeBean dt);
	public String updDrugType(DrugTypeBean dt);
	public List<DrugTypeBean> findSecondType(DrugTypeBean dt);
	
	
	/***************************药品信息增删改查*****************************/
	public String addDrugInfo(StoDrugBean sd);
	public ModelAndView findDrugInfo(CondiBean condi);
	public String updDrugInfo(StoDrugBean sd);
	
	/***************************配伍禁忌增删改查*****************************/
	
	public String addCompTaboo(TabuBean tb);
	public String updCompTaboo(TabuBean tb);
	public String delCompTaboo(TabuBean tb);
	public ModelAndView findCompTaboo(CondiBean condi);
	//匹配药品名是否存在
	public String findfirstName(TabuBean tb);
	public String findSecondName(TabuBean tb);
	public int checkInfo(StoDrugBean sd);//查重
	/***************************药品库存修改*****************************/
	public ModelAndView findDrugInventory(CondiBean condi);
	public String updDrugInventory(InventoryBean inventory);
	
	/***************************药房库存修改*****************************/
	public ModelAndView findPharmacyStock(CondiBean condi);
	public String updPharmacyStock(PhaDrugBean phaDrug);
	
	/***************************药品调价统计*****************************/
	public ModelAndView findAdjustPrice(CondiBean condi) ;
	public adjPriceStatisBean priceData(AdjustPriceBean adjustPrice);
	public int priceDataSum(AdjustPriceBean adjustPrice);
}
