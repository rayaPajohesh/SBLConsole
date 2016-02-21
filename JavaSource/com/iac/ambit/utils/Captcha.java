package com.iac.ambit.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iac.ambit.utils.Tracer;
               
public class Captcha extends HttpServlet{
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws
	ServletException, IOException {
	
		
		createImage(request,response);
		
	}
	
	public static void createImage(HttpServletRequest request,HttpServletResponse response) {
		final int width = 130;
		final int height = 40;
		
		File outfile;
		try {
			
			
			Random random = new Random();
			char data[][] = new char[1][];
			data[0] = getRandomNumber(5).toCharArray();
			
			
			final BufferedImage bufferedImage = new BufferedImage(width,
					height, BufferedImage.TYPE_INT_RGB);

			final Graphics2D g2d = bufferedImage.createGraphics();

			final Font  font = new Font("Verdana", Font.BOLD,  22);
			g2d.setFont(font);

			RenderingHints renderingHints = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			renderingHints.put(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);

			g2d.setRenderingHints(renderingHints);

			
			
			
			final GradientPaint gradientPaint = new GradientPaint(0, 0,
					Color.lightGray , 0, height / (random.nextInt(20)+2), Color.WHITE, true);

			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, 0, width, height);

			
			

			final String captcha = String.copyValueOf(data[0]);

			int xCordinate = 0;
			int yCordinate = 0;
			int xPreCordinate =10 + (Math.abs(random.nextInt()) % 10);
			int yPreCordinate=20 + Math.abs(random.nextInt()) % 10;
			
			for (int i = 0; i < data[0].length; i++) {
				

				xCordinate += random.nextInt(3)+ 15 + (Math.abs(random.nextInt()) % 10); //10
				if (xCordinate >= width - 5) {
					xCordinate = 0;
				}
				yCordinate = 20 + Math.abs(random.nextInt()) % 20; //20
				
				
				float brightness = 0.4f + random.nextFloat() * 0.2f;
				Color randomColor = Color.getHSBColor( random.nextFloat(), random.nextFloat(), brightness);
				g2d.setPaint(randomColor);
          	    g2d.drawLine(xCordinate ,yCordinate-2  , xPreCordinate, yPreCordinate-2);				
				g2d.drawLine(xCordinate ,yCordinate-(random.nextInt(5)+2)  , xPreCordinate, yPreCordinate-(random.nextInt(5)+2));		
				g2d.drawLine(xCordinate ,yCordinate-(random.nextInt(5)+5)  , xPreCordinate, yPreCordinate-(random.nextInt(5)+5));
				
				randomColor = Color.getHSBColor( random.nextFloat(), random.nextFloat(), brightness/2);
				g2d.setPaint(randomColor);
				g2d.drawChars(data[0], i, 1, xCordinate , yCordinate );
				
			
			
				yPreCordinate = yCordinate;
				xPreCordinate=xCordinate;

			}

			g2d.dispose();
			

			byte[] captchaBytes;
			ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
			String sessionid = request.getSession().getId();
				
			response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			OutputStream outputStream = response.getOutputStream();
		    ImageIO.write(bufferedImage, "jpeg", imgOutputStream);
		    captchaBytes = imgOutputStream.toByteArray();

		    outputStream.write( captchaBytes );
		    outputStream.flush();
		    outputStream.close();

			 
			request.getSession().setAttribute("customerCaptcha",captcha);
			
			
			
		//	
			
		} catch (Exception e) {
			Tracer.traceOut(Tracer.Tracing_Level.ERROR,"Captcha","createImage","Error : " + e.getMessage());
			e.printStackTrace();
		}

	}
	

	public static String getRandomNumber(int length) {

	//String chars = "abcdefghjijkmnpqrstuvwxyz123456789";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		Random random = new Random();

		char[] buf = new char[length];

		for (int i = 0; i < length; i++) {
			buf[i] = chars.charAt(random.nextInt(chars.length()));
		}

		return new String(buf);

	}
	


}
