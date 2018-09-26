/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.biz
 * 文件名称：StockWorkBizImpl.java
 * 
 * 创建时间：2018年9月18日 下午8:49:18
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.bean.BatchDetailBean;
import org.great.bean.CondiBean;
import org.great.bean.DrugApplyBean;
import org.great.bean.InventoryBean;
import org.great.bean.OutAndInBean;
import org.great.bean.StoDrugBean;
import org.great.mapper.DailyWorkMapper;
import org.great.mapper.StockWorkMapper;
import org.great.tools.DateUtil;
import org.great.tools.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: StockWorkBizImpl
 * @Description: 库存工作业务实现类
 * @author: 周志展(lenovo)
 * @date: 2018年9月18日 下午8:49:18
 */
@Service
public class StockWorkBizImpl implements IStockWorkBiz {
	@Resource
	private StockWorkMapper stockWorkMapper;
	@Resource
	private DailyWorkMapper dailyWorkMapper;

	/***
	 * 药品采购申请
	 * 
	 * @see org.great.biz.IStockWorkBiz#purchaseApply(org.great.bean.DrugApplyBean)
	 */
	@Override
	public String purchaseApply(HttpServletRequest request, DrugApplyBean drugApplyBean) {
		// TODO Auto-generated method stub
		for (int i = 0, len = drugApplyBean.getApplyList().size(); i < len; i++) {
			drugApplyBean.getApplyList().get(i).setAdminId(1001);
			drugApplyBean.getApplyList().get(i).setCheckId(7);
			drugApplyBean.getApplyList().get(i).setApplyTypeId(13);
			stockWorkMapper.purchaseApply(drugApplyBean.getApplyList().get(i));// 插入药品采购申请
		}
		System.out.println("插入数据成功");
		return "storage/purchaseApply";
	}

	/**
	 * 通过拼音码/药品编号/药品名称查找药品
	 * 
	 * @see org.great.biz.IStockWorkBiz#stoDrugSearch(org.great.bean.StoDrugBean)
	 */
	@Override
	public List<StoDrugBean> stoDrugSearch(StoDrugBean stoDrugBean) {
		// TODO Auto-generated method stub
		return stockWorkMapper.stoDrugSearch(stoDrugBean);
	}

	/**
	 * 申请记录查询
	 * 
	 * @see org.great.biz.IStockWorkBiz#selectDrugApply(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView drugApplyAudit(CondiBean condiBean) {
		condiBean.setCheckId(7);// 审核类型
		condiBean.setApplyTypeId(13);// 采购类型
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		int count = dailyWorkMapper.getDrugApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/purchaseAudit");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	/**
	 * 药品采购申请审核不通过
	 * 
	 * @see org.great.biz.IStockWorkBiz#purchaseAuditFail(org.great.bean.DrugApplyBean)
	 */
	@Override
	public String purchaseAuditFail(DrugApplyBean drugApplyBean) {
		drugApplyBean.setApplyTypeId(13);// 设置申请的类别
		// drugApplyBean.setCheckId(8);//审核不通过
		stockWorkMapper.purchaseAuditFail(drugApplyBean);
		System.out.println("修改审核状态");
		return "redirect:/storage/drugApplyAudit.action";
	}

	/**
	 * 查看采购申请记录
	 * 
	 * @see org.great.biz.IStockWorkBiz#getDrugPurchaseApply(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView purchaseApplyLook(CondiBean condiBean) {
		condiBean.setApplyTypeId(13);// 采购类型
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		int count = dailyWorkMapper.getDrugApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/purchaseApplyLook");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("count", count);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	/**
	 * 查看需要购买药品的记录
	 * 
	 * @see org.great.biz.IStockWorkBiz#pdInstorage(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView pdInstorage(CondiBean condiBean) {
		condiBean.setCheckId(23);// 审核类型
		condiBean.setApplyTypeId(13);// 采购类型
		List<DrugApplyBean> list = dailyWorkMapper.selectDrugApply(condiBean);
		int count = dailyWorkMapper.getDrugApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/pdInstorage");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("count", count);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	/**
	 * 药品采购确认
	 * 
	 * @see org.great.biz.IStockWorkBiz#updatePurState(org.great.bean.DrugApplyBean)
	 */
	@Override
	public ModelAndView pdInstorageConfirm(DrugApplyBean drugApplyBean) {
		StoDrugBean sdBean = stockWorkMapper.getStoDrugBean(drugApplyBean);
		ModelAndView mav = new ModelAndView();
		mav.addObject("sdBean", sdBean);
		mav.addObject("daBean", drugApplyBean);
		mav.setViewName("storage/pdInstorageConfirm");
		return mav;// 跳转药品入库确认页面

	}

	/**
	 * 药品采购入库成功，增加采购入库批次详情，增加采购入库记录，修改库存量
	 * 
	 * @see org.great.biz.IStockWorkBiz#pdInstorageSuccess(org.great.bean.BatchDetailBean)
	 */
	@Override
	public String pdInstorageSuccess(BatchDetailBean batchDetailBean) {
		batchDetailBean.getOutAndInBean().setPlaceId(22);// 药库
		batchDetailBean.getOutAndInBean().setOutInId(18);// 入库
		batchDetailBean.setPutBatch(DateUtil.getDayBegin()+batchDetailBean.getPutBatch());//入库批次
		batchDetailBean.getOutAndInBean().setPutBatch(batchDetailBean.getPutBatch());//设置入库记录中的入库批次
		batchDetailBean.getOutAndInBean().setTotalMoney(batchDetailBean.getTotalMoney());//设置入库记录中的总价
		batchDetailBean.getOutAndInBean().setDrugSource(batchDetailBean.getStoDrugBean().getDrugmanu());// 来源
		stockWorkMapper.addBatchDetail(batchDetailBean);// 增加采购入库批次详情
		
		stockWorkMapper.addPurOutAndIn(batchDetailBean.getOutAndInBean());// 增加采购入库记录
		
		int total = Integer.valueOf(batchDetailBean.getHandleNum())+Integer.valueOf(batchDetailBean.getInventoryBean().getInventoryNum());
		batchDetailBean.getInventoryBean().setInventoryNum(total);//设置总库量
		batchDetailBean.getInventoryBean().setDrugId(batchDetailBean.getDrugId());//设置库存表中药品Id
		stockWorkMapper.updateInventory(batchDetailBean.getInventoryBean());//修改库存量
		
		stockWorkMapper.updatePurState(batchDetailBean.getDrugApplyBean());
		return "redirect:/storage/pdInstorage.action";
	}

}
