package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sky extends FlyingObject{
	private int y1;//�ڶ���ͼƬ�ֻ�
	private int ySpeed;//�ٶ�
	private static BufferedImage image;
	
	static{
		image = loadImage("C:/Users/cjwst/workspace/MyShoot/res/background.png");
	}	
	
	public Sky() {
		super(World.WIDTH, World.HEIGHT,0,0);
		this.ySpeed = 50;
		this.y1 = -this.height;// Ϊ-�߶�
	}

	//��� �ƶ�����
	public void step(){
		if ( y >= World.HEIGHT ){
			y = -World.HEIGHT;
			y1 = 0;
		}
		if ( y1 >= World.HEIGHT ){
			y1 = -World.HEIGHT;
			y = 0;
		}
		y += ySpeed;
		y1 += ySpeed;
	}
	
	// ����л�ͼƬ�ķ���
	public BufferedImage getImage(){
		return image;
	}
	
	// �����д����Ļ��������Ҫ������ͼ
	public void paintObject(Graphics g){
		g.drawImage(getImage(), x, y, null);
		g.drawImage(getImage(), x, y1, null);
	}
}
