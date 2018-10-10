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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.taglibs.standard.tag.el.core.OutTag;
import org.great.bean.AdminBean;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @ClassName: StockWorkBizImpl
 * @Description: 库存工作业务实现类
 * @author: 周志展(lenovo)
 * @date: 2018年9月18日 下午8:49:18
 */
@Service
public class StockWorkBizImpl implements IStockWorkBiz {
	@Resource
	private StockWorkMapper stockWorkMapper;// (库存工作)映射器
	@Resource
	private DailyWorkMapper dailyWorkMapper;// (日常工作)映射器
	@Resource
	private BatchDetailBean batchDetailBean;// 出入库详情批次对象
	@Resource
	private OutAndInBean outAndInBean;// 出入库记录实体
	@Resource
	private IDailyWorkBiz dailyWorkBizImpl;// 出入库记录实体
	
	private List<BatchDetailBean> purchaseList;// 已采购列表

	/* 采购模块 */
	/***
	 * 药品采购申请
	 * 
	 * @see org.great.biz.IStockWorkBiz#purchaseApply(org.great.bean.DrugApplyBean)
	 */
	@Override
	public String purchaseApply(HttpServletRequest request, DrugApplyBean drugApplyBean) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AdminBean admin = (AdminBean) session.getAttribute("User");
		for (int i = 0, len = drugApplyBean.getApplyList().size(); i < len; i++) {
			drugApplyBean.getApplyList().get(i).setAdminId(admin.getAdminId());
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
		mav.addObject("condiBean", condiBean);
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
		stockWorkMapper.purchaseAuditFail(drugApplyBean);
		System.out.println("修改审核状态");
		return "redirect:/storage/drugApplyAudit.action";
	}

	/**
	 * 查看所有采购申请记录
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
		mav.addObject("checkId", condiBean.getCheckId());
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
		mav.addObject("condiBean", condiBean);
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
	 * 药品采购入库成功，增加采购入库批次详情，增加采购入库记录，修改库存量，更新药品进价
	 * 
	 * @see org.great.biz.IStockWorkBiz#pdInstorageSuccess(org.great.bean.BatchDetailBean)
	 */
	@Override
	public String pdInstorageSuccess(BatchDetailBean batchDetailBean) {
		batchDetailBean.getOutAndInBean().setPlaceId(22);// 药库
		batchDetailBean.getOutAndInBean().setOutInId(18);// 入库
		batchDetailBean.setPutBatch(DateUtil.getDayBegin() + batchDetailBean.getPutBatch());// 入库批次
		batchDetailBean.getOutAndInBean().setPutBatch(batchDetailBean.getPutBatch());// 设置入库记录中的入库批次
		batchDetailBean.getOutAndInBean().setTotalMoney(batchDetailBean.getTotalMoney());// 设置入库记录中的总价
		batchDetailBean.getOutAndInBean().setDrugSource(batchDetailBean.getStoDrugBean().getDrugmanu());// 来源
		stockWorkMapper.addBatchDetail(batchDetailBean);// 增加采购入库批次详情

		stockWorkMapper.addPurOutAndIn(batchDetailBean.getOutAndInBean());// 增加采购入库记录

		int total = Integer.valueOf(batchDetailBean.getHandleNum())
				+ Integer.valueOf(batchDetailBean.getInventoryBean().getInventoryNum());
		batchDetailBean.getInventoryBean().setInventoryNum(total);// 设置总库量
		batchDetailBean.getInventoryBean().setDrugId(batchDetailBean.getDrugId());// 设置库存表中药品Id

		stockWorkMapper.updateInventory(batchDetailBean.getInventoryBean());// 修改库存量

		stockWorkMapper.updatePurPrice(batchDetailBean);// 修改药库药品进价
		batchDetailBean.getDrugApplyBean().setPutBatch(batchDetailBean.getPutBatch());
		stockWorkMapper.updatePurState(batchDetailBean.getDrugApplyBean());// 更新采购入库状态
		return "redirect:/storage/pdInstorage.action";
	}

