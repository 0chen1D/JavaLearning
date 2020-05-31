package com.zhangchi.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame{
	public static int gameWidth = 1080;
	public static int gameHeight = 960;
/*	int x = 200;
	int y = 200;
	//初始化一个方向
	Dir dir = Dir.DOWN;
	//坦克速度
	private final static int speed = 5;*/
	
	//得到一个坦克
	//将窗口对象（自己）传入到坦克的构造方面里面
	Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
	
	//Bullet bullet = new Bullet(200,200,Dir.DOWN);
	//将子弹变更为可支持多颗
	List<Bullet> bulletList = new ArrayList<Bullet>();
	
	//多个地方坦克
	List<Tank> dTank = new ArrayList<Tank>();
	
	//定义一个爆炸
	//Explode exp = new Explode(100,100,this);
	List<Explode> expList = new ArrayList<Explode>();
	
	//构造方法 形成一个窗口
	public TankFrame(){
		this.setSize(gameWidth,gameHeight);
		//设置窗口可见
		this.setVisible(true);
		//设置是否可以拖动窗口大小
		this.setResizable(false);
		//设置标题
		this.setTitle("Tank War");
		
		//获得一个窗口的监听
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				System.out.print("窗口关闭");
				System.exit(0);
			}
		});
		
		//获得一个键盘监听 
		this.addKeyListener(new  MyKeyListener());
		
	}
	
	
	//通过双缓冲解决闪烁问题
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(gameWidth, gameHeight);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, gameWidth, gameHeight);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	//Graphics是系统的一直画笔 paint方法会被自动调用
	@Override
	public void paint(Graphics g){
		//画一个矩形  左上角为 0 0   长和高分别为50 
/*		g.fillRect(x, y, 50, 50);
		System.out.println("重画一次");
		x=x+50;
		y=y+50;
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
			
		}*/
		//在屏幕上输出字体
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量："+bulletList.size(), 10, 60);
		g.drawString("敌人坦克的数量："+dTank.size(), 10, 80);
		g.drawString("爆炸的数量："+expList.size(), 10, 100);
		
		//通过坦克类 让坦克自己把自己画出来
		myTank.paint(g);
		//子弹类自己画出子弹
		//bullet.paint(g);
		//将List中的子弹全部画出来  使用迭代器的循环方式 不允许除迭代器以外的方式删除
		/*for(Bullet b : bulletList){
			b.paint(g);
		}*/
		for (int i =0;i<bulletList.size();i++){
			bulletList.get(i).paint(g);
		}
		
		//画出所有地方坦克
		for (int i = 0; i < dTank.size(); i++){
			dTank.get(i).paint(g);
		}
		
		//判断所有子弹和坦克是否相撞  用于击杀敌方坦克
		for (int i = 0;i < bulletList.size(); i++){
			for(int j =0 ;j < dTank.size(); j++){
				bulletList.get(i).collideWith(dTank.get(j));
			}
			
		}
		
		//画出爆炸
		for(int i = 0 ;i < expList.size(); i++){
			expList.get(i).paint(g);
		}
		
	}
	
	//定义一个内部类
	class MyKeyListener extends KeyAdapter{
		boolean bl = false;
		boolean br = false;
		boolean bu = false;
		boolean bd = false;
		
		//当一个按键被按下的时候触发
		@Override
		public void keyPressed(KeyEvent e){
			System.out.println("键被按下了");
			int key = e.getKeyCode();
			switch (key){
			case KeyEvent.VK_LEFT:
				bl = true;
				break;
			case KeyEvent.VK_RIGHT:
				br = true;
				break;
			case KeyEvent.VK_UP:
				bu = true;
				break;
			case KeyEvent.VK_DOWN:
				bd = true;
				break;
			default:
				break;
			}
			//repaint();
			//改变坦克的方向
			setMainTankDir();
		}
		

		//当一个按键被抬起来的时候触发
		@Override
		public void keyReleased(KeyEvent e){
			System.out.println("键被抬起了");
			int key = e.getKeyCode();
			switch (key){
			case KeyEvent.VK_LEFT:
				bl = false;
				break;
			case KeyEvent.VK_RIGHT:
				br = false;
				break;
			case KeyEvent.VK_UP:
				bu = false;
				break;
			case KeyEvent.VK_DOWN:
				bd = false;
				break;
			//处理ctrl键打出一颗子弹
			case KeyEvent.VK_CONTROL:
				myTank.fire();
			default:
				break;
			}
			
			setMainTankDir();
			
		}
		
		//改变方向的方法 + 控制移动
		private void setMainTankDir() {
			if(!bl && !br && !bu && !bd){
				myTank.setMoving(false);
			}else{
				myTank.setMoving(true);
			}
			if(bl){
				//dir = Dir.LEFT;
				myTank.setDir(Dir.LEFT);
			}
			
			if(br){
				//dir = Dir.RIGHT;
				myTank.setDir(Dir.RIGHT);
			}
			
			if(bu){
				//dir = Dir.UP;
				myTank.setDir(Dir.UP);
			}
			
			if(bd){
				//dir = Dir.DOWN;
				myTank.setDir(Dir.DOWN);
			}
		}
		
		
		
	}
	

}
