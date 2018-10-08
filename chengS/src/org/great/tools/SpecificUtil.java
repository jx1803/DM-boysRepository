package org.great.tools;

import javax.annotation.Resource;

import org.great.bean.PhaDrugBean;
import org.great.mapper.DailyWorkMapper;

public class SpecificUtil {
	@Resource
	private static DailyWorkMapper dailyWorkMapper;// 映射器（日常工作）
	public static int getSpecificNum(int drugId) {
		
		PhaDrugBean phaDrugBean = dailyWorkMapper.selectOnePhaDrug(drugId);
		String specific = phaDrugBean.getStoDrugBean().getSpecific();// 获取药品规格
		String[] str = specific.split("-");
		int n = Integer.parseInt(str[0]);
		return n;
	}
	
public static String getSpecific(int drugId) {
		
		PhaDrugBean phaDrugBean = dailyWorkMapper.selectOnePhaDrug(drugId);
		String specific = phaDrugBean.getStoDrugBean().getSpecific();// 获取药品规格
		String[] str = specific.split("-");
		return str[1];
	}
}
