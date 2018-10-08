package org.great.biz;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.great.bean.AdjustPriceBean;
import org.great.bean.AdminBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CheckBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.OutAndInBean;
import org.great.bean.ParamBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
import org.great.bean.TakeStockBean;
import org.great.bean.WarnBean;
import org.great.mapper.DailyWorkMapper;
import org.great.mapper.DrugAllocatMapper;
import org.great.tools.DateUtil;
import org.great.tools.PageUtil;
import org.great.tools.SpecificUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @ClassName: DailyWorkBizImpl
 * @Description: 日常工作biz实现类
 * @author: JX180327_陈文炽(wc)
 * @date: 2018年9月18日 下午2:58:48
 */
@Service
public class DailyWorkBizImpl implements IDailyWorkBiz {
	@Resource
	private DailyWorkMapper dailyWorkMapper;// 映射器（日常工作）
	@Resource
	private OutAndInBean outAndInBean;// 出入库对象
	@Resource
	private PhaDrugBean phaDrugBean;// 药房信息对象
	@Resource
	private BatchDetailBean batchDetailBean;// 药品批次详情对象
	@Resource
	private SellBean sellBean;// 药品销售记录对象
	@Resource
	private CheckBean checkBean;//药品盘对对象
	@Resource
	private TakeStockBean takeStockBean;//盘点盈亏对象
	@Resource
	private DrugAllocatMapper drugAllocatMapper;//映射器（药品信息）

	// 报损审核
	@Override
	public String breakCheck(HttpServletRequest req,DrugApplyBean drugApplyBean) {
		int i = dailyWorkMapper.breakCheck(drugApplyBean);
		 AdminBean admin=(AdminBean) req.getSession().getAttribute("User");//获取管理员信息
		// 如果审核通过，增加药品出库记录
		if (drugApplyBean.getCheckId() == 6) {
			batchDetailBean.setDrugId(drugApplyBean.getDrugId());
			batchDetailBean.setHandleNum(drugApplyBean.getApplyNum());
			batchDetailBean.setManuBatch(drugApplyBean.getManuBatch());
			dailyWorkMapper.insertBatchDetail(batchDetailBean);// 插入药品出库批次详情记录
			outAndInBean.setAdminId(admin.getAdminId());// 模拟管理员数据
			outAndInBean.setDestination("焚烧场");
			outAndInBean.setTotalMoney(drugApplyBean.getApplyMoney());
			outAndInBean.setPutBatch("" + batchDetailBean.getBatchDetailId());
			dailyWorkMapper.insertDrugOut(outAndInBean);// 插入药房出库记录
		}
		/* 如果审核不通过，则将药房药品数量加回 */
		else {

			/* 增加药房药品库存 */
			drugApplyBean.setApplyNum(-drugApplyBean.getApplyNum());// 将报损数量单位设为最小规格单位
			dailyWorkMapper.reducePhaDrugNum2(drugApplyBean);// 减少库存

			/* 增加药房入库批次详情中的药品数量 */
			sellBean.setPutBatch("" + drugApplyBean.getBdBean().getBatchDetailId());
			sellBean.setSellNum(drugApplyBean.getApplyNum());
			dailyWorkMapper.reducePhaPutDrugNum(sellBean);

		}
		return "redirect:toBreakCheck.action";
	}

	// 报损申请
	@Override
	public String breakApply(HttpServletRequest req,DrugApplyBean drugApplyBean) {
		HttpSession session=req.getSession();
		 AdminBean admin=(AdminBean) session.getAttribute("User");//获取管理员信息
		List<DrugApplyBean> list = drugApplyBean.getApplyList();
		for (int i = 0; i < list.size(); i++) {
			DrugApplyBean da = list.get(i);
			da.setAdminId(admin.getAdminId());// 操作人员id

			/* 预先扣除药房药品库存 */
//			int n=SpecificUtil.getSpecificNum(da.getDrugId());
//			da.setApplyNum(da.getApplyNum() * n);// 将报损数量单位设为最小规格单位
			dailyWorkMapper.reducePhaDrugNum2(da);// 减少库存

			/* 预先扣除药房入库批次详情中的药品数量 */
			sellBean.setPutBatch("" + da.getBdBean().getBatchDetailId());
			sellBean.setSellNum(da.getApplyNum());
			dailyWorkMapper.reducePhaPutDrugNum(sellBean);

			/* 增加申请记录 */
			da.setPutBatch("" + da.getBdBean().getBatchDetailId());
			dailyWorkMapper.breakApply(da);
			phaMinimunWarn(session, da.getDrugId());// 是否低于最低限制
		}
		return "pharmacy/breakApply";
	}

