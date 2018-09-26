package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.InventoryBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TabuBean;
import org.great.biz.IDrugAllocatBiz;
import org.great.mapper.DrugAllocatMapper;
import org.great.tools.PageUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
//	@Resource
//	private IDrugAllocatBiz iDrugAllocatBiz;

	private int pageTotal;	//总页数
	private List<DfBean> dfList; //药品剂量信息表
	private List<DrugTypeBean> drugTypeList; //药品分类表
	private List<StoDrugBean> stoDrugList;	//药品信息表
	private List<TabuBean> tabuList;	//配伍禁忌表
	@Resource
	private DrugAllocatMapper damapper;
	@Resource
	private InventoryBean it;	//药品入库信息
	@Resource
	private PhaDrugBean pd;		//药房药品信息
	//进入主页
	@RequestMapping(value="/toIndex.action")
	public ModelAndView toIndex(HttpServletRequest req) {
		System.out.println("进入主页........");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/storage/index");
		return mav;
	}
	//进入右边主页面
	@RequestMapping(value="/toIndexInfo.action")
	public ModelAndView toIndexInfo(HttpServletRequest req) {
			
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/storage/welcome");
		return mav;
	}	
	
	/*************************************************************
	 * @param 药品管理操作
	 * @return
	 */
		//进入药品信息列表
		@RequestMapping(value="/findDrugInfo.action")
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
		//添加药品信息
		@RequestMapping(value="/addDrugInfo.action")
		public String addMedicineDictionary(StoDrugBean sd) {
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
			
			return "redirect:/storage/findDrugInfo.action";
		}
		
		//获取所有药品剂型
		@RequestMapping(value="/findDf.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
		@ResponseBody
		public  List<DfBean> findTopType(){
			dfList = damapper.findAllDf();
			return dfList;
		}
		
		//获取所有药品类型
		@RequestMapping(value="/findSecondType.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
		@ResponseBody
		public  List<DrugTypeBean> findSecondType(DrugTypeBean dt){
			
			drugTypeList = damapper.findSecondType(dt);
			
			return drugTypeList;
		}
				
		//删除药品
		@RequestMapping(value="/delDrugInfo.action")
		public String delDrugInfo(StoDrugBean sd) {
			
			int k = damapper.delDrugInfo(sd);
			
			return "redirect:/storage/findDrugInfo.action";
		}
		
		//修改药品信息
		@RequestMapping(value="/updDrugInfo.action")
		public String updMedicineDictionary(StoDrugBean sd) {
			
			int k = damapper.updDrugInfo(sd);
			return "redirect:/storage/findDrugInfo.action";
		}
	
		/********************************************************************
		 * @param 药品剂型管理操作
		 * @return
		 */
		//进入药品剂型列表
		@RequestMapping(value="/findDfInfo.action")
		public ModelAndView toDfInfo(HttpServletRequest req,CondiBean condi) {
			
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
		
		//添加药品剂型
		@RequestMapping(value="/addDfInfo.action")
		public String addDfInfo(HttpServletRequest req,DfBean df) {
			int k = damapper.addDf(df);
			return "redirect:/storage/findDfInfo.action";
		}
		
		//修改药品剂型
		@RequestMapping(value="/updDfInfo.action")
		public String updDfInfo(HttpServletRequest req,DfBean df) {
			int k = damapper.updDf(df);
			return "redirect:/storage/findDfInfo.action";
		}
		//删除药品剂型
		@RequestMapping(value="/delDfInfo.action")
		public String delDfInfo(HttpServletRequest req,DfBean df) {
			int k = damapper.delDf(df);
			return "redirect:/storage/findDfInfo.action";
		}
		
		
		/*******************************************************************
		 * @param 药品类型操作
		 * @return
		 */
		//进入药品类型列表
		@RequestMapping(value="/findDrugTypeInfo.action")
		public ModelAndView findDrugTypeInfo(HttpServletRequest req,CondiBean condi) {
			
			condi.setPageup(condi.getPage()*5);
			condi.setPagedown((condi.getPage()*5-5)+1);
			drugTypeList = damapper.findDfDrugType(condi);
			
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
		
		//添加药品类型
		@RequestMapping(value="/addDrugTypeInfo.action")
		public String addDrugTypeInfo(HttpServletRequest req,DrugTypeBean dt) {
			
			int k = damapper.addDrugType(dt);
			return "redirect:/storage/findDrugTypeInfo.action";
		}
		//删除药品类型
		@RequestMapping(value="/delDrugTypeInfo.action")
		public String delDrugTypeInfo(HttpServletRequest req,DrugTypeBean dt) {
			int k = damapper.delDrugType(dt);
			return "redirect:/storage/findDrugTypeInfo.action";
		}
		
		//修改药品类型
		@RequestMapping(value="/updDrugTypeInfo.action")
		public String updDrugTypeInfo(DrugTypeBean dt) {
			System.out.println(dt.toString());
			int k = damapper.updDrugType(dt);
			return "redirect:/storage/findDrugTypeInfo.action";
		}
		
	/***************************配伍禁忌管理**********************************/
		//进入配伍禁忌管理
		@RequestMapping(value="/findCompTaboo.action")
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
	
			mav.setViewName("/storage/findCompTaboo");
			return mav;
		}
		
		//匹配第一个药品名
		@RequestMapping(value="/findfirstName.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  String findfirstName(TabuBean tb){
			int k = damapper.findfirstName(tb);
			if(k>0) {
				return "";
			}else {
				return "此药品不存在";
			}
		}
		
		//匹配第二个药品名
		@RequestMapping(value="/findSecondName.action", method=RequestMethod.POST, 
				produces="application/json;charset=utf-8")
		@ResponseBody
		public  String findSecondName(TabuBean tb){
			int k = damapper.findSecondName(tb);
			if(k>0) {
				return "";
			}else {
				return "此药品不存在";
			}
		}
		
		//添加配伍禁忌
		@RequestMapping(value="/addCompTaboo.action")
		public String addCompTaboo(TabuBean tb) {
			
			int k = damapper.addCompTaboo(tb);
			return "redirect:/storage/findCompTaboo.action";
		}
		
//		修改配伍禁忌
		@RequestMapping(value="/updCompTaboo.action")
		public String updCompTaboo(TabuBean tb) {
			int k = damapper.updCompTaboo(tb);
			return "redirect:/storage/findCompTaboo.action";
		}
		
//		删除配伍禁忌
		@RequestMapping(value="/delCompTaboo.action")
		public String delCompTaboo(TabuBean tb) {
			int k = damapper.delCompTaboo(tb);
			return "redirect:/storage/findCompTaboo.action";
		}
}
