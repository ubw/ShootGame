package cn.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Hero extends FlyingObject{
	private int lives = 3;
	//private long score;
	private static BufferedImage[] image = new BufferedImage[6];
	private int doublefile;//�Ƿ�˫������
	private int stateIndex = 0;//״̬�׶�
	public static final int WIDTH = 97;
	public static final int HEIGHT = 124;
	
	static{
		for(int i = 0; i<image.length;i++){
			image[i] = loadImage("C:/Users/cjwst/workspace/MyShoot/res/hero"+i+".png");
		}
	}	

	public Hero() {
		super(97,124,140,400);
		this.lives = 3;
		//this.width = 97;//ͼƬ���ؿ�
		//this.height = 124;
		//this.x = 140;//��ʼ����
		//this.y = 400;
		this.doublefile = 40;//��ʼ����Ϊ0
	}

	//Ӣ�ۻ� �ƶ�����
	//���س������step����
	public void step(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub		
	}
	
	// Ӣ�ۻ��л�ͼƬ
	@Override
	public BufferedImage getImage(){
		if (checkAlive()){
			stateIndex = (stateIndex == 0)?1:0;
			return image[stateIndex];
		} else if (checkDead()){
			if(stateIndex == 0 || stateIndex == 1){
				stateIndex = 2;
				return image[stateIndex];
			} else {
				if(stateIndex == image.length){
					state = DELETE;
				} else {
					return image[stateIndex++];
				}
			}
		}
		
		return null;
	}	
	
	public Bullet[] loadBullet(){
		// ����µ�һ���ӵ�
		if (doublefile < 0){
			return new Bullet[]{
					new Bullet(x+WIDTH/2-Bullet.WIDTH/2, y - Bullet.HEIGHT)
			};
		} else {
			doublefile -= 2;
			return new Bullet[]{
					new Bullet(x+WIDTH/4-Bullet.WIDTH/2, y - Bullet.HEIGHT),
					new Bullet(x+3*WIDTH/4 - Bullet.WIDTH/2, y - Bullet.HEIGHT)
			};
		}
	}
	
	public void addLives(){
		lives++;
	}
	
	public void minusLives(){
		lives--;
	}
	
	public void addBullets(){
		doublefile += 10;
	}
	
	public int getLives(){
		return lives;
	}
	
	public void restartSetLives() {
		lives = 3;
	}

}
