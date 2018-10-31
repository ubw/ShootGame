package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bee extends FlyingObject implements Award{
	private int ySpeed;//速度
	private int xSpeed;//速度
	private int awardType;//奖励类型(0:命  1:火力)
	private static BufferedImage[] image = new BufferedImage[5];
	private static final int WIDTH = 60;
	private static final int HEIGHT = 50;

	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bee"+i+".png");
		}
	}
	
	public Bee() {
		super(WIDTH, HEIGHT);
		this.ySpeed = 4;
		
		this.awardType = new Random().nextInt(2);//左闭右开
		this.xSpeed = ( awardType == 0 ) ? 3 : -3;
	}
	
	//蜜蜂 移动方法
	public void step(){
		y += ySpeed;
		if( x >= World.WIDTH-WIDTH || x <= 0){
			xSpeed = -xSpeed;
		}
		x += xSpeed;
		
		if(y>World.HEIGHT){
			this.state = DEAD;
		}
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
	
	public int getAward(){
		return awardType;
	}

}
