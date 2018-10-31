package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//加载图片的类
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;

//不能定义抽象类，碰撞和子弹动作需要用到多态，父类需要实例化
public  abstract class FlyingObject {
	protected int width;//宽
	protected int height;//长
	protected int x;//坐标x
	protected int y;//坐标y
	// 飞行物的三种状态
	public static final int ALIVE = 0;
	public static final int DEAD = 1;
	public static final int DELETE = 2;
	// 飞行物的当前状态
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
	
	//加载图片和对象无关？所以用static
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
	
	// 图片切换函数，子类实现具体的效果
	public abstract BufferedImage getImage();
	
	// 检查状态的三个方法
	public boolean checkAlive(){
		return state==ALIVE;
	}
	
	public boolean checkDead(){
		return state==DEAD;
	}
	
	public boolean checkDelete(){
		return state==DELETE;
	}
	
	// 画对象
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
