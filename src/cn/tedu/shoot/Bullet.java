package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private int ySpeed;//�ٶ�
	private static BufferedImage image;
	public static final int BULLET_WIDTH = 8;
	public static final int BULLET_HEIGHT = 14;
	
	static{
		image = loadImage("C:/Users/cjwst/workspace/MyShoot/res/bullet.png");
	}
	
	public Bullet( int x, int y) {
		super(BULLET_WIDTH,BULLET_HEIGHT,x,y);
		this.ySpeed = 4;
	}

	//�ӵ� �ƶ�����
	public void step(){
		y = y-ySpeed;
		System.out.println("�ӵ�y���� �ƶ�"+this.ySpeed);
	}
	
	// �ӵ��л�ͼƬ
	public BufferedImage getImage(){
		if (checkAlive()){
			return image;
		} else if (checkDead()){
			state = DELETE;
		}
		return null;
	}
}
