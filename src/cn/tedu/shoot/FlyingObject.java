package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//����ͼƬ����
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;;

//���ܶ�������࣬��ײ���ӵ�������Ҫ�õ���̬��������Ҫʵ����
public  class FlyingObject {
	protected int width;//��
	protected int height;//��
	protected int x;//����x
	protected int y;//����y
	
	public FlyingObject(){}
	
	public FlyingObject(int width,int heitht,int x,int y){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public FlyingObject(int width,int heitht){
		this.width = width;
		this.height = height;
		this.x = new Random().nextInt(400-this.width);
		this.y = -this.height;
	}
	public  void step(){
		System.out.println("base step");
	}
	
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
		System.out.println("�����쳣�ܷ�����");
		return image;
	}
	
	//protected abstract void step(int moveX, int moveY);//��������������������ͬ�ĳ��󷽷����Ƿ������أ������Ƿ�����Ҫʵ����������Ҫ
	//static�෽��  ��̬ʱ�� ֻ�ο��������� ��������������
	protected static void foo(){
		System.out.println("base foo");
	} 
	
	//�Ǿ�̬��������̬����������ʱ����
	protected  void fooInstance(){
		System.out.println("base fooInstance ");
	} 
	
	void test(FlyingObject a){
		System.out.println("flyingobject");//Ϊʲô����ת��������õ�?���ؿ�����
		a.step();//��д������
	}
	
	void test(Airplane a){
		System.out.println("airplaneobject");
	}
}
