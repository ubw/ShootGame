package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//加载图片的类

public class Airplane extends FlyingObject implements Enemy{
	private int ySpeed;//速度
	private static BufferedImage[] image = new BufferedImage[5];//从正常到炸毁，5张图
	public static final int WIDTH = 49;
	public static final int HEIGHTA = 36;
	private int stateIndex = 1;
	
	//给图片赋值，静态初始块
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/airplane"+i+".png");
		}
	}

	public Airplane() {
		super(WIDTH, HEIGHTA);
		this.ySpeed = 5;
	}

	//敌机 移动方法
	public void step(){
		y += ySpeed;
		if(y>World.HEIGHT){
			this.state = DEAD;
		}
	}
	
	// 切换图片的方法
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
	
	public int getScore(){
		return 1;
	}

}
