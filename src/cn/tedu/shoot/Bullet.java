package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private int ySpeed;//速度
	private static BufferedImage image;
	public static final int WIDTH = 8;
	public static final int HEIGHT = 14;
	
	static{
		image = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bullet.png");
	}
	
	public Bullet( int x, int y) {
		super(WIDTH,HEIGHT,x,y);
		this.ySpeed = 4;
	}

	//子弹 移动方法
	public void step(){
		y -= ySpeed;
		
		if(y<-HEIGHT){
			this.state = DEAD;
		}
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
