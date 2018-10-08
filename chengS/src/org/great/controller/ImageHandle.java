package org.great.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.great.tools.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/code")
public class ImageHandle extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	 @RequestMapping(value = "/toCaptcha.action", method = { RequestMethod.POST,
	            RequestMethod.GET })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("11111111111");
		Map<String, BufferedImage> imageb= ImageUtil.createImage();
		//得到验证码
		String code = imageb.keySet().iterator().next();
		//得到图片
		BufferedImage  image = imageb.get(code);
		
		//验证码存在session
		request.getSession().setAttribute("keyCode", code);
		
		//输出图片
		ServletOutputStream out = response.getOutputStream();
		
		//第一个参数是图片，第二个是扩展名，第三个输出方式
		ImageIO.write(image, "jpg", out);
	}
}
