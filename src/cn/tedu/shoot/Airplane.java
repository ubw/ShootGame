package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//����ͼƬ����

public class Airplane extends FlyingObject{
	private int ySpeed;//�ٶ�
	private static BufferedImage[] image = new BufferedImage[5];//��������ը�٣�5��ͼ
	public static final int AIR_PLANE_WIDTH = 49;
	public static final int AIR_PLANE_HEIGHTA = 36;
	private int stateIndex = 1;
	
	//��ͼƬ��ֵ����̬��ʼ��
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/airplane"+i+".png");
		}
	}

	public Airplane() {
		super(AIR_PLANE_WIDTH, AIR_PLANE_HEIGHTA);
		this.ySpeed = 100;
	}

	//�л� �ƶ�����
	public void step(){
		y += ySpeed;
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
}
