package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bigairplane extends FlyingObject implements Enemy{
	private int ySpeed;//�ٶ�
	private static BufferedImage[] image = new BufferedImage[5];
	private int stateIndex = 1;
	private static final int WIDTH = 69;
	private static final int HEIGHT = 99;
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bigplane"+i+".png");
		}
	}
	
	public Bigairplane() {
		super(WIDTH,HEIGHT);
		this.ySpeed = 3;
	}
	//�л� �ƶ�����
	public void step(){
		y += ySpeed;
		
		if(y>World.HEIGHT){
			this.state = DEAD;
		}
	}
	
	// ��л��л�ͼƬ�ķ���
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
		return 2;
	}
	
}
