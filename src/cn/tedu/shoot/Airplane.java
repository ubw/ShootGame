package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//����ͼƬ����

public class Airplane extends FlyingObject{
	private int ySpeed;//�ٶ�
	private static BufferedImage[] image = new BufferedImage[5];//��������ը�٣�5��ͼ
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("ariplane"+i+".png");
		}
	}
	
	{
		System.out.println("�Ǿ�̬��ʼ����");
	}

	public Airplane() {
		super(49, 36);
		//this.width = 49;//oop1��ʦд���ǣ����д��дsuper��������̳и���ʱ���Ƿ�����·����ڴ�ռ仹���õ��Ǹ���ģ�
		//this.height = 36;
		//this.x = (int)(Math.random()*(400-this.width));//λ�ò���,��������ɵĵ�һ�ַ���
		//this.x = new Random().nextInt(400-this.width);//��������ɵĵڶ��ַ���
		//this.y = -this.height;
		this.ySpeed = 2;
	}

	//�л� �ƶ�����
	public void step(){
		System.out.println("�л� �ƶ�"+this.ySpeed);
	}
	
	public static void foo(){
		System.out.println("ariplane foo");
	}
	
	public  void fooInstance(){
		System.out.println("ariplane fooInstance");
	}

}
