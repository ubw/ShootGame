package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private int ySpeed;//速度
	private static BufferedImage image;
	public static final int BULLET_WIDTH = 8;
	public static final int BULLET_HEIGHT = 14;
	
	static{
		image = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bullet.png");
	}
	
	public Bullet( int x, int y) {
		super(BULLET_WIDTH,BULLET_HEIGHT,x,y);
		this.ySpeed = 4;
	}

	//子弹 移动方法
	public void step(){
		y = y-ySpeed;
		System.out.println("子弹y坐标 移动"+this.ySpeed);
	}
	
	// 子弹切换图片
	public BufferedImage getImage(){
		if (checkAlive()){
			return image;
		} else if (checkDead()){
			state = DELETE;
		}
		return null;
	}
}
