package cn.tedu.shoot;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sky extends FlyingObject{
	private int y1;//第二张图片轮换
	private int ySpeed;//速度
	private static BufferedImage image;
	
	static{
		image = loadImage("../../res/background.png");
	}	
	
	public Sky() {
		super(400,700,0,0);
		//this.width = 400;//固定值
		//this.height = 700;
		//this.x = 0;//初值为0
		//this.y = 0;
		this.ySpeed = 2;
		this.y1 = -this.height;// 为-高度
	}

	//天空 移动方法
	public void step(){
		System.out.println("天空 移动"+this.ySpeed);
	}
}
