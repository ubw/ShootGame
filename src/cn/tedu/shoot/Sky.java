package cn.tedu.shoot;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sky extends FlyingObject{
	private int y1;//�ڶ���ͼƬ�ֻ�
	private int ySpeed;//�ٶ�
	private static BufferedImage image;
	
	static{
		image = loadImage("../../res/background.png");
	}	
	
	public Sky() {
		super(400,700,0,0);
		//this.width = 400;//�̶�ֵ
		//this.height = 700;
		//this.x = 0;//��ֵΪ0
		//this.y = 0;
		this.ySpeed = 2;
		this.y1 = -this.height;// Ϊ-�߶�
	}

	//��� �ƶ�����
	public void step(){
		System.out.println("��� �ƶ�"+this.ySpeed);
	}
}