	/* 退还厂家模块 */
	/**
	 * 获取出入库批次详情列表
	 * 
	 * @see org.great.biz.IStockWorkBiz#getInventoryBatchList(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView getInventoryBatchList(CondiBean condiBean) {
		List<BatchDetailBean> inventoryBatchList = stockWorkMapper.getStoBatchList(condiBean);
		ModelAndView mav = new ModelAndView("storage/inventoryBatchList");
		mav.addObject("condiBean", condiBean);
		mav.addObject("inBatchList", inventoryBatchList);
		return mav;
	}

	/**
	 * 提交退还厂家申请
	 * 
	 * @see org.great.biz.IStockWorkBiz#returnManuApply(org.great.bean.DrugApplyBean)
	 */
	@Override
	public ModelAndView returnManuApply(DrugApplyBean drugApplyBean) {
		System.out.println(drugApplyBean.toString());
		for (int i = 0, len = drugApplyBean.getApplyList().size(); i < len; i++) {
			/* 获取页面传来的值，并设置到drugApplyBean中 */
			DrugApplyBean daBean = drugApplyBean.getApplyList().get(i);
			daBean.setCheckId(7);// 审核状态ID
			daBean.setApplyTypeId(19);// 退还厂家参数id
			String putId = "" + daBean.getBdBean().getBatchDetailId();
			daBean.setPutBatch(putId);// 设置退还申请实体批次为入库批次详情ID

			System.out.println("传过来的数量" + daBean.getApplyNum());
			/* 插入退还厂家申请 */
			stockWorkMapper.addReturnApply(daBean);
			int remainNum = Integer.valueOf(daBean.getInventoryNum()) - Integer.valueOf(daBean.getApplyNum());// 药库新剩余库存量
			int handleNum = Integer.valueOf(daBean.getBdBean().getHandleNum()) - Integer.valueOf(daBean.getApplyNum());// 申请数量
			BatchDetailBean bdBean = daBean.getBdBean();// 入库批次详情bean
			bdBean.setHandleNum(handleNum);
			/* 修改入库详情表数量 */
			stockWorkMapper.updateStoBatchNum(bdBean);
			/* 修改库存数量 */
			stockWorkMapper.updateInventoryNum(remainNum, daBean.getDrugId());
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("storage/returnManuApply");
		return mav;
	}

	/**
	 * 所有退还厂家记录查询
	 * 
	 * @see org.great.biz.IStockWorkBiz#selReturnManuApply(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView toAllReturnManuApply(CondiBean condiBean) {
		condiBean.setApplyTypeId(19);// 退还类型
		List<DrugApplyBean> list = stockWorkMapper.selectDrugApply(condiBean);
		int count = stockWorkMapper.selReturnManuApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/allReturnManuApply");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("count", count);
		mav.addObject("condiBean", condiBean);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	/**
	 * 退还厂家未审核记录查询
	 * 
	 * @see org.great.biz.IStockWorkBiz#toReturnManuAuditList(org.great.bean.CondiBean)
	 */
	@Override
	public ModelAndView toReturnManuAuditList(CondiBean condiBean) {
		condiBean.setCheckId(7);// 未审核状态
		condiBean.setApplyTypeId(19);// 退还类型
		List<DrugApplyBean> list = stockWorkMapper.selectDrugApply(condiBean);
		int count = stockWorkMapper.selReturnManuApplyCount(condiBean);
		int pageTotal = PageUtil.pageTotal(count);
		ModelAndView mav = new ModelAndView("storage/returnManuAuditList");
		mav.addObject("pageTotal", pageTotal);
		mav.addObject("pageNum", condiBean.getPageNum());
		mav.addObject("count", count);
		mav.addObject("condiBean", condiBean);
		mav.addObject("drugApplyList", list);
		return mav;
	}

	/**
	 * 退还审核
	 * 
	 * @see org.great.biz.IStockWorkBiz#returnManuSuccess(javax.servlet.http.HttpServletRequest,
	 *      java.lang.String, org.great.bean.BatchDetailBean)
	 */
	@Override
	public String returnManuAudit(HttpSession session,DrugApplyBean drugApplyBean) {
		int i = stockWorkMapper.udReturnManuApplyState(drugApplyBean);
		// 审核通过，修改状态，插入出入库记录表表
		if (drugApplyBean.getCheckId() == 6) {
			// 出入库详情实体bean
			double countMoney = Integer.valueOf(drugApplyBean.getApplyNum())
					* Double.valueOf(drugApplyBean.getBdBean().getPurPrice());
			batchDetailBean.setDrugId(drugApplyBean.getDrugId());
			batchDetailBean.setHandleNum(drugApplyBean.getApplyNum());
			batchDetailBean.setManuBatch(drugApplyBean.getBdBean().getManuBatch());
			batchDetailBean.setPurPrice(drugApplyBean.getBdBean().getPurPrice());
			batchDetailBean.setProDate(drugApplyBean.getBdBean().getProDate());
			batchDetailBean.setTotalMoney(countMoney);
			stockWorkMapper.insertBatchDetail(batchDetailBean);// 插入出入库详情信息表

			outAndInBean.setAdminId(drugApplyBean.getAuditorId());
			outAndInBean.setDestination(drugApplyBean.getStoDrugBean().getDrugmanu());
			outAndInBean.setTotalMoney(countMoney);
			outAndInBean.setPutBatch("" + batchDetailBean.getBatchDetailId());
			stockWorkMapper.insertToOut(outAndInBean);
			dailyWorkBizImpl.stoMinimunWarn(session,drugApplyBean.getDrugId());
		} else {
			// 审核不通过，将入库批次详情表数量，库存数量加回来
			drugApplyBean.setApplyNum(-drugApplyBean.getApplyNum());// 设置申请退还的数量
			stockWorkMapper.reduceInventoryNum(drugApplyBean);// 将库存数量加回来了

			drugApplyBean.setPutBatch("" + drugApplyBean.getBdBean().getBatchDetailId());
			stockWorkMapper.reduceStoPutDrugNum(drugApplyBean);// 将出入库批次详情表中申请的数量加回
		}
		return "redirect:/storage/toReturnManuAuditList.action";
	}

	/**
	 * 导出Excel文件
	 * 
	 * @see org.great.biz.IStockWorkBiz#exportExcel(org.great.bean.CondiBean)
	 */
	@Override
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, CondiBean condiBean)
			throws Exception {
		Date now = new Date();
		InputStream fin = null;
		ServletOutputStream out = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowdate = df.format(now);
		String root = request.getRealPath("/upload");// 设置文件上传路径
		System.out.println("路径：" + root);
		String fileName = "已采购药品清单" + nowdate + ".xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		File file = new File(root + fileName);
		// 打开文件
		WritableWorkbook book = Workbook.createWorkbook(file);

		System.out.println(fileName);

		// 生成名为"第一页"的工作表，参数0表示这是第一
		WritableSheet sheet = book.createSheet("第一页", 0);

		// 设置字体为宋体,16号字,加粗,颜色为黑色
		WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
		font1.setColour(Colour.BLACK);
		WritableCellFormat format1 = new WritableCellFormat(font1);
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		Label labelA = new Label(0, 0, "药品编码", format1);
		Label labelB = new Label(1, 0, "药品名称", format1);
		Label labelC = new Label(2, 0, "采购数量", format1);
		Label labelD = new Label(3, 0, "采购单价", format1);
		Label labelE = new Label(4, 0, "产地", format1);
		Label labelF = new Label(5, 0, "生产厂商", format1);
		Label labelG = new Label(6, 0, "生产批次", format1);
		Label labelH = new Label(7, 0, "入库批次", format1);
		Label labelI = new Label(8, 0, "采购日期", format1);

		// 将定义好的单元格添加到工作表中
		sheet.addCell(labelA);
		sheet.addCell(labelB);
		sheet.addCell(labelC);
		sheet.addCell(labelD);
		sheet.addCell(labelE);
		sheet.addCell(labelF);
		sheet.addCell(labelG);
		sheet.addCell(labelH);
		sheet.addCell(labelI);
		System.out.println("-----------------------------");
		// 接收数据
		condiBean.setCheckId(24);
		condiBean.setApplyTypeId(13);
		purchaseList = stockWorkMapper.getPurchaseList(condiBean);

		for (int i = 0; i < purchaseList.size(); i++) {
			Label labelAi = new Label(0, i + 1, String.valueOf(purchaseList.get(i).getDrugId()));
			Label labelBi = new Label(1, i + 1, purchaseList.get(i).getStoDrugBean().getDrugName());
			Label labelCi = new Label(2, i + 1, String.valueOf(purchaseList.get(i).getDrugApplyBean().getApplyNum()));
			Label labelDi = new Label(3, i + 1, String.valueOf(purchaseList.get(i).getPurPrice()));
			Label labelEi = new Label(4, i + 1, purchaseList.get(i).getStoDrugBean().getProPlace());
			Label labelFi = new Label(5, i + 1, purchaseList.get(i).getStoDrugBean().getDrugmanu());
			Label labelGi = new Label(6, i + 1, purchaseList.get(i).getManuBatch());
			Label labelHi = new Label(7, i + 1, purchaseList.get(i).getPutBatch());
			Label labelIi = new Label(8, i + 1, purchaseList.get(i).getOutAndInBean().getHandleDate());

			System.out.println("----------------------" + labelAi.toString());

			sheet.addCell(labelAi);
			sheet.addCell(labelBi);
			sheet.addCell(labelCi);
			sheet.addCell(labelDi);
			sheet.addCell(labelEi);
			sheet.addCell(labelFi);
			sheet.addCell(labelGi);
			sheet.addCell(labelHi);
			sheet.addCell(labelIi);
		}
		// 写入数据并关闭文件
		book.write();
		book.close();
		System.out.println("创建文件成功!");

		fin = new FileInputStream(file);

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/excel");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

		out = response.getOutputStream();
		byte[] buffer = new byte[1024];// 缓冲区
		int bytesToRead = -1;
		// 通过循环将读入的Word文件的内容输出到浏览器中
		while ((bytesToRead = fin.read(buffer)) != -1) {
			out.write(buffer, 0, bytesToRead);
			out.flush();
		}

		try {
			if (fin != null) {
				fin.close();
			}
			if (out != null) {
				out.close();
			}
			if (file != null) {
				file.delete(); // 删除临时文件
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
