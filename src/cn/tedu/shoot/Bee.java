package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bee extends FlyingObject{
	private int ySpeed;//速度
	private int xSpeed;//速度
	private int awardType;//奖励类型(0:命  1:火力)
	private static BufferedImage[] image = new BufferedImage[5];

	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bee"+i+".png");
		}
	}
	
	public Bee() {
		super(60, 50);
		this.ySpeed = 40;
		this.xSpeed = 20;
		this.awardType = new Random().nextInt(2);//左闭右开
	}
	
	//蜜蜂 移动方法
	public void step(){
		y += ySpeed;
		x += awardType == 0 ? 1 : -1;
		System.out.println("蜜蜂 移动"+this.xSpeed+this.ySpeed);
	}
	
	// 蜜蜂切换图片
	public BufferedImage getImage(){
		if (checkAlive()){
			return image[0];
		} else if (checkDead()){
			state = DELETE;
		}
		return null;
	}
}
