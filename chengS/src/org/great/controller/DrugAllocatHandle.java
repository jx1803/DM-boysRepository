package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.bean.DrugTypeBean;
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
	
	@Resource
	private DrugAllocatMapper damapper;
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
		@RequestMapping(value="/toMedicineDictionary.action")
		public ModelAndView toInfo(HttpServletRequest req) {
			System.out.println("进入药品主页........");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/findMedicineDictionary");
			return mav;
		}
		//添加药品信息
		@RequestMapping(value="/addMedicineDictionary.action")
		public ModelAndView addMedicineDictionary(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/addMedicineDictionary");
			return mav;
		}
		//修改药品信息
		@RequestMapping(value="/updMedicineDictionary.action")
		public ModelAndView updMedicineDictionary(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/updMedicineDictionary");
			return mav;
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
		//获取药品大类型
		@RequestMapping(value="/findTopType.action", method=RequestMethod.POST, produces="application/json;charset=utf-8")
		@ResponseBody
		public  List<DrugTypeBean> findTopType(DrugTypeBean dt){
			
			drugTypeList = damapper.findTopType(dt);
			
			return drugTypeList;
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
		
	
//	//查询药品信息
//	@RequestMapping(value="/findDrugInfo.action")
//	public ModelAndView findDrugInfo(HttpServletRequest req) {
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("DrugInfo");
//		return mav;
//	}
//	//查看药品剂型
//	@RequestMapping(value="/findDf.action")
//	public ModelAndView findDf(HttpServletRequest req) {
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("DfInfo");
//		return mav;
//	}
//	
//	//添加药品剂型
//	@RequestMapping(value="/addDf.action")
//	public String addDf(HttpServletRequest req,DfBean df) {
//	
//		damapper.addDf(df);
//		return "redirect:/storage/findDf.action";
//	}
//	
//	//更改药品剂型
//	@RequestMapping(value="/addDf.action")
//	public String updDf(HttpServletRequest req,DfBean df) {
//		damapper.delDf(df);
//		return "redirect:/storage/findDf.action";
//	}
//	
//	//删除药品剂型
//	public String delDf(HttpServletRequest req,DfBean df) {
//		damapper.updDf(df);
//		return "redirect:/storage/findDf.action";
//	}
//	
//	//添加药品分类
//	@RequestMapping(value="/addDrugTypeInfo.action")
//	public ModelAndView addDrugInfo(HttpRequest req) {
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("DrugInfo");
//		return mav;
//	}
	
}
