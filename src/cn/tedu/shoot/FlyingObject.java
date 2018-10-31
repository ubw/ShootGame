package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//����ͼƬ����
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;

//���ܶ�������࣬��ײ���ӵ�������Ҫ�õ���̬��������Ҫʵ����
public  abstract class FlyingObject {
	protected int width;//��
	protected int height;//��
	protected int x;//����x
	protected int y;//����y
	// �����������״̬
	public static final int ALIVE = 0;
	public static final int DEAD = 1;
	public static final int DELETE = 2;
	// ������ĵ�ǰ״̬
	protected int state = ALIVE;
	
	public FlyingObject(){}
	
	public FlyingObject(int width,int height,int x,int y){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public FlyingObject(int width,int height){
		this.width = width;
		this.height = height;
		this.x = new Random().nextInt(World.WIDTH-this.width);
		this.y = -this.height;
	}
	public  abstract void step();
	
	//����ͼƬ�Ͷ����޹أ�������static
	public static BufferedImage loadImage(String filepath){
		BufferedImage image = null;
		try {
			image = ImageIO.read( new FileInputStream(filepath)); 
			//image = ImageIO.read(FlyingObject.class.getResource(filepath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return image;
	}
	
	// ͼƬ�л�����������ʵ�־����Ч��
	public abstract BufferedImage getImage();
	
	// ���״̬����������
	public boolean checkAlive(){
		return state==ALIVE;
	}
	
	public boolean checkDead(){
		return state==DEAD;
	}
	
	public boolean checkDelete(){
		return state==DELETE;
	}
	
	// ������
	public void paintObject(Graphics g){
		g.drawImage(getImage(), x, y, null);
	}
	
	public boolean hit(FlyingObject enemis){
		if (x + width < enemis.x || x > enemis.x + enemis.width) {
			return false;
		} else {
			if (y > enemis.y + enemis.height || y + height < enemis.y) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public void goToDeath(){
		this.state = DEAD;
	}
	
}
