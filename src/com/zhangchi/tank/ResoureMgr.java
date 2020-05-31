package com.zhangchi.tank;

//静态资源加载
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResoureMgr {
	
	public static BufferedImage goodtankL = null;
	public static BufferedImage goodtankR = null;
	public static BufferedImage goodtankU = null;
	public static BufferedImage goodtankD = null;
	
	public static BufferedImage badtankL = null;
	public static BufferedImage badtankR = null;
	public static BufferedImage badtankU = null;
	public static BufferedImage badtankD = null;
	
	public static BufferedImage bulletL = null;
	public static BufferedImage bulletR = null;
	public static BufferedImage bulletU = null;
	public static BufferedImage bulletD = null;
	
	//由于爆炸的图片比较多  所以定义一个数组
	public static BufferedImage[] explode = new BufferedImage[16];
	static {
		
		try {
			//坦克图片加载内存
			/*tankL = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
			tankR = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
			tankU = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
			tankD = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
			
			//子弹图片加载内存
			bulletL = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
			bulletR = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
			bulletU = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
			bulletD = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));*/
			
			//通过图片旋转的方式
			goodtankU = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodtankL = ImageUtil.rotateImage(goodtankU, -90);
			goodtankR = ImageUtil.rotateImage(goodtankU, 90);
			goodtankD = ImageUtil.rotateImage(goodtankU, 180);
			
			
			badtankU = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badtankL = ImageUtil.rotateImage(badtankU, -90);
			badtankR = ImageUtil.rotateImage(badtankU, 90);
			badtankD = ImageUtil.rotateImage(badtankU, 180);
			
			bulletU = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			//讲所有爆炸图片加载到数组中
			for(int i = 0 ; i< 16 ; i++){
				explode[i] = ImageIO.read(ResoureMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