	// 申请记录查询
	@Override
	public ModelAndView selectDrugApply(CondiBean condiBean) {
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		int count = dailyWorkMapper.getDrugApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/breakApplyList");
		mav.addObject("checkId", condiBean.getCheckId());
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	// 查询药品入库批次详情列表
	@Override
	public ModelAndView selectPutDrug(CondiBean condiBean) {
		// TODO Auto-generated method stub
		List<BatchDetailBean> list = dailyWorkMapper.selectCanSellDrug(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/putDrugList");
		mav.addObject("drugList", list);
		return mav;
	}

	// 获取报损未审核列表
	@Override
	public ModelAndView selectBreakCheck(CondiBean condiBean) {
		condiBean.setCheckId(7);
		condiBean.setApplyTypeId(12);
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		int count = dailyWorkMapper.getDrugApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/breakCheck");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("count", count);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	// 进入药品调价界面显示药品信息
	@Override
	public ModelAndView toAdjustPrice(CondiBean condiBean) {
		List<StoDrugBean> list = dailyWorkMapper.findDrugInfo(condiBean);
		List<DrugTypeBean> typeList=drugAllocatMapper.findAllSecondType();
		int count = dailyWorkMapper.findDrugCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/adjustPrice");
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("stoDrugList", list);
		mav.addObject("drugTypeList", typeList);
		mav.addObject("count", count);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condiBean);
		return mav;
	}

	// 查询调价记录
	@Override
	public ModelAndView selectAdjustPrice(CondiBean condiBean) {
		List<AdjustPriceBean> list = dailyWorkMapper.selectAdjustPrice(condiBean);
		List<DrugTypeBean> typeList=drugAllocatMapper.findAllSecondType();
		int count = dailyWorkMapper.getAdjustCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/priceAdjustment");
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("adjustList", list);
		mav.addObject("drugTypeList", typeList);
		mav.addObject("count", count);
		mav.addObject("pageTotal", pageTotal);
		return mav;
	}
	

	// 药品调价弹窗
	@Override
	public ModelAndView adjustLayer(double beforeAdjust, int drugId) {
		ModelAndView mav = new ModelAndView("pharmacy/adjustLayer");
		mav.addObject("beforeAdjust", beforeAdjust);
		mav.addObject("drugId", drugId);
		ParamBean param = dailyWorkMapper.getParam(15);
		double markup = Double.parseDouble(param.getParam());
		double maximum = beforeAdjust * markup + beforeAdjust;// 最高零售价
		mav.addObject("maximum", maximum);
		return mav;

	}

	// 调整药品价格（修改药品信息表，插入药品调价记录表）
	@Override
	public String adjustPrice(AdjustPriceBean adjustPriceBean) {
		dailyWorkMapper.adjustPrice(adjustPriceBean);
		dailyWorkMapper.insertAdjustPrice(adjustPriceBean);
		return "redirect:toAdjustPrice.action";
	}

	// 卖药
	@Override
	public String sellDrug(HttpServletRequest req,StoDrugBean stoDrugBean) {
		List<StoDrugBean> list = stoDrugBean.getDrugList();
		HttpSession session=req.getSession();
		AdminBean admin=(AdminBean) session.getAttribute("User");
		for (int i = 0; i < list.size(); i++) {
			SellBean sellBean = list.get(i).getSellBean();
			sellBean.setAdminId(admin.getAdminId());
			dailyWorkMapper.insertSell(sellBean);// 插入销售记录表
			dailyWorkMapper.reducePhaDrugNum(sellBean);// 减少库存
			dailyWorkMapper.reducePhaPutDrugNum(sellBean);// 减少药房入库批次详情药品数量
			batchDetailBean.setDrugId(sellBean.getDrugId());
			batchDetailBean.setHandleNum(sellBean.getSellNum());
			batchDetailBean.setManuBatch(sellBean.getManuBatch());
			dailyWorkMapper.insertBatchDetail(batchDetailBean);// 插入药品出库批次详情记录
			outAndInBean.setAdminId(admin.getAdminId());// 模拟管理员数据
			outAndInBean.setDestination("顾客");
			outAndInBean.setTotalMoney(sellBean.getSales());
			outAndInBean.setPutBatch("" + batchDetailBean.getBatchDetailId());
			dailyWorkMapper.insertDrugOut(outAndInBean);// 插入药房出库记录
			phaMinimunWarn(session, sellBean.getDrugId());// 是否低于最低限制
		}
		return "redirect:toSellDrug.action";
	}

	// 卖药弹窗
	@Override
	public ModelAndView sellDrugLayer(CondiBean condiBean) {
		// List<PhaDrugBean> list=dailyWorkMapper.selectPhaDrug(condiBean);
		List<BatchDetailBean> list = dailyWorkMapper.selectCanSellDrug(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/sellDrugLayer");
		mav.addObject("drugList", list);
		mav.addObject("condiBean", condiBean);
		return mav;
	}

	/*
	 * 进入药品停用功能
	 * @see org.great.biz.IDailyWorkBiz#toForbidDrug(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView toForbidDrug(CondiBean condiBean) {
		List<PhaDrugBean> list = dailyWorkMapper.selectPhaDrug(condiBean);
		List<DrugTypeBean> typeList=drugAllocatMapper.findAllSecondType();
		int count = dailyWorkMapper.getPhaDrugCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/toForbidDrug");
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("drugList", list);
		mav.addObject("count", count);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condi", condiBean);
		mav.addObject("drugTypeList", typeList);
		mav.addObject("condi", condiBean);
		return mav;
	}

	/*
	 *停用药品
	 * @see org.great.biz.IDailyWorkBiz#forbidDrug(org.great.bean.StoDrugBean)
	 */
	@Override
	public String forbidDrug(StoDrugBean stoDrugBean) {
		dailyWorkMapper.forbidDrug(phaDrugBean);
		return "redirect:toForbidDrug";
	}	
	/*
	 * 药品利润计算
	 * @see org.great.biz.IDailyWorkBiz#checkTask()
	 */
	@Override
	public void checkProfit() {
		// TODO Auto-generated method stub
		CondiBean condiBean=new CondiBean();
		condiBean.setAfterDate(DateUtil.getBeginDayOfLastMonth());
		condiBean.setBeforeDate(DateUtil.getBeginDayOfMonth());
		List<SellBean> list=dailyWorkMapper.selectSellDrug(condiBean);
		for (int i = 0; i < list.size(); i++) {
			SellBean sellBean=list.get(i);
			condiBean.setDrugId(sellBean.getDrugId());
			int drugNum=dailyWorkMapper.getBreakDrugNum(condiBean);//获取报损药品数量
			double breakMoney=drugNum*sellBean.getBatchDetailBean().getPurPrice();
			double checkMoney=sellBean.getSales()-breakMoney;
			checkBean.setCheckMoney(checkMoney);//计算盈亏
			checkBean.setDrugId(sellBean.getDrugId());
			if(checkMoney>0) {
				checkBean.setProfitId(33);//设置为盈利
			}else if(checkMoney<0) {
				checkBean.setProfitId(34);//设置为亏损
			}else {
			checkBean.setProfitId(35);//设置为正常
			}
			dailyWorkMapper.insertCheckProfit(checkBean);//插入盘点记录
		}		
	}
	
	/**
	 * 月初自动盘点上个月药房药品盈亏
	 */
	@Override
	public void checkStock() {
		// TODO Auto-generated method stub
		CondiBean condiBean =new CondiBean();
		condiBean.setPageNum(0);
		condiBean.setAfterDate(DateUtil.getBeginDayOfLastMonth());
		condiBean.setBeforeDate(DateUtil.getBeginDayOfMonth());
		List<PhaDrugBean> list=dailyWorkMapper.selectPhaDrug(condiBean);//获取药房药品库存信息
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			PhaDrugBean phaDrugBean = (PhaDrugBean) iterator.next();
			takeStockBean.setDrugId(phaDrugBean.getStoDrugBean().getDrugId());
			takeStockBean.setDrugNum(phaDrugBean.getDrugNum());
			condiBean.setOutInId(17);
			condiBean.setDrugId(phaDrugBean.getStoDrugBean().getDrugId());
			Integer outStorage=dailyWorkMapper.getDrugCurrentNum(condiBean);//获取药房药品出库数量
			if(outStorage==null) {
				outStorage=0;
			}
			condiBean.setOutInId(18);
			Integer putStorage=dailyWorkMapper.getDrugCurrentNum(condiBean);//获取药房药品入库数量
			if(putStorage==null) {
				putStorage=0;
			}
			takeStockBean.setOutStorage(outStorage);//设置出库数量
			takeStockBean.setPutStorage(putStorage);//设置入库数量
			dailyWorkMapper.insertCheckStock(takeStockBean);//插入自动盘点记录
			
		}
	}
	
	/*
	 * 管理员录入药房药品实际库存
	 */
	@Override
	public String checkStockData(TakeStockBean takeStockBean) {
		List<TakeStockBean> list=takeStockBean.getStockList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			TakeStockBean takeStock = (TakeStockBean) iterator.next();
			int drugNum=takeStock.getDrugNum();//药房库存
			int realNum=takeStock.getRealNum();//盘点库存
			if(drugNum>realNum) {
				takeStock.setParamId(34);
			}else if(drugNum<realNum) {
				takeStock.setParamId(33);
			}else {
				takeStock.setParamId(35);
			}
			dailyWorkMapper.checkStockData(takeStock);
		}
		return "redirect:toCheckData.action";
	}
	
	/**
	 * 进入盘点界面
	 */
	@Override
	public ModelAndView toCheckData(CondiBean condiBean) {
		condiBean.setPageNum(0);
		List<TakeStockBean> list=dailyWorkMapper.selectTakeStockList(condiBean);
		List<DrugTypeBean> typeList=drugAllocatMapper.findAllSecondType();
		ModelAndView mav=new ModelAndView("pharmacy/checkStockData");
		mav.addObject("stockList", list);
		mav.addObject("drugTypeList", typeList);
		mav.addObject("condiBean", condiBean);
		return mav;
	}
	
	/*查询上月药品盘点结果*/
	@Override
	public ModelAndView showTakeStock(CondiBean condiBean) {
		List<TakeStockBean> list=dailyWorkMapper.selectTakeStockList(condiBean);
		int count=dailyWorkMapper.getTakeStockCount(condiBean);
		int pageTotal=PageUtil.pageTotal(count);
		ModelAndView mav =new ModelAndView("pharmacy/showTakeStock");
		mav.addObject("stockList", list);
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("condiBean", condiBean);
		return mav;
	}
	
	//查询药房药品信息
	@Override
	public ModelAndView selectPhaDrug(CondiBean condiBean) {
		List<PhaDrugBean> list=dailyWorkMapper.selectPhaDrug(condiBean);
		List<DrugTypeBean> typeList=drugAllocatMapper.findAllSecondType();
		int count=dailyWorkMapper.getPhaDrugCount(condiBean);
		int pageTotal=PageUtil.pageTotal(count);
		ModelAndView mav=new ModelAndView("pharmacy/phaDrugList");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("drugTypeList", typeList);
		mav.addObject("drugList", list);
		return mav;
	}
	
	
	/*以下为蓝鹏完成功能*/
	
	@Override
	// 请领申请(lp)
	public ModelAndView takeDrugAppleFor(DrugApplyBean drugApplyBean) {

		for (int i = 0; i < drugApplyBean.getApplyList().size(); i++) {
			drugApplyBean.setAdminId(drugApplyBean.getApplyList().get(i).getAdminId());
			drugApplyBean.setDrugId(drugApplyBean.getApplyList().get(i).getDrugId());
			drugApplyBean.setApplyNum(drugApplyBean.getApplyList().get(i).getApplyNum());
			drugApplyBean.setApplyReason(drugApplyBean.getApplyList().get(i).getApplyReason());
			dailyWorkMapper.takeDrugAppleFor(drugApplyBean);
		}

		ModelAndView mav = new ModelAndView("pharmacy/takeDrugApply");
		return mav;

	}

	// 审核退库查询(lp)
	@Override
	public ModelAndView selectCancellingApply(CondiBean condiBean) {
		condiBean.setApplyTypeId(11);
		condiBean.setCheckId(7);
		int count = dailyWorkMapper.selectCancellingApplyCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/cancellingApplyList");
		mav.addObject("drugApplyList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}

	// 查找药品(lp)
	@Override
	public ModelAndView selectDrug(StoDrugBean stoDrugBean) {
		List<StoDrugBean> drugList = dailyWorkMapper.selectDrug(stoDrugBean);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/chooseDrug");
		mav.addObject("drugList", drugList);
		return mav;
	}

	// 查找要退库的药
	@Override
	public ModelAndView chooseCaceDrug(StoDrugBean stoDrugBean) {

		List<BatchDetailBean> drugList = dailyWorkMapper.chooseCaceDrug(stoDrugBean);


		List<BatchDetailBean> drugList = dailyWorkMapper.chooseCaceDrug(stoDrugBean);

		System.out.println("查找出来有几条" + drugList.size());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/chooseCaceDrug");
		mav.addObject("drugList", drugList);
		return mav;
	}

	@Override
	// 提交退库申请
	public ModelAndView cacellingApply(HttpServletRequest request, DrugApplyBean drugApplyBean) {
		HttpSession session = request.getSession();
		for (int i = 0; i < drugApplyBean.getApplyList().size(); i++) {
			drugApplyBean.setAdminId(drugApplyBean.getApplyList().get(i).getAdminId());
			drugApplyBean.setDrugId(drugApplyBean.getApplyList().get(i).getDrugId());
			drugApplyBean.setApplyNum(drugApplyBean.getApplyList().get(i).getApplyNum());
			System.out.println("传过来的数量" + drugApplyBean.getApplyList().get(i).getApplyNum());
			drugApplyBean.setApplyReason(drugApplyBean.getApplyList().get(i).getApplyReason());
			drugApplyBean.setManuBatch(drugApplyBean.getApplyList().get(i).getManuBatch());
			// 插入退库申请
			dailyWorkMapper.cacellingApply(drugApplyBean);
			BatchDetailBean batchDetailBean = new BatchDetailBean();

			String specific = dailyWorkMapper.selSpecific(drugApplyBean.getApplyList().get(i).getDrugId());
			String[] str = specific.split("-");

			batchDetailBean.setManuBatch(drugApplyBean.getApplyList().get(i).getManuBatch());
			batchDetailBean.setDrugId(drugApplyBean.getApplyList().get(i).getDrugId());
			BatchDetailBean bdBean = dailyWorkMapper.selectPhaInfo(batchDetailBean);
			System.out.println("查找出来的信息" + bdBean);
			bdBean.setHandleNum(drugApplyBean.getApplyNum() * Integer.valueOf(str[0]));
			dailyWorkMapper.updatePhaBatchNum(bdBean);// 修改相对应药房入库批次数量
			dailyWorkMapper.updatePhaDrugNum(
					drugApplyBean.getApplyList().get(i).getApplyNum() * Integer.valueOf(str[0]), bdBean.getDrugId());// 修改药房库存数量
			phaMinimunWarn(session, bdBean.getDrugId());// 是否低于最低限制
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pharmacy/cacellingApply");
		return mav;

	}

	// 获取药品请领列表(lp)
	@Override
	public ModelAndView totakeApplyList(CondiBean condiBean) {
		condiBean.setApplyTypeId(10);
		condiBean.setCheckId(7);
		int count = dailyWorkMapper.selectCancellingApplyCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/takeDrugApplyList");
		mav.addObject("drugApplyList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;

	}

	// 不同意退库申请
	@Override
	public ModelAndView chickError(HttpServletRequest request, String drugApplyId) {
		HttpSession session = request.getSession();
		AdminBean admin = (AdminBean) session.getAttribute("User");
		dailyWorkMapper.chickError(drugApplyId, admin.getAdminId() + "");
		DrugApplyBean drugApplyBean = dailyWorkMapper.selApply(drugApplyId);

		BatchDetailBean batchDetailBean = new BatchDetailBean();
		batchDetailBean.setManuBatch(drugApplyBean.getManuBatch());
		batchDetailBean.setDrugId(drugApplyBean.getDrugId());

		String specific = dailyWorkMapper.selSpecific(drugApplyBean.getDrugId());
		String[] str = specific.split("-");

		BatchDetailBean bdBean = dailyWorkMapper.selectPhaInfo(batchDetailBean);
		bdBean.setHandleNum(-drugApplyBean.getApplyNum() * Integer.valueOf(str[0]));
		dailyWorkMapper.updatePhaBatchNum(bdBean);// 增加相对应药房入库批次数量
		dailyWorkMapper.updateStoDrugNum(-bdBean.getHandleNum(), bdBean.getDrugId());// 增加药房库存数量
		return selectCancellingApply(new CondiBean());
	};

	// 不同意请领申请
	@Override
	public ModelAndView takeError(HttpServletRequest request, String drugApplyId) {
		HttpSession session = request.getSession();
		AdminBean admin = (AdminBean) session.getAttribute("User");
		dailyWorkMapper.chickError(drugApplyId, admin.getAdminId() + "");
		return totakeApplyList(new CondiBean());
	};

	// 所有退库记录查询(lp)
	@Override
	public ModelAndView toAllCancApply(CondiBean condiBean) {
		condiBean.setApplyTypeId(11);
		int count = dailyWorkMapper.selectCancellingApplyCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/allCaceApply");
		mav.addObject("drugApplyList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}

	// 获取所有药品请领列表(lp)
	@Override
	public ModelAndView toAllTakeApply(CondiBean condiBean) {
		condiBean.setApplyTypeId(10);
		int count = dailyWorkMapper.selectCancellingApplyCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/allTakeApply");
		mav.addObject("drugApplyList", list);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;

	}

	// 同意退库申请
	@Override
	public ModelAndView chickSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean) {
		HttpSession session = request.getSession();
		AdminBean admin = (AdminBean) session.getAttribute("User");
		dailyWorkMapper.updayeCaceApply(drugApplyId, admin.getAdminId() + "");// 修改当前审核转态以及添加时间
		System.out.println(1);
		BatchDetailBean bdBean = dailyWorkMapper.selectInfo(batchDetailBean);// 通过药品名,产品批次找到批次详情
		System.out.println(2);
		bdBean.setHandleNum(batchDetailBean.getHandleNum());
		bdBean.setTotalMoney(batchDetailBean.getHandleNum() * bdBean.getPurPrice());
		dailyWorkMapper.updatePhaDrugNum(-batchDetailBean.getHandleNum(), bdBean.getDrugId());// 修改药库库存
		System.out.println(3);
		dailyWorkMapper.updatePutBatchNum(bdBean.getBatchDetailId(), batchDetailBean.getHandleNum(),
				bdBean.getDrugId());// 修改药库当前批次数量
		System.out.println(4);
		dailyWorkMapper.insetStoIn(bdBean.getPutBatch(), admin.getAdminId() + "");// 插入药库入库记录
		System.out.println(5);
		String specific = dailyWorkMapper.selSpecific(batchDetailBean.getDrugId());
		String[] str = specific.split("-");
		System.out.println(6);

		bdBean.setHandleNum(bdBean.getHandleNum() * Integer.valueOf(str[0]));
		dailyWorkMapper.insetPhaBatch(bdBean);// 插入药房出库详情

		System.out.println(7);
		dailyWorkMapper.insetPhaOut(admin.getAdminId());// 插入药房出库记录
		System.out.println(8);

		System.out.println(9);
		return selectCancellingApply(new CondiBean());

	}

	// 同意请领申请
	@Override
	public ModelAndView takeSuccess(HttpServletRequest request, String drugApplyId, BatchDetailBean batchDetailBean) {
		HttpSession session = request.getSession();
		AdminBean admin = (AdminBean) session.getAttribute("User");
		int number = batchDetailBean.getHandleNum();
		String specific = dailyWorkMapper.selSpecific(batchDetailBean.getDrugId());
		String[] str = specific.split("-");
		// 找出当前药品编号的所有入库批次详情
		List<BatchDetailBean> bdLsit = dailyWorkMapper.selectStoIn(batchDetailBean.getDrugId());
		System.out.println(1);
		String manuBatch = "";
		for (int i = 0; i < bdLsit.size(); i++) {

			if (i < bdLsit.size() - 1) {
				manuBatch = bdLsit.get(i).getManuBatch() + "," + manuBatch;
			}
			if (i == bdLsit.size() - 1) {
				manuBatch = manuBatch + bdLsit.get(i).getManuBatch();
			}
			if (number > 0) {
				if (bdLsit.get(i).getHandleNum() >= number) {
					bdLsit.get(i).setTotalMoney((bdLsit.get(i).getHandleNum() - number) * bdLsit.get(i).getPurPrice());
					bdLsit.get(i).setHandleNum(number);
					number = 0;
				} else {
					bdLsit.get(i).setHandleNum(bdLsit.get(i).getHandleNum());
					bdLsit.get(i).setTotalMoney(0);
					number = number - bdLsit.get(i).getHandleNum();
				}
				// 修改药库当前批次数量
				dailyWorkMapper.updatePhaBatchNum(bdLsit.get(i));
				System.out.println(2);
				// 根据药品id和产品批次找批次详情记录
				List<BatchDetailBean> bdBean = dailyWorkMapper.selBatchBean(bdLsit.get(i).getDrugId() + "",
						bdLsit.get(i).getManuBatch());
				if (bdBean.size() != 0) {
					// 去修改详情数量
					bdLsit.get(i).setHandleNum(-bdLsit.get(i).getHandleNum() * Integer.valueOf(str[0]));
					dailyWorkMapper.updatePhaBatchNum(bdLsit.get(i));// 增加相对应药房入库批次数量
					dailyWorkMapper.insetPhaInt(bdLsit.get(i).getBatchDetailId(), admin.getAdminId());// 插入药房入库库记录
				} else {
					bdLsit.get(i).setHandleNum(bdLsit.get(i).getHandleNum() * Integer.valueOf(str[0]));
					dailyWorkMapper.insetPhaBatch(bdLsit.get(i));// 插入药房入库详情

					System.out.println(3);
					dailyWorkMapper.insetPhaInt(bdLsit.get(i).getBatchDetailId(), admin.getAdminId());// 插入药房入库库记录
				}
				System.out.println(4);
				// 插入药库出库记录
				dailyWorkMapper.insetStoOut(bdLsit.get(i).getPutBatch(), admin.getAdminId() + "");
				System.out.println(5);
			}
		}
		// 修改当前申请状态
		dailyWorkMapper.updateTakeApply(drugApplyId, admin.getAdminId() + "", manuBatch);
		System.out.println(6);
		// 减少药库库存量
		dailyWorkMapper.reduceStoDrugNum(batchDetailBean.getHandleNum(), batchDetailBean.getDrugId());
		stoMinimunWarn(session, batchDetailBean.getDrugId());
		System.out.println(7);
		// 增加药房库存量
		dailyWorkMapper.addPhaDrugNum(batchDetailBean.getHandleNum() * Integer.valueOf(str[0]),
				batchDetailBean.getDrugId());
		System.out.println(8);
		return totakeApplyList(new CondiBean());
	}

	// 药房药品底限预警
	public void phaMinimunWarn(HttpSession session, int drugId) {
		PhaDrugBean phaDrugBean = dailyWorkMapper.selPhaMinimum(drugId);
		if (phaDrugBean.getDrugNum() <= phaDrugBean.getMinimum()) {
			String str = phaDrugBean.getStoDrugBean().getDrugName() + "以低于限制数量,请尽快处理";
			dailyWorkMapper.insertPhaMinWarn(str);
		}

	}

	// 药库药品底限预警
	public void stoMinimunWarn(HttpSession session, int drugId) {
		StoDrugBean stoDrugBean = dailyWorkMapper.selStoMinimum(drugId);
		if (stoDrugBean.getInventoryBean().getInventoryNum() <= stoDrugBean.getInventoryBean().getMinimum()) {
			String str = stoDrugBean.getDrugName() + "以低于限制数量,请尽快处理";
			dailyWorkMapper.insertStoMinWarn(str, "26", "22");
		}

	}

	// 到预警界面
	public ModelAndView toWarn(HttpServletRequest request) {
		HttpSession session = request.getSession();

		int belongId = Integer.valueOf(session.getAttribute("belongId").toString());
		List<WarnBean> warnList = dailyWorkMapper.selWarn(belongId);
		session.setAttribute("warnCount", warnList.size());// 页面提醒数量
		ModelAndView mav = new ModelAndView("pharmacy/warn");
		mav.addObject("warnList", warnList);
		return mav;
	}

	// 过期预警
		@Override
		public void expirationWarn() {

			List<StoDrugBean> stoBeanList = dailyWorkMapper.selAllStoDrug();
			for (int i = 0; i < stoBeanList.size(); i++) {
				// 药库当前药品的批次详情
				List<BatchDetailBean> bdLsit = dailyWorkMapper.selectStoIn(stoBeanList.get(i).getDrugId());
				for (int j = 0; j < bdLsit.size(); j++) {
					// 如果当前批次数量大于0才执行下面的
					if (bdLsit.get(j).getHandleNum() > 0) {
						int inDate = Integer.valueOf(bdLsit.get(j).getInDate());
						// 过期时间
						String exTime = DateUtil.plusDay(inDate, bdLsit.get(j).getProDate());
						// 现在时间
						String nowTime = DateUtil.getNowDay();
						int days = DateUtil.daysBetween(nowTime, exTime);
						// 今天时间和过期时间的比较
						if (0 < days && days <= 180) {
							String str = "批次" + bdLsit.get(j).getManuBatch() + "的" + stoBeanList.get(i).getDrugName() + "将在"
									+ days + "天后过期,请尽快进行处理";
							dailyWorkMapper.insertStoMinWarn(str, "27", "22");
						}
						if (days <= 0) {
							String str = "批次" + bdLsit.get(j).getManuBatch() + "的" + stoBeanList.get(i).getDrugName()
									+ "已经过期" + -days + "天,请尽快进行处理";
							dailyWorkMapper.insertStoMinWarn(str, "27", "22");
						}
					}
				}
				// 药房药品详情
				List<BatchDetailBean> drugList = dailyWorkMapper.chooseCaceDrug(stoBeanList.get(i));
				for (int z = 0; z < drugList.size(); z++) {
					if (drugList.get(z).getHandleNum() > 0) {
						int inDate = Integer.valueOf(drugList.get(z).getInDate());
						// 过期时间
						String exTime = DateUtil.plusDay(inDate, drugList.get(z).getProDate());
						// 现在时间
						String nowTime = DateUtil.getNowDay();
						int days = DateUtil.daysBetween(nowTime, exTime);
						// 今天时间和过期时间的比较
						if (0 < days && days <= 180) {
							String str = "批次" + drugList.get(z).getManuBatch() + "的" + stoBeanList.get(i).getDrugName()
									+ "将在" + days + "天后过期,请尽快进行处理";
							dailyWorkMapper.insertStoMinWarn(str, "27", "21");
						}
						if (days <= 0) {
							String str = "批次" + drugList.get(z).getManuBatch() + "的" + stoBeanList.get(i).getDrugName()
									+ "已经过期" + -days + "天,请尽快进行处理";
							dailyWorkMapper.insertStoMinWarn(str, "27", "21");
						}
					}
				}
				// 结束过期预警,开始滞销预警
			}
			// 现在时间
			String nowTime = DateUtil.getNowDay();
			// 90天以前时间
			String exTime = DateUtil.plusDay(-90, nowTime);
			
			List<StoDrugBean> stoList = dailyWorkMapper.unsalableDrug(exTime);
			for (int k = 0; k < stoList.size(); k++) {
				String str = "药品编号为" + stoList.get(k).getDrugId() + "的" + stoList.get(k).getDrugName()
						+ "已经超过90天没有消售了,请尽快处理";
				dailyWorkMapper.insertStoMinWarn(str, "28", "21");
			}

		}


	// 低限预警界面
	@Override
	public ModelAndView toMinimumWarn(HttpServletRequest request, CondiBean condiBean) {
		HttpSession session = request.getSession();
		int belongId = Integer.valueOf(session.getAttribute("belongId").toString());
		condiBean.setBelongId(belongId);
		condiBean.setEventId(26);
		int count = dailyWorkMapper.selAllWarnCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		
		List<WarnBean> warnList = dailyWorkMapper.selAllWarn(condiBean);
		session.setAttribute("warnCount", warnList.size());// 页面提醒数量
		ModelAndView mav = new ModelAndView("pharmacy/minimumWarn");
		mav.addObject("warnList", warnList);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}

	// 滞销预警界面
	@Override
	public ModelAndView toUnsalableWarn(HttpServletRequest request, CondiBean condiBean) {
		HttpSession session = request.getSession();
		int belongId = Integer.valueOf(session.getAttribute("belongId").toString());
		condiBean.setBelongId(belongId);
		condiBean.setEventId(28);
		int count = dailyWorkMapper.selAllWarnCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		
		List<WarnBean> warnList = dailyWorkMapper.selAllWarn(condiBean);
		session.setAttribute("warnCount", warnList.size());// 页面提醒数量
		ModelAndView mav = new ModelAndView("pharmacy/unsalableWarn");
		mav.addObject("warnList", warnList);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}

	// 过期预警界面
	@Override
	public ModelAndView toOverdueWarn(HttpServletRequest request, CondiBean condiBean) {
		HttpSession session = request.getSession();
		int belongId = Integer.valueOf(session.getAttribute("belongId").toString());
		condiBean.setBelongId(belongId);
		condiBean.setEventId(27);
		int count = dailyWorkMapper.selAllWarnCount(condiBean);
		if (condiBean.getPageNum() > PageUtil.pageTotal(count)) {
			condiBean.setPageNum(PageUtil.pageTotal(count));
		}
		if (condiBean.getPageNum() < 1) {
			condiBean.setPageNum(1);
		}
		
		List<WarnBean> warnList = dailyWorkMapper.selAllWarn(condiBean);
		session.setAttribute("warnCount", warnList.size());// 页面提醒数量
		ModelAndView mav = new ModelAndView("pharmacy/overdueWarn");
		mav.addObject("warnList", warnList);
		mav.addObject("condiBean", condiBean);
		mav.addObject("count", PageUtil.pageTotal(count));
		return mav;
	}

	// 确认警告已处理
	@Override
	public ModelAndView affirmWarn(HttpServletRequest request, int warnId) {
		dailyWorkMapper.affirmWarn(warnId);
		return toWarn(request);

	}

}
