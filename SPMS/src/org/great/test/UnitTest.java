package org.great.test;

import java.util.List;

import org.great.bean.DrugApplyBean;
import org.great.bean.DrugTypeBean;
import org.great.bean.StoDrugBean;
import org.great.mapper.DailyWorkMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UnitTest {

	@Test // 插入申请
	public void test() {
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		DailyWorkMapper dailyWorkMapper = ac.getBean(DailyWorkMapper.class);

		List<StoDrugBean> dbList = dailyWorkMapper.selDrugByName("");
		System.out.println(dbList.size());

	}

}
