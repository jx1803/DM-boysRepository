package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.AdjPriceStatisBean;
import org.great.bean.AdjustPriceBean;
import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.InventoryBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TabuBean;
import org.great.biz.IDrugAllocatBiz;
import org.great.tools.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**
 * @ClassName:  药品配置操作
 * @Description:获取页面信息，调用biz提供的结果，进行页面及方法间的跳转
 * （完成药品的 分类管理、药品信息管理、  配伍禁忌、医保药品核对、药品的低限设置）
 * @author: 程帅 
 * @date:  2018年9月18日 下午3:12:49   
 *    
 * @Copyright: 2018 www.cykj.com Inc. All rights reserved. 
 * 注意：本内容仅限于传一科技内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
@RequestMapping("/storage")
public class DrugAllocatHandle {

	@Resource
	private IDrugAllocatBiz iDrugAllocatBiz;

	
	/*************************************************************
	 * @param 药品管理操作
	 * @author: 程帅 
	 */
		//进入药品信息列表
		@RequestMapping(value="/findDrugInfo.action")
		public ModelAndView findDrugInfo(CondiBean condi) {
			
			return iDrugAllocatBiz.findDrugInfo(condi);
		}
		
		//药品信息查重
		@RequestMapping(value="/checkInfo.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  int checkInfo(StoDrugBean sd){
				System.out.println(sd.toString());
			return iDrugAllocatBiz.checkInfo(sd);
		}
		
		//添加药品信息
		@Log(operationType = "添加操作", operationName = "添加药品信息")
		@RequestMapping(value="/addDrugInfo.action")
		public String addDrugInfo(StoDrugBean sd) {
		
			return iDrugAllocatBiz.addDrugInfo(sd);
		}
		
		//获取所有药品剂型
		@RequestMapping(value="/findDf.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  List<DfBean> findTopType(){
			
			return iDrugAllocatBiz.findAllDf();
		}
		
		//获取所有药品类型
		@RequestMapping(value="/findSecondType.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  List<DrugTypeBean> findSecondType(DrugTypeBean dt){
			
			return iDrugAllocatBiz.findSecondType(dt);
		}
				
		
		//修改药品信息
		@Log(operationType = "修改操作", operationName = "添加药品信息")
		@RequestMapping(value="/updDrugInfo.action")
		public String updDrugInfo(StoDrugBean sd) {
			
			return iDrugAllocatBiz.updDrugInfo(sd);
		}
	
		/********************************************************************
		 * @param 药品剂型管理操作
		 * @author: 程帅 
		 */
		//进入药品剂型列表
		@RequestMapping(value="/findDfInfo.action")
		public ModelAndView findDf(CondiBean condi) {
			
			return iDrugAllocatBiz.findDf(condi);
		}
		
		//添加药品剂型
		@Log(operationType = "添加操作", operationName = "添加药品剂型")
		@RequestMapping(value="/addDfInfo.action")
		public String addDfInfo(HttpServletRequest req,DfBean df) {
			
			return iDrugAllocatBiz.addDf(df);
		}
		
		//修改药品剂型
		@Log(operationType = "修改操作", operationName = "修改药品剂型")
		@RequestMapping(value="/updDfInfo.action")
		public String updDfInfo(HttpServletRequest req,DfBean df) {
			
			return iDrugAllocatBiz.updDf(df);
		}
		//删除药品剂型
		@Log(operationType = "删除操作", operationName = "删除药品剂型")
		@RequestMapping(value="/delDfInfo.action")
		public String delDfInfo(HttpServletRequest req,DfBean df) {
			
			return iDrugAllocatBiz.delDf(df);
		}
		
		
		/*******************************************************************
		 * @param 药品类型管理
		 * @author: 程帅 
		 */
		//进入药品类型列表
		
		@RequestMapping(value="/findDrugTypeInfo.action")
	
		public ModelAndView findDrugTypeInfo(CondiBean condi) {
			
			return iDrugAllocatBiz.findDrugTypeInfo(condi);
		}
		
		//添加药品类型
		@Log(operationType = "添加操作", operationName = "添加药品类型")
		@RequestMapping(value="/addDrugTypeInfo.action")
		public String addDrugTypeInfo(DrugTypeBean dt) {
			
			return iDrugAllocatBiz.addDrugType(dt);
		}
		//删除药品类型
		@Log(operationType = "删除操作", operationName = "删除药品类型")
		@RequestMapping(value="/delDrugTypeInfo.action")
		public String delDrugTypeInfo(DrugTypeBean dt) {
			
			return iDrugAllocatBiz.delDrugType(dt);
		}
		
