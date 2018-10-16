package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sky extends FlyingObject{
	private int y1;//第二张图片轮换
	private int ySpeed;//速度
	private static BufferedImage image;
	
	static{
		image = loadImage("C:/Users/cjwst/workspace/MyShoot/res/background.png");
	}	
	
	public Sky() {
		super(World.WIDTH, World.HEIGHT,0,0);
		this.ySpeed = 50;
		this.y1 = -this.height;// 为-高度
	}

	//天空 移动方法
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
	
	// 天空切换图片的方法
	public BufferedImage getImage(){
		return image;
	}
	
	// 天空重写父类的画对象，天空要画两张图
	public void paintObject(Graphics g){
		g.drawImage(getImage(), x, y, null);
		g.drawImage(getImage(), x, y1, null);
	}
}
