package com.zhangchi.tank;


import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {

	//子弹位置
	private int x ;
	private int y;
	//子弹方向
	private Dir dir ;
	//子弹速度
	private static final int speed = 8;
	//子弹大小 
	public static int width = ResoureMgr.bulletD.getWidth();
	public static int height = ResoureMgr.bulletD.getHeight();
	
	//判断子弹是否消亡
	private boolean living = true;
	
	//得到一个frame的引用
	private TankFrame tf = null;
	
	//定义子弹阵营
	private Group group = Group.GOOD;
	
	//定义一个Rectangle
	public Rectangle bulletRectangle = new Rectangle();
	
	//构造方法
	public Bullet(int x, int y, Dir dir,Group group ,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		//初始化Rectangle的值
		bulletRectangle.x = this.x;
		bulletRectangle.y = this.y;
		bulletRectangle.width = width;
		bulletRectangle.height = height;
		
	}
	
	//画出子弹的方法
	public void paint(Graphics g) {
		/*g.setColor(Color.RED);
		g.fillOval(this.x, this.y, this.width, this.height);*/
		//将子弹转换为图片
		switch(dir){
		case LEFT:
			g.drawImage(ResoureMgr.bulletL, x, y+2, null);
			break;
		case RIGHT:
			g.drawImage(ResoureMgr.bulletR, x, y+2, null);
			break;
		case UP:
			g.drawImage(ResoureMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResoureMgr.bulletD, x, y, null);
			break;
		}
		System.out.println("重画一次子弹");
		move();
		if(!living){
			tf.bulletList.remove(this);
		}

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
		
		if(this.x < 0 || this.x > TankFrame.gameWidth || this.y < 0 || this.y > TankFrame.gameWidth) {
			living  = false;
		}
		
		//让rectangle属于随着子弹动
		bulletRectangle.x = this.x;
		bulletRectangle.y = this.y;
	
		
	}

	//使用Rectangle类中的 intersects方法   判断子弹和坦克相交
	public void collideWith(Tank tank) {
		//加入判断 如果是自己的子弹和自己的坦克  则不会伤害
		if(this.group == tank.getGroup()){
			return;
		}
		
		//
		/*Rectangle bulletRect = new Rectangle(this.x,this.y,width,height);
		Rectangle dTankRect = new Rectangle(tank.getX(),tank.getY(),Tank.width,Tank.height);
		*/
		//为了防止产生过多的Rectangle 对象，将这个对象放到坦克和子弹的对象中
		
		
		//爆炸位置
		int ex = tank.getX()+Tank.width/2 - Explode.width/2;
		int ey = tank.getY()+Tank.height/2 - Explode.height/2;
		
		// intersects方法   判断子弹和坦克相交
		if(bulletRectangle.intersects(tank.tankRectangle)){
			tf.expList.add(new Explode(ex,ey,tf));
			tank.die();
			this.die();
		}
	}

	//子弹死亡
	private void die() {
		this.living = false;
		
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
