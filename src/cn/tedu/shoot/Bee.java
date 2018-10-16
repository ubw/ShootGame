package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bee extends FlyingObject{
	private int ySpeed;//�ٶ�
	private int xSpeed;//�ٶ�
	private int awardType;//��������(0:��  1:����)
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
		this.awardType = new Random().nextInt(2);//����ҿ�
	}
	
	//�۷� �ƶ�����
	public void step(){
		y += ySpeed;
		x += awardType == 0 ? 1 : -1;
		System.out.println("�۷� �ƶ�"+this.xSpeed+this.ySpeed);
	}
	
	// �۷��л�ͼƬ
	public BufferedImage getImage(){
		if (checkAlive()){
			return image[0];
		} else if (checkDead()){
			state = DELETE;
		}
		return null;
	}
}