		//修改药品类型
		@Log(operationType = "修改操作", operationName = "修改药品类型")
		@RequestMapping(value="/updDrugTypeInfo.action")
		public String updDrugTypeInfo(DrugTypeBean dt) {
			
			return iDrugAllocatBiz.updDrugType(dt);
		}
		
	/*******************************************************************
	 * @param 配伍禁忌管理
	 * @author: 程帅 
	 */
		//进入配伍禁忌管理
		@RequestMapping(value="/findCompTaboo.action")
		public ModelAndView findCompTaboo(CondiBean condi) {
			
			return iDrugAllocatBiz.findCompTaboo(condi);
		}
		
		//匹配第一个药品名
		@RequestMapping(value="/findfirstName.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  String findfirstName(TabuBean tb){
			
			return iDrugAllocatBiz.findfirstName(tb);
		}
		
		//匹配第二个药品名
		@RequestMapping(value="/findSecondName.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  String findSecondName(TabuBean tb){
			
			return iDrugAllocatBiz.findSecondName(tb);
		}
		
		//添加配伍禁忌
		@Log(operationType = "添加操作", operationName = "添加配伍禁忌")
		@RequestMapping(value="/addCompTaboo.action")
		public String addCompTaboo(TabuBean tb) {
			
			return iDrugAllocatBiz.addCompTaboo(tb);
		}
		
		//修改配伍禁忌
		@Log(operationType = "修改操作", operationName = "修改配伍禁忌")
		@RequestMapping(value="/updCompTaboo.action")
		public String updCompTaboo(TabuBean tb) {
			
			return iDrugAllocatBiz.updCompTaboo(tb);
		}
		
		//删除配伍禁忌
		@Log(operationType = "删除操作", operationName = "删除配伍禁忌")
		@RequestMapping(value="/delCompTaboo.action")
		public String delCompTaboo(TabuBean tb) {
			
			return iDrugAllocatBiz.delCompTaboo(tb);
		}
	
		/*******************************************************************
		 * @param 药品库存管理
		 * @author: 程帅 
		 */
		
		//查询药品库存
		
		@RequestMapping(value="/findDrugInventory.action")
		public ModelAndView findDrugInventory(CondiBean condi) {
			
			return iDrugAllocatBiz.findDrugInventory(condi);
			
		}
		//修改库存限制
		@Log(operationType = "修改操作", operationName = "修改库存限制")
		@RequestMapping(value="updDrugInventory.action")
		public String updDrugInventory(InventoryBean inventory) {
			
			return iDrugAllocatBiz.updDrugInventory(inventory);
			
		}
	
		/*******************************************************************
		 * @param 药房库存管理
		 * @author: 程帅 
		 */
		//查询药房库存信息
		@RequestMapping(value="/findPharmacyStock.action")
		public ModelAndView findPharmacyStock(CondiBean condi) {
			
			return iDrugAllocatBiz.findPharmacyStock(condi);
		}
		//修改药房库存限制
		@Log(operationType = "修改操作", operationName = "修改药房库存限制")
		@RequestMapping(value="/updPharmacyStock.action")
		public String updPharmacyStock(PhaDrugBean phaDrug) {
			
			return iDrugAllocatBiz.updPharmacyStock(phaDrug);
			
		}
		
		/*******************************************************************
		 * @param 药品调价统计
		 * @author: 程帅 
		 */
		//进入药品调价页面
		@RequestMapping(value="/findAdjustPrice.action")
		public ModelAndView toAdjustPrice(CondiBean condiBean) {
			
			return iDrugAllocatBiz.findAdjustPrice(condiBean);
			
		}
		
		
		
		//进入药品调价统计页面
		@RequestMapping(value="/toPriceStatistics.action")
		public ModelAndView getDrugId(HttpServletRequest req,AdjustPriceBean adjustPrice) {
			
			ModelAndView mav =new ModelAndView("storage/priceStatistics");
			mav.addObject("drugId", adjustPrice.getDrugId());
			return mav;
		}
		
		//查看药品调价统计
		@RequestMapping(value="/showPriceStatistics.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  AdjPriceStatisBean  showPriceStatistics(AdjustPriceBean adjustPrice){
			
			return iDrugAllocatBiz.priceData(adjustPrice);
			
		}
		
		
}
