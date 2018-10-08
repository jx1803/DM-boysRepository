package org.great.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.great.bean.AdjustPriceBean;
import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.InventoryBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TabuBean;
import org.great.bean.adjPriceStatisBean;
import org.great.mapper.DailyWorkMapper;
import org.great.mapper.DrugAllocatMapper;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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


	private int pageTotal;	//总页数
	private List<DfBean> dfList; //药品剂量信息表
	private List<DrugTypeBean> drugTypeList; //药品分类表
	private List<StoDrugBean> stoDrugList;	//药品信息表
	private List<TabuBean> tabuList;	//配伍禁忌表
	private List<InventoryBean> inventoryList;	//药品库存表
	private List<PhaDrugBean> phaDrugList;	//药房库存表
	private List<AdjustPriceBean> adjustPriceList;//药品调价信息表
	
	@Resource
	private DailyWorkMapper dailyWorkMapper;
	@Resource
	private DrugAllocatMapper damapper;
	@Resource
	private IDrugAllocatBiz iDrugAllocatBiz;
	@Resource
	private InventoryBean it;	//药品入库信息
	@Resource
	private PhaDrugBean pd;		//药房药品信息
	@Resource
	private adjPriceStatisBean aps;	//药品调价统计信息
/*****************************药品剂型管理**********************/
	//添加药品剂型
	@Override
	public String addDf(DfBean df) {
		int k = damapper.addDf(df);
		
		return "redirect:/storage/findDfInfo.action";
	}

	//显示药品剂型
	@Override
	public ModelAndView findDf(CondiBean condi) {
		
		condi.setPageup(condi.getPage()*5);
		condi.setPagedown((condi.getPage()*5-5)+1);
		dfList = damapper.findDf(condi);
		
		int count = damapper.findDfCount(condi);
		pageTotal = PageUtil.pageTotal(count);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dfList", dfList);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condi);
		mav.addObject("dosageForm", condi.getDosageForm());
		
		mav.setViewName("/storage/findDfInfo");
		return mav;
	}
	//删除药品剂型
	@Override
	public String  delDf(DfBean df) {
		int k = damapper.delDf(df);
		
		return "redirect:/storage/findDfInfo.action";
	}
	//修改药品剂型
	@Override
	public String updDf(DfBean df) {
		int k = damapper.updDf(df);
		return "redirect:/storage/findDfInfo.action";
	}

	
	//查询所有剂型信息(无条件)
	@Override
	public List<DfBean> findAllDf() {
		
		dfList = damapper.findAllDf();
		return dfList;
	}
	
	/*******************************************************************
	 * @param 药品类型操作
	 * @return
	 */
	//添加药品类型
	@Override
	public String addDrugType(DrugTypeBean dt) {
		int k = damapper.addDrugType(dt);
		return "redirect:/storage/findDrugTypeInfo.action";
	}
	//查询药品类型
	@Override
	public ModelAndView findDrugTypeInfo(CondiBean condi) {

		condi.setPageup(condi.getPage()*5);
		condi.setPagedown((condi.getPage()*5-5)+1);
		drugTypeList = damapper.findDrugTypeInfo(condi);
		
		int count = damapper.findDtCount(condi);
		pageTotal = PageUtil.pageTotal(count);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("drugTypeList", drugTypeList);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condi);
		mav.addObject("drugType", condi.getDrugType());
		
		mav.setViewName("/storage/findDrugTypeInfo");
	
		return mav;
	}
	//删除药品类型
	@Override
	public String delDrugType(DrugTypeBean dt) {
		int k = damapper.delDrugType(dt);
		
		return "redirect:/storage/findDrugTypeInfo.action";
	}
	//修改药品类型
	@Override
	public String updDrugType(DrugTypeBean dt) {
		int k = damapper.updDrugType(dt);
		
		return "redirect:/storage/findDrugTypeInfo.action";
	}
	//查询一级药品类型对应的二级分类药品类型
	@Override
	public List<DrugTypeBean> findSecondType(DrugTypeBean dt) {
		drugTypeList = damapper.findSecondType(dt);
		
		return drugTypeList;
	}
	
	/*************************************************************
	 * @param 药品管理操作
	 * @return
	 */
	//添加药品信息（同时添加药品库存信息及药房药品信息）
	@Override
	public String addDrugInfo(StoDrugBean sd) {
	
		int z = damapper.checkInfo(sd);
	
		if(z==0) {
			//添加药品信息
			int k = damapper.addDrugInfo(sd);
			//添加药库存储信息
			it.setDrugId(sd.getDrugId());
			it.setInventoryNum(0);
			it.setMinimum(300);
			it.setMaximum(3000);
			int j = damapper.addInventory(it);
			//添加药房药品信息
			pd.setDrugId(sd.getDrugId());
			pd.setDrugNum(0);
			pd.setMaximum(100);
			pd.setMinimum(10);
			pd.setUseableId(2);
			int i = damapper.addPhaDrug(pd);
			
		}
		
		return "redirect:/storage/findDrugInfo.action";
	}

	//查询药品信息
	@Override
	public ModelAndView findDrugInfo(CondiBean condi) {

		
			
		condi.setPageup(condi.getPage()*5);
		condi.setPagedown((condi.getPage()*5-5)+1);
		int count = damapper.findDrugCount(condi);
		pageTotal = PageUtil.pageTotal(count);
		
		//查询药品分类表
		drugTypeList = damapper.findAllSecondType();
		//查询药品信息
		stoDrugList = damapper.findDrugInfo(condi);
		//查询所有药品剂型
		dfList = damapper.findAllDf();
		ModelAndView mav = new ModelAndView();
		mav.addObject("drugTypeList",drugTypeList );
		mav.addObject("stoDrugList", stoDrugList);
		mav.addObject("dfList", dfList);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condi);
		mav.setViewName("/storage/findDrugInfo");
		
		return mav;

	}
	
	
	//修改药品信息
	@Override
	public String updDrugInfo(StoDrugBean sd) {
		
		int k = damapper.updDrugInfo(sd);
		
		return "redirect:/storage/findDrugInfo.action";
	}
	//药品信息查重
	@Override
	public int checkInfo(StoDrugBean sd) {
		int k = damapper.checkInfo(sd);
		System.out.println("查重biz="+k);
		return k;
	}

	/*******************************************************************
	 * @param 配伍禁忌管理
	 * @author: 程帅 
	 */
	//添加配伍禁忌
	@Override
	public String addCompTaboo(TabuBean tb) {

		int k = damapper.addCompTaboo(tb);
		return "redirect:/storage/findCompTaboo.action";
	}
	//修改配伍禁忌
	@Override
	public String updCompTaboo(TabuBean tb) {
		int k = damapper.updCompTaboo(tb);
		return "redirect:/storage/findCompTaboo.action";
	}
	//删除配伍禁忌
	@Override
	public String delCompTaboo(TabuBean tb) {
		
		int k = damapper.delCompTaboo(tb);
		
		return "redirect:/storage/findCompTaboo.action";
	}
	//查询配伍禁忌
	@Override
	public ModelAndView findCompTaboo(CondiBean condi) {
		
		condi.setPageup(condi.getPage()*5);
		condi.setPagedown((condi.getPage()*5-5)+1);
		tabuList= damapper.findCompTaboo(condi);
		
		int count = damapper.findCompTabooCount(condi);
		pageTotal = PageUtil.pageTotal(count);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("tabuList", tabuList);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condi);

		mav.setViewName("storage/findCompTaboo");
		return mav;
	}


	//匹配第一个药品名
	@Override
	public String findfirstName(TabuBean tb) {
		int k = damapper.findfirstName(tb);
		if(k>0) {
			return "";
		}else {
			return "此药品不存在";
		}
		
	}
	//匹配第二个药品名
	@Override
	public String findSecondName(TabuBean tb) {
		int k = damapper.findSecondName(tb);
		if(k>0) {
			return "";
		}else {
			return "此药品不存在";
		}
	
	}

	//查询药品库存
	@Override
	public ModelAndView findDrugInventory(CondiBean condi) {
		
		condi.setPageup(condi.getPage()*5);
		condi.setPagedown((condi.getPage()*5-5)+1);
		inventoryList= damapper.findDrugInventory(condi);
		
		int count = damapper.findDrugInventoryCount(condi);
		pageTotal = PageUtil.pageTotal(count);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("inventoryList", inventoryList);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condi);
		mav.setViewName("storage/setDrugInventory");
		return mav;
	}

	//修改药品上限和低限值
	@Override
	public String updDrugInventory(InventoryBean inventory) {
		
		int k = damapper.updDrugInventory(inventory);
		
		return "redirect:findDrugInventory.action";
	}

	@Override
	public ModelAndView findPharmacyStock(CondiBean condi) {
		
		condi.setPageup(condi.getPage()*5);
		condi.setPagedown((condi.getPage()*5-5)+1);
		phaDrugList= damapper.findPharmacyStock(condi);
		
		int count = damapper.findPharmacyStockCount(condi);
		pageTotal = PageUtil.pageTotal(count);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("phaDrugList", phaDrugList);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condi);
		mav.setViewName("storage/setPharmacyStock");
		
		return mav;
	}

	@Override
	public String updPharmacyStock(PhaDrugBean phaDrug) {

		int k = damapper.updPharmacyStock(phaDrug);
		return "redirect:findPharmacyStock.action";
	}

	/***************************药品调价统计*****************************/
	
	// 进入药品调价界面显示药品信息
		@Override
		public ModelAndView findAdjustPrice(CondiBean condi) {
			
			condi.setPageup(condi.getPage()*5);
			condi.setPagedown((condi.getPage()*5-5)+1);
			int count = damapper.findDrugCount(condi);
			pageTotal = PageUtil.pageTotal(count);
			
			//查询药品分类表
			drugTypeList = damapper.findAllSecondType();
			//查询药品信息
			stoDrugList = damapper.findDrugInfo(condi);
			//查询所有药品剂型
			dfList = damapper.findAllDf();
			ModelAndView mav = new ModelAndView();
			mav.addObject("drugTypeList",drugTypeList );
			mav.addObject("stoDrugList", stoDrugList);
			mav.addObject("dfList", dfList);
			mav.addObject("pageTotal", pageTotal);
			mav.addObject("condi", condi);
			mav.setViewName("storage/findAdjustPrice");
			return mav;
		}
	
	//调价数据
	@Override
	public adjPriceStatisBean priceData(AdjustPriceBean adjustPrice) {
		
		adjustPriceList = damapper.priceData(adjustPrice);
		
		Object[] dataArray = new Object[adjustPriceList.size()];
		Object[] countArray = new  Object[adjustPriceList.size()];
			for(int i =0;i<adjustPriceList.size();i++) {
				dataArray[i]=adjustPriceList.get(i).getBeforeAdjust();
				countArray[i]=i+1;
			}
			aps.setDataArray(dataArray);
			aps.setCountArray(countArray);
			aps.setGeneralName(adjustPriceList.get(0).getStoDrugBean().getGeneralName());
			System.out.println(aps.toString());
		return aps;
	}
	//数据总数
	@Override
	public int priceDataSum(AdjustPriceBean adjustPrice) {
		
		int sum =  damapper.priceDataSum(adjustPrice);
		return sum;
	}
	
	
}
