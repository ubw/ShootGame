package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private int ySpeed;//�ٶ�
	private static BufferedImage image;
	
	static{
		image = loadImage("img_ariplane1.png");
	}
	
	public Bullet( int x, int y) {
		super(8,14,x,y);
		//this.width = 8;
		//this.height = 14;
		//this.x = x;//��Ӣ�ۻ���λ�ñ仯
		//this.y = y;
		this.ySpeed = 4;
	}

	//�ӵ� �ƶ�����
	public void step(){
		System.out.println("�ӵ�y���� �ƶ�"+this.ySpeed);
	}
}
