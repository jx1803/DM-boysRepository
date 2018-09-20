package org.great.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.CondiBean;
import org.great.bean.DfBean;
import org.great.biz.IDrugAllocatBiz;
import org.great.mapper.DrugAllocatMapper;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	private List<DfBean> dfList;
	
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
	/**
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
			System.out.println("进入药品主页........");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/addMedicineDictionary");
			return mav;
		}
		//修改药品信息
		@RequestMapping(value="/updMedicineDictionary.action")
		public ModelAndView updMedicineDictionary(HttpServletRequest req) {
			System.out.println("进入药品主页........");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/updMedicineDictionary");
			return mav;
		}
	
		/**
		 * @param 药品剂型管理操作
		 * @return
		 */
		//进入药品剂型列表
		@RequestMapping(value="/findDfInfo.action")
		public ModelAndView toDfInfo(HttpServletRequest req,CondiBean condi) {
			
			condi.setPageup(condi.getPage()*5);
			condi.setPagedown((condi.getPage()*5-5)+1);
			dfList = damapper.findDf(condi);
			
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("dfList", dfList);
			mav.addObject("dosageForm", condi.getDosageForm());
			
			mav.setViewName("/storage/findDfInfo");
			return mav;
		}
		
		//进入添加药品剂型页面
		@RequestMapping(value="/toAddDfInfo.action")
		public ModelAndView toAddDfInfo(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/addDfInfo");
			return mav;
		}
		//添加药品剂型
		@RequestMapping(value="/addDfInfo.action")
		public String addDfInfo(HttpServletRequest req,DfBean df) {
			int k = damapper.addDf(df);
			return "redirect:/storage/toDfInfo.action";
		}
		
		//修改药品剂型
		@RequestMapping(value="/updDfInfo.action")
		public String updDfInfo(HttpServletRequest req,DfBean df) {
			int k = damapper.updDf(df);
			return "redirect:/storage/toDfInfo.action";
		}
		//删除药品剂型
		@RequestMapping(value="/delDfInfo.action")
		public String delDfInfo(HttpServletRequest req,DfBean df) {
			int k = damapper.delDf(df);
			return "redirect:/storage/toDfInfo.action";
		}
		
		
		/**
		 * @param 药品类型操作
		 * @return
		 */
		//进入药品类型列表
		@RequestMapping(value="/toDrugTypeInfo.action")
		public ModelAndView toDrugTypeInfo(HttpServletRequest req) {
		
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/findDrugTypeInfo");
			return mav;
		}
		//添加药品类型
		@RequestMapping(value="/addDrugTypeInfo.action")
		public ModelAndView addDrugTypeInfo(HttpServletRequest req) {
		
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/addDrugTypeInfo");
			return mav;
		}
		//修改药品类型
		@RequestMapping(value="/updDrugTypeInfo.action")
		public ModelAndView updDrugTypeInfo(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/updDrugTypeInfo");
			return mav;
		}
		////进入首页
		@RequestMapping(value="/toIndexInfo.action")
		public ModelAndView toIndexInfo(HttpServletRequest req) {
		
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/storage/welcome");
			return mav;
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
