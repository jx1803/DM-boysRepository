package org.great.biz;

import java.util.List;

import javax.annotation.Resource;

import org.great.bean.AdjustPriceBean;
import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.OutAndInBean;
import org.great.bean.ParamBean;
import org.great.bean.PhaDrugBean;
import org.great.bean.SellBean;
import org.great.bean.StoDrugBean;
import org.great.mapper.DailyWorkMapper;
import org.great.tools.PageUtil;
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
	private BatchDetailBean batchDetailBean;//药品批次详情对象
	@Resource
	private SellBean sellBean;//药品销售记录对象

	// 报损审核
	@Override
	public String breakCheck(DrugApplyBean drugApplyBean) {
		int i = dailyWorkMapper.breakCheck(drugApplyBean);
		// 如果审核通过，
		if (drugApplyBean.getCheckId() == 6) {
			batchDetailBean.setDrugId(drugApplyBean.getDrugId());
			batchDetailBean.setHandleNum(drugApplyBean.getApplyNum());
			batchDetailBean.setManuBatch(drugApplyBean.getManuBatch());
			dailyWorkMapper.insertBatchDetail(batchDetailBean);//插入药品出库批次详情记录
			outAndInBean.setAdminId(1001);//模拟管理员数据
			outAndInBean.setDestination("焚烧场");
			outAndInBean.setTotalMoney(drugApplyBean.getApplyMoney());
			outAndInBean.setPutBatch(""+batchDetailBean.getBatchDetailId());
			dailyWorkMapper.insertDrugOut(outAndInBean);// 插入药房出库记录
		}else {
			
			/*预先扣除药房药品库存*/
			
			drugApplyBean.setApplyNum(-drugApplyBean.getApplyNum());//将报损数量单位设为最小规格单位
			dailyWorkMapper.reducePhaDrugNum2(drugApplyBean);// 减少库存
			
			/*预先扣除药房入库批次详情中的药品数量*/
			sellBean.setPutBatch(""+drugApplyBean.getBdBean().getBatchDetailId());
			sellBean.setSellNum(drugApplyBean.getApplyNum());
			dailyWorkMapper.reducePhaPutDrugNum(sellBean);
			
		}
		return "redirect:toBreakCheck.action";
	}

	// 报损申请
	@Override
	public String breakApply(DrugApplyBean drugApplyBean) {
//		 AdminBean admin=(AdminBean) req.getSession().getAttribute("User");//获取管理员信息
		List<DrugApplyBean> list=drugApplyBean.getApplyList();
		for (int i = 0; i < list.size(); i++) {
			DrugApplyBean da=list.get(i);
			da.setAdminId(1001);// 模拟操作人员数据
			
			/*预先扣除药房药品库存*/
			phaDrugBean=dailyWorkMapper.selectOnePhaDrug(da.getDrugId());
			String specific=phaDrugBean.getStoDrugBean().getSpecific();//获取药品规格
			String[] str=specific.split("-");
			int n=Integer.parseInt(str[0]);
			da.setApplyNum(da.getApplyNum()*n);//将报损数量单位设为最小规格单位
			dailyWorkMapper.reducePhaDrugNum2(da);// 减少库存
			
			/*预先扣除药房入库批次详情中的药品数量*/
			sellBean.setPutBatch(""+da.getBdBean().getBatchDetailId());
			sellBean.setSellNum(da.getApplyNum());
			dailyWorkMapper.reducePhaPutDrugNum(sellBean);
			
			
			/*增加申请记录*/
			da.setPutBatch(""+da.getBdBean().getBatchDetailId());
			dailyWorkMapper.breakApply(da);
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
		List<StoDrugBean> list = dailyWorkMapper.selectCanSellDrug(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/putDrugList");
		mav.addObject("drugList", list);
		return mav;
	}

	// 获取报损未审核列表
	@Override
	public ModelAndView selectBreakCheck(CondiBean condiBean) {
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

	// 查询调价记录
	@Override
	public ModelAndView selectAdjustPrice(CondiBean condiBean) {
		List<AdjustPriceBean> list = dailyWorkMapper.selectAdjustPrice(condiBean);
		int count = dailyWorkMapper.getAdjustCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("pharmacy/priceAdjustment");
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("adjustList", list);
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
	public String sellDrug(StoDrugBean stoDrugBean) {
		List<StoDrugBean> list = stoDrugBean.getDrugList();
		for (int i = 0; i < list.size(); i++) {
			SellBean sellBean = list.get(i).getSellBean();
			sellBean.setAdminId(1001);
			dailyWorkMapper.insertSell(sellBean);// 插入销售记录表
			dailyWorkMapper.reducePhaDrugNum(sellBean);// 减少库存
			dailyWorkMapper.reducePhaPutDrugNum(sellBean);//减少药房入库批次详情药品数量
			batchDetailBean.setDrugId(sellBean.getDrugId());
			batchDetailBean.setHandleNum(sellBean.getSellNum());
			batchDetailBean.setManuBatch(sellBean.getManuBatch());
			dailyWorkMapper.insertBatchDetail(batchDetailBean);//插入药品出库批次详情记录
			outAndInBean.setAdminId(1001);//模拟管理员数据
			outAndInBean.setDestination("病人");
			outAndInBean.setTotalMoney(sellBean.getSales());
			outAndInBean.setPutBatch(""+batchDetailBean.getBatchDetailId());
			dailyWorkMapper.insertDrugOut(outAndInBean);// 插入药房出库记录
		}
		return "redirect:toSellDrug.action";
	}

	// 卖药弹窗
	@Override
	public ModelAndView sellDrugLayer(CondiBean condiBean) {
		// List<PhaDrugBean> list=dailyWorkMapper.selectPhaDrug(condiBean);
		List<StoDrugBean> list = dailyWorkMapper.selectCanSellDrug(condiBean);
		ModelAndView mav = new ModelAndView("pharmacy/sellDrugLayer");
		mav.addObject("drugList", list);
		return mav;
	}

}
