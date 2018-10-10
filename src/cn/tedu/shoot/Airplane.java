package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//加载图片的类

public class Airplane extends FlyingObject{
	private int ySpeed;//速度
	private static BufferedImage[] image = new BufferedImage[5];//从正常到炸毁，5张图
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("ariplane"+i+".png");
		}
	}
	
	{
		System.out.println("非静态初始化块");
	}

	public Airplane() {
		super(49, 36);
		//this.width = 49;//oop1老师写的是，如果写，写super。当子类继承父类时，是否会重新分配内存空间还是用的是父类的？
		//this.height = 36;
		//this.x = (int)(Math.random()*(400-this.width));//位置不定,随机数生成的第一种方法
		//this.x = new Random().nextInt(400-this.width);//随机数生成的第二种方法
		//this.y = -this.height;
		this.ySpeed = 2;
	}

	//敌机 移动方法
	public void step(){
		System.out.println("敌机 移动"+this.ySpeed);
	}
	
	public static void foo(){
		System.out.println("ariplane foo");
	}
	
	public  void fooInstance(){
		System.out.println("ariplane fooInstance");
	}

}
