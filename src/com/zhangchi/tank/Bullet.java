package com.zhangchi.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

	//子弹位置
	private int x ;
	private int y;
	//子弹方向
	private Dir dir ;
	//子弹速度
	private static final int speed = 10;
	
	
	//子弹大小
	private int width = 10;
	private int height = 10;
	
	//构造方法
	public Bullet(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	//画出子弹的方法
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, this.width, this.height);
		System.out.println("重画一次子弹");
		move();

	}
	
	
	private void move() {
		
		switch (dir){
		case LEFT:
			x=x-speed;
			break;
		case RIGHT:
			x=x+speed;
			break;
		case UP:
			y=y-speed;
			break;
		case DOWN:
			y=y+speed;
			break;
		default:
			break;
			
		}
		
	}
	
}
