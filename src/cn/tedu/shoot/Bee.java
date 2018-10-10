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
			image[i] = loadImage("bee"+i+".png");
		}
	}
	
	public Bee() {
		super(60, 50);
		//this.width = 60;
		//this.height = 50;
		//this.x = (int)(Math.random()*(400-this.width));//位置不定,随机数生成的第一种方法
		//this.x = new Random().nextInt(400-this.width);//随机数生成的第二种方法
		//this.y = -this.height;
		this.ySpeed = 2;
		this.xSpeed = 1;
		this.awardType = new Random().nextInt(2);//左闭右开
	}
	
	//蜜蜂 移动方法
	public void step(){
		System.out.println("蜜蜂 移动"+this.xSpeed+this.ySpeed);
	}
}
