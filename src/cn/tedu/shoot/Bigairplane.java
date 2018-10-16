package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bigairplane extends FlyingObject{
	private int ySpeed;//速度
	private static BufferedImage[] image = new BufferedImage[5];
	private int stateIndex = 1;
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bigplane"+i+".png");
		}
	}
	
	public Bigairplane() {
		super(69,99);
		this.ySpeed = 29;
	}
	//敌机 移动方法
	public void step(){
		y += ySpeed;
	}
	
	// 大敌机切换图片的方法
	public BufferedImage getImage(){
		if (checkAlive()){
			return image[0];
		} else if (checkDead()) {
			if (stateIndex != image.length){
				return image[stateIndex++];	
			} else {
				state = DELETE;
			}
		}
		return null;
	}
}
