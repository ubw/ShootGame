package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//����ͼƬ����

public class Airplane extends FlyingObject implements Enemy{
	private int ySpeed;//�ٶ�
	private static BufferedImage[] image = new BufferedImage[5];//��������ը�٣�5��ͼ
	public static final int WIDTH = 49;
	public static final int HEIGHTA = 36;
	private int stateIndex = 1;
	
	//��ͼƬ��ֵ����̬��ʼ��
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/airplane"+i+".png");
		}
	}

	public Airplane() {
		super(WIDTH, HEIGHTA);
		this.ySpeed = 5;
	}

	//�л� �ƶ�����
	public void step(){
		y += ySpeed;
		if(y>World.HEIGHT){
			this.state = DEAD;
		}
	}
	
	// �л�ͼƬ�ķ���
	public BufferedImage getImage(){
		if (checkAlive()){
			return image[0];
		} else if (checkDead()) {
			if (stateIndex != image.length){
				return image[stateIndex++];	
			} else {
				state = DELETE;
			}
		}
		return null;
	}
	
	public int getScore(){
		return 1;
	}

}
