package org.great.biz;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.mapper.DrugAllocatMapper;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName:  药品配置Biz实现类
 * @Description:处理页面和后台的业务逻辑，将结果提供给handle  
 * @author: 程帅 
 * @date:  2018年9月18日 下午3:26:00   
 *    
 * @Copyright: 2018 www.cykj.com Inc. All rights reserved. 
 * 注意：本内容仅限于传一科技内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class DrugAllocatBizImpl implements IDrugAllocatBiz{

	@Resource
	private DrugAllocatMapper damapper;

	@Override
	public String addDf(DfBean df) {

		damapper.addDf(df);
		
		return "DrugInfo";
	}

	@Override
	public List<DfBean> findDf() {
		
		
		return null;
	}

	@Override
	public String delDf(DfBean df) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addDrugType(DrugTypeBean dt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DrugTypeBean> findDfDrugType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delDrugType(DrugTypeBean dt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
