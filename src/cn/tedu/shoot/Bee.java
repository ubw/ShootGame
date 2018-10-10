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
			image[i] = loadImage("bee"+i+".png");
		}
	}
	
	public Bee() {
		super(60, 50);
		//this.width = 60;
		//this.height = 50;
		//this.x = (int)(Math.random()*(400-this.width));//λ�ò���,��������ɵĵ�һ�ַ���
		//this.x = new Random().nextInt(400-this.width);//��������ɵĵڶ��ַ���
		//this.y = -this.height;
		this.ySpeed = 2;
		this.xSpeed = 1;
		this.awardType = new Random().nextInt(2);//����ҿ�
	}
	
	//�۷� �ƶ�����
	public void step(){
		System.out.println("�۷� �ƶ�"+this.xSpeed+this.ySpeed);
	}
}
