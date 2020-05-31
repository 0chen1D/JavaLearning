package com.zhangchi.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
/*		Frame f =new Frame();
		//设置窗口大小
		f.setSize(800,600);
		//设置窗口可见
		f.setVisible(true);
		//设置是否可以拖动窗口大小
		f.setResizable(false);
		//设置标题
		f.setTitle("Tank War");
		
		//获得一个窗口的监听
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				//System.out.print("123");
				System.exit(0);
			}
		});
		
	}*/
		TankFrame tf = new TankFrame();
		
		//增加敌方坦克
		for(int i = 0; i < 5; i++){
			tf.dTank.add(new Tank(200+i*80,200,Dir.DOWN,Group.BAD,tf));
		}
		
		//不断重画
		while(true){
			Thread.sleep(50);
			tf.repaint();
		}
		
	}
}
