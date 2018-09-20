package org.great.test;

import static org.junit.Assert.*;

import org.great.bean.*;
import org.great.biz.*;
import org.great.biz.IDailyWorkBiz;
import org.junit.Test;

public class UnitTest {

	@Test
	public void test() {
		IDailyWorkBiz dailyWorkBizImpl=new DailyWorkBizImpl();
		DrugApplyBean da=new DrugApplyBean(1, 1001, 1, 100,  5, 12, "破损");
		dailyWorkBizImpl.breakApply(da);
		
	}

}
