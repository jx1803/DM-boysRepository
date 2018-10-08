package org.great.tools;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.great.bean.AdminBean;
import org.great.bean.LogBean;
import org.great.mapper.DrugAllocatMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
public class LogAop
{
	@Resource
	private LogBean logBean;
	@Resource
	private DrugAllocatMapper damapper;

	// 配置接入点
	@Pointcut("execution(* org.great.controller..*.*(..))")
	private void controllerAspect()
	{
	}// 定义一个切入点

	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable
	{
	
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		// 获取登录用户id
		AdminBean  adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("User");
		
		if (adminBean == null)
		{
			return pjp.proceed();
		}
		
		int userId = adminBean.getAdminId();
		logBean.setAdminId(userId);
		// 获取系统时间
		logBean.setOperateDate(DateUtil.getDayBegin());
		// 拦截的实体类，就是当前正在执行的controller
		Object target = pjp.getTarget();
		// 拦截的方法名称。当前正在执行的方法
		String methodName = pjp.getSignature().getName();
		// 拦截的方法参数
		Object[] args = pjp.getArgs();
		// 拦截的放参数类型
		Signature sig = pjp.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature))
		{
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Class[] parameterTypes = msig.getMethod().getParameterTypes();

		Object object = null;
		// 获得被拦截的方法
		Method method = null;
		try
		{
			method = target.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (null != method)
		{
			
			if (method.isAnnotationPresent(Log.class))
			{
				Log log = method.getAnnotation(Log.class);
				logBean.setOperateItem(log.operationName());
				System.out.println("日志数据"+logBean.toString());
				damapper.addLog(logBean);
				object = pjp.proceed();
			} else
			{// 没有包含注解
				object = pjp.proceed();
			}
		} else
		{ // 不需要拦截直接执行
			object = pjp.proceed();
		}
		
		return object;
	}
}
