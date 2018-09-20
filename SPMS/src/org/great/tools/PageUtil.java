package org.great.tools;

public class PageUtil {
	public static int pageTotal(int count) {
		int pageTotal;
		if (count == 0) {
			pageTotal = 1;
		} else if (count % 5 == 0) {
			pageTotal = count/5;
		} else {
			pageTotal = count/5+1;
		}
		return pageTotal;
	}
}
