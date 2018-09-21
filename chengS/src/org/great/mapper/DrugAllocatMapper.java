package org.great.mapper;

import java.util.List;

import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.StoDrugBean;
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
	
	/***************************药品类型增删改查*****************************/
	public List<DrugTypeBean> findTopType(DrugTypeBean dt);
	public int addDrugType(DrugTypeBean dt);
	public List<DrugTypeBean> findDfDrugType(CondiBean condi);
	public int delDrugType(DrugTypeBean dt);
	public int findDtCount(CondiBean condi);
	public int updDrugType(DrugTypeBean dt);
	
	/***************************药品信息增删改查*****************************/
	public int addDrugInfo(StoDrugBean sd);
	public List<DrugTypeBean> findDrugInfo(CondiBean condi);
	public int delDrugInfo(StoDrugBean sd);
	public int findDrugCount(CondiBean condi);
	public int updDrugInfo(StoDrugBean sd);
}
