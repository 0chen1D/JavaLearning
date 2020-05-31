package com.zhangchi.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {

	//坦克位置
	private int x;
	private int y;
	//初始化一个方向
	private Dir dir = Dir.DOWN;
	private final static int speed = 4;
	
	//坦克大小  直接去图片的长宽
	public static int width = ResoureMgr.goodtankU.getWidth();
	public static int height = ResoureMgr.goodtankU.getHeight();
	
	//得到窗口frame对象的引用
	private TankFrame tf = null;
	
	//定义是否是移动状态
	private boolean moving = true;
	
	//坦克是否存活标志
	private boolean living =true;
	
	//定义一个随机数
	private Random random  = new Random();
	
	//定义阵营
	private Group group = Group.GOOD;
	
	//定义一个Rectangle
	public Rectangle tankRectangle = new Rectangle();
	
	public Tank(int x, int y, Dir dir, Group group,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		//初始化Rectangle的值
		tankRectangle.x = this.x;
		tankRectangle.y = this.y;
		tankRectangle.width = width;
		tankRectangle.height = height;
		
	}


	


	//画出坦克的方法
	public void paint(Graphics g) {
		/*g.setColor(Color.YELLOW);
		g.fillRect(this.x, this.y, this.width, this.height);*/
		//将坦克更新为图片  三个参数分别为 图片  坐标  监听器（这里先使用null）
		switch(dir){
		case LEFT:
			g.drawImage(this.group == Group.GOOD ? ResoureMgr.goodtankL : ResoureMgr.badtankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD ? ResoureMgr.goodtankR : ResoureMgr.badtankR, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD ? ResoureMgr.goodtankU : ResoureMgr.badtankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD ? ResoureMgr.goodtankD : ResoureMgr.badtankD, x, y, null);
			break;
		}
		
		System.out.println("重画一次坦克");
		move();

	}


	private void move() {
		//如果坦克死亡  直接从list中移除（敌人不画了）
		if(!living){
			this.tf.dTank.remove(this);
		}
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
		
		//敌人坦克随机打出子弹
		if(random.nextInt(100) > 95 && this.group == Group.BAD){
			this.fire();
		}
		//让敌人坦克随机转变方向
		if(random.nextInt(100) > 95 && this.group == Group.BAD){
			this.randomDir();
		}
		
		//边界检测
		boundsCheck();
		
		//让rectangle属于随着坦克动
		tankRectangle.x = this.x;
		tankRectangle.y = this.y;
		
	}
	
	private void boundsCheck() {
		if(x < 0){
			x = 2;
		}
		if (x > TankFrame.gameWidth - Tank.width -2){
			x = TankFrame.gameWidth - Tank.width -2;
		}
		if(y < 28){
			y = 28;
		}
		if(y > TankFrame.gameHeight - Tank.height -2){
			y = TankFrame.gameHeight - Tank.height -2;
		}
		
	}

	//随机转变方向
	private void randomDir() {
		this.dir = this.dir.values()[random.nextInt(4)];
	}





	//开火 同时修改子弹的正确位置
	public void fire() {
		//得到frame窗口的引用后，直接将子弹new到窗口对象里面
		//支持多颗子弹
		int bulletx = this.x+Tank.width/2 - Bullet.width/2;
		int bullety = this.y+Tank.height/2 - Bullet.height/2 -2;
		tf.bulletList.add(new Bullet(bulletx,bullety,this.dir,this.group,this.tf));
	}


	//坦克死亡
	public void die() {
		living = false;
		
	}
	
	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		this.group = group;
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

	
	
}
