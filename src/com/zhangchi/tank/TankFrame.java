package com.zhangchi.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame{
	
	int x = 200;
	int y = 200;
	//初始化一个方向
	Dir dir = Dir.DOWN;
	//坦克速度
	final static int speed = 5;
	
	
	//构造方法 形成一个窗口
	public TankFrame(){
		this.setSize(800,600);
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
	
	//Graphics是系统的一直画笔 paint方法会被自动调用
	@Override
	public void paint(Graphics g){
		//画一个矩形  左上角为 0 0   长和高分别为50 
		g.fillRect(x, y, 50, 50);
		System.out.println("重画一次");
		/*x=x+50;
		y=y+50;*/
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
			System.out.println("健被抬起了");
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
			default:
				break;
			}
			
			setMainTankDir();
			
		}
		
		//改变方向的方法
		private void setMainTankDir() {
			if(bl){
				dir = Dir.LEFT;
			}
			
			if(br){
				dir = Dir.RIGHT;
			}
			
			if(bu){
				dir = Dir.UP;
			}
			
			if(bd){
				dir = Dir.DOWN;
			}
		}
		
		
		
	}
	

}
