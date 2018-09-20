package org.great.biz;

import java.util.List;

import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;

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

	public String addDf(DfBean df);
	public List<DfBean> findDf();
	public String delDf(DfBean df);
	
	public String addDrugType(DrugTypeBean dt);
	public List<DrugTypeBean> findDfDrugType();
	public String delDrugType(DrugTypeBean dt);
}
