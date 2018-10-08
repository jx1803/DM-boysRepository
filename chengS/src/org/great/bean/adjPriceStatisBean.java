package org.great.bean;

import java.util.Arrays;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName:  药品调价统计
 * @Description:获取药品名、调价数据和调价次数   
 * @author: 程帅 
 * @date:  2018年9月29日 下午4:12:10   
 *    
 * @Copyright: 2018 www.cykj.com Inc. All rights reserved. 
 * 注意：本内容仅限于传一科技内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
public class adjPriceStatisBean {
	
	private String generalName;	//药品名称
	private Object [] dataArray;//调价数据
	private Object [] countArray;//调价次数
	
	public adjPriceStatisBean() {
		
	}

	public String getGeneralName() {
		return generalName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public Object[] getDataArray() {
		return dataArray;
	}

	public void setDataArray(Object[] dataArray) {
		this.dataArray = dataArray;
	}

	public Object[] getCountArray() {
		return countArray;
	}

	public void setCountArray(Object[] countArray) {
		this.countArray = countArray;
	}

	@Override
	public String toString() {
		return "adjPriceStatisBean [generalName=" + generalName + ", dataArray=" + Arrays.toString(dataArray)
				+ ", countArray=" + Arrays.toString(countArray) + "]";
	}
	
	
}
