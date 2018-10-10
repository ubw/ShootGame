package cn.tedu.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;//加载图片的类
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;;

//不能定义抽象类，碰撞和子弹动作需要用到多态，父类需要实例化
public  class FlyingObject {
	protected int width;//宽
	protected int height;//长
	protected int x;//坐标x
	protected int y;//坐标y
	
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
		System.out.println("测试异常能否到这里");
		return image;
	}
	
	//protected abstract void step(int moveX, int moveY);//抽象类有两个方法名相同的抽象方法，是否算重载？子类是否是需要实现两个？需要
	//static类方法  多态时候 只参考编译类型 不考虑运行类型
	protected static void foo(){
		System.out.println("base foo");
	} 
	
	//非静态方法，多态，考虑运行时类型
	protected  void fooInstance(){
		System.out.println("base fooInstance ");
	} 
	
	void test(FlyingObject a){
		System.out.println("flyingobject");//为什么向上转型这里会用到?重载看参数
		a.step();//重写看对象
	}
	
	void test(Airplane a){
		System.out.println("airplaneobject");
	}
}
