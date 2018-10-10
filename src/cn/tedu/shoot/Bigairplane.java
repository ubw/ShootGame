package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bigairplane extends FlyingObject{
	private int ySpeed;//速度
	private static BufferedImage[] image = new BufferedImage[5];
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("bigplane"+i+".png");
		}
	}
	
	public Bigairplane() {
		super(69,99);
		//this.width = 69;
		//this.height = 99;
		//this.x = (int)(Math.random()*(400-this.width));//位置不定,随机数生成的第一种方法
		//this.x = new Random().nextInt(400-this.width);//随机数生成的第二种方法
		//this.y = -this.height;
		this.ySpeed = 2;
	}
	//敌机 移动方法
	public void step(){
		System.out.println("大敌机 移动"+this.ySpeed);
	}
}
