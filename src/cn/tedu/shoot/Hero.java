package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
	private int lives;
	//private long score;
	private static BufferedImage[] image = new BufferedImage[6];
	private int doublefile;//�Ƿ�˫������
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("hero"+i+".png");
		}
	}	

	public Hero() {
		super(97,124,140,400);
		this.lives = 3;
		//this.width = 97;//ͼƬ���ؿ�
		//this.height = 124;
		//this.x = 140;//��ʼ����
		//this.y = 400;
		this.doublefile = 0;//��ʼ����Ϊ0
	}

	//Ӣ�ۻ� �ƶ�����
	//���س������step����
	public void step(int moveX, int moveY){
		System.out.println("Ӣ�ۻ� �ƶ�");
	}
	
	//Ӣ�ۼ��л�ͼƬ
	public void change(){
		System.out.println("Ӣ�ۼ� ��ͼƬ �Ӿ��ϵĶ�̬");
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub		
	}
}
