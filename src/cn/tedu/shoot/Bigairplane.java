package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;

public class Bigairplane extends FlyingObject{
	private int ySpeed;//�ٶ�
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
		//this.x = (int)(Math.random()*(400-this.width));//λ�ò���,��������ɵĵ�һ�ַ���
		//this.x = new Random().nextInt(400-this.width);//��������ɵĵڶ��ַ���
		//this.y = -this.height;
		this.ySpeed = 2;
	}
	//�л� �ƶ�����
	public void step(){
		System.out.println("��л� �ƶ�"+this.ySpeed);
	}
}
