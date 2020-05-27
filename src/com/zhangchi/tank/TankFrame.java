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
	
	//构造方法
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
		
	}
	
	//定义一个内部类
	class MyKeyListener extends KeyAdapter{
		
		//当一个按键被按下的时候触发
		@Override
		public void keyPressed(KeyEvent e){
			System.out.println("健被按下了");
			x=x+20;
			repaint();
		}
		
		//当一个按键被抬起来的时候触发
		@Override
		public void keyReleased(KeyEvent e){
			System.out.println("健被抬起了");
		}
	}
	

}
