package com.zhangchi.tank;

import java.awt.Graphics;
import java.util.Random;

public class Explode {

	//爆炸位置
	private int x;
	private int y;
	
	//爆炸大小  直接去图片的长宽
	public static int width = ResoureMgr.explode[0].getWidth();
	public static int height = ResoureMgr.explode[0].getHeight();
	
	//得到窗口frame对象的引用
	private TankFrame tf = null;
	
	
	//坦克是否存活标志
	private boolean living =true;
	
	//定义一个随机数
	private Random random  = new Random();
	
	//定义一个步数
	private int step = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
	}



	//画爆炸的方法
	public void paint(Graphics g) {
		if(step < ResoureMgr.explode.length){
			g.drawImage(ResoureMgr.explode[step++], x, y, null);
		}else{
			tf.expList.remove(this);
		}
	}


	

	//爆炸消亡
	public void die() {
		living = false;
		
	}
	
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}
 

	public void setY(int y) {
		this.y = y;
	}


}
