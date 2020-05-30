package com.zhangchi.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {

	//坦克位置
	private int x;
	private int y;
	//初始化一个方向
	private Dir dir = Dir.DOWN;
	private final static int speed = 5;
	
	//坦克大小
	private int width = 50;
	private int height = 50;
	
	//定义是否是移动状态
	private boolean moving = false;
	
	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
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


	public Dir getDir() {
		return dir;
	}


	public void setDir(Dir dir) {
		this.dir = dir;
	}

	

	public boolean isMoving() {
		return moving;
	}


	public void setMoving(boolean moving) {
		this.moving = moving;
	}


	//画出坦克的方法
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, this.width, this.height);
		System.out.println("重画一次坦克");
		move();

	}


	private void move() {
		if(!moving){
			return;
		}
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
