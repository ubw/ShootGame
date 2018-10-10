package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private int ySpeed;//速度
	private static BufferedImage image;
	
	static{
		image = loadImage("img_ariplane1.png");
	}
	
	public Bullet( int x, int y) {
		super(8,14,x,y);
		//this.width = 8;
		//this.height = 14;
		//this.x = x;//随英雄机的位置变化
		//this.y = y;
		this.ySpeed = 4;
	}

	//子弹 移动方法
	public void step(){
		System.out.println("子弹y坐标 移动"+this.ySpeed);
	}
}
