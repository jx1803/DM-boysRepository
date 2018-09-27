/***********************************
 * 版权所有   (c)2018, 厦门great corp<p>
 * @Package: org.great.controller
 * 文件名称：StorStatisticsHandle.java
 * 
 * 创建时间：2018年9月26日 下午5:15:02
 * @author: 周志展(lenovo)
 * 当前版本号：v0.1
 ***********************************/

package org.great.controller;

import javax.annotation.Resource;

import org.great.biz.IStorStatisticsBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: StorStatisticsHandle 
 * @Description: 药库统计控制层
 * @author: 周志展(lenovo)  
 * @date: 2018年9月26日 下午5:15:02  
 */
@Controller
@RequestMapping("/storage")
public class StorStatisticsHandle {
	@Resource
	private IStorStatisticsBiz storStatisticBizImpl;//药库统计业务实现类

}
