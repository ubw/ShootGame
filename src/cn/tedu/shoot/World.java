package cn.tedu.shoot;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class World extends JPanel {
	private Sky sky = new Sky();
	private  Hero hero = new Hero();
	private FlyingObject enemies[] = {};//为什么要选用这样的方式初始化，如何增加数组？
	private Bullet[] bullet = {};
	public static final int WIDTH = 400;
	public static final int HEIGHT = 700;
	private static long enemiesFrequency = 20;
	private long FrequencyCnt = 0;
	private static long bulletFrequency = 30;
	private int score = 0;
	private static BufferedImage[] image = new BufferedImage[3];
	private int state = READY;
	private static final int READY = 0;
	private static final int START = 1;
	private static final int PAUSE = 2;
	private static final int END = 3;
	
	static{
		image[0] = FlyingObject.loadImage("C:/Users/cjwst/workspace/MyShoot/res/start.png");
		image[1] = FlyingObject.loadImage("C:/Users/cjwst/workspace/MyShoot/res/gameover.png");
		image[2] = FlyingObject.loadImage("C:/Users/cjwst/workspace/MyShoot/res/pause.png");
	}
	
	public void action(JFrame jFrame){
		
        /* 1.需要有一个侦听器对象 2.需要将侦听器装载到面板中
        * MouseAdapter 是一个抽象类，因为仅需一个侦听器，所以需要创建一个匿名内部类
        */
        MouseAdapter foo = new MouseAdapter(){
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		if (state == START) {
        			hero.step(e.getX()-Hero.WIDTH/2, e.getY()-Hero.HEIGHT/2);
        		}
        	}
        	
        	@Override
        	public void mouseClicked(MouseEvent e){
        		
        		if (state == READY ){
        			state = START;
        		} else if (state == END){
        			restart();
        		}
        	}
        	
        	public void mouseEntered(MouseEvent e){
        		if (state == PAUSE) {
        			state = START;
        		}
        	}
        	
        	public void mouseExited(MouseEvent e){
        		if (state == START) {
        			state = PAUSE;
        		}
        	}
        };
        this.addMouseMotionListener(foo);
        this.addMouseListener(foo);
        
		// 创建一个定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			/* 定时器需要定时创建新的敌人，新的子弹，这些对象存到哪？感觉放到链表比较合适啊，新增就直接在后面新增，删除就删除元素。数组长度不方便变化
			 * 越界和死亡即可在下次定时任务时删除对象，怎么删
			 */
			@Override
			public void run() {
				if( state == START && !hero.checkDelete()){
					// 入场
					enterAction();
					// 飞行物移动
					stepAction();
					// 删除不需要的对象
					deleteAction();
					// 判断是否碰撞
					judgeAction();
				}
				
				jFrame.repaint();
			}
			
		}, 200, 10);;
		
	}
	
	@Override
	public void paint(Graphics g){
		// 画天空
		sky.paintObject(g);
		hero.paintObject(g);
		if (state == READY){
			g.drawImage(image[0], 0, 0, null);
			return;
		}
		for( int i = 0; i < enemies.length; i++){
			enemies[i].paintObject(g);
		}
		for( int i = 0; i < bullet.length; i++){
			bullet[i].paintObject(g);
		}
		
		if (state == PAUSE){
			g.drawImage(image[2], 0, 0, null);
			return;
		}
		
		g.drawString("lives : " + hero.getLives(), 10, 10);
		g.drawString("score : " + score, 10, 20);
		
		if (state == END) {
			g.drawImage(image[1], 0, 0, null);
		}
	}
	
	public FlyingObject nextOne(){
		int i = new Random().nextInt(20);
		// 敌人的概率不同
		if(i <= 12){
			return new Airplane();
		} else if (i >=16){
			return new Bee();
		} else {
			return new Bigairplane();
		}
		
	}
	
	public void enterAction(){
		//敌人入场
		if((FrequencyCnt++)%enemiesFrequency == 0){
			// 数组扩容
			enemies=Arrays.copyOf(enemies, enemies.length+1);
			// 添加新的一个敌人
			enemies[enemies.length -1] = nextOne();
		}
		
		//子弹入场
		if((FrequencyCnt)%bulletFrequency == 0){

			Bullet[] nextbullet2 = hero.loadBullet();
			// System.arraycopy 写法两个数组合并，需要先扩容，再复制到后面去
			bullet=Arrays.copyOf(bullet, bullet.length+nextbullet2.length);
			System.arraycopy(nextbullet2, 0, bullet, bullet.length-nextbullet2.length, nextbullet2.length);
			
		}
	}
	
	public void stepAction(){
		// 天空移动
		sky.step();
		// 敌人移动
		System.out.println("enemies length: " + enemies.length);
		for( int i = 0; i<enemies.length; i++){
			if (i<2)
			{
				
			}
			if(enemies[i].checkAlive()){
				enemies[i].step();
			}
		}
		// 子弹动
		System.out.println("bullet length: " + bullet.length);
		for( int i = 0; i<bullet.length; i++){
			if(bullet[i].checkAlive()){
				bullet[i].step();
			}
		}
	}
	
	public void deleteAction(){
		FlyingObject[] tmp = new FlyingObject[enemies.length];
		int newLength = 0;
		
		for(int i = 0; i<enemies.length; i++){
			if(enemies[i].checkDelete()){
				continue;
			}
			tmp[newLength++] = enemies[i];
		}
		
		enemies = Arrays.copyOf(tmp, newLength);		
		
		Bullet[] tmp2 = new Bullet[bullet.length];
		int newLength2 = 0;
		
		for(int i = 0; i<bullet.length; i++){
			if(bullet[i].checkDelete()){
				continue;
			}
			tmp2[newLength2++] = bullet[i];
		}
		
		bullet = Arrays.copyOf(tmp2, newLength2);
	}
	
	public void judgeAction(){
		// 子弹和敌人碰撞
		for(int i = 0; i < bullet.length; i++){
			for(int j = 0; j< enemies.length; j++){
				if(bullet[i].hit(enemies[j])){
					
					if (bullet[i].checkAlive() && enemies[j].checkAlive()) {
						// 死亡
						enemies[j].goToDeath();
						bullet[i].goToDeath();
						
						// 如果是敌人加分
						if (enemies[j] instanceof Enemy) {
							Enemy enemy = (Enemy)enemies[j];
							score += enemy.getScore();
						}
						// 如果是蜜蜂加命或者子弹
						if (enemies[j] instanceof Award) {
							Award award = (Award)enemies[j];
							if (award.getAward() == 0) {
								hero.addLives();
							} else {
								hero.addBullets();
							}
						}
					}
				}				
			}
		}
		
		// 英雄机和敌机的碰撞
		for (int i = 0; i < enemies.length ; i++){
			if (enemies[i].checkAlive() && enemies[i].hit(hero)){
				enemies[i].goToDeath();
				hero.minusLives();
				if (hero.getLives() <= 0){
					hero.goToDeath();
					state = END;
				}
			}
		}
	}
	
	//static 方法中 没有对象不能直接掉非静态方法，因为非static方法是实例方法，static方法是类方法
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);//将面板添加到相框
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭模式，没有这行，关闭窗口程序不结束
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null); //窗口居中
        frame.setVisible(true); //默认窗口不可见，（1）设置为可见（2）尽快调用paint();超类有默认的
             
        world.action(frame);//paint是异步执行，paint可能回合action同时进行，造成空指针的问题
	}

	public void restart(){
		hero = new Hero();
		hero.restartSetLives();
		score = 0;
		for (int i = 0; i<enemies.length; i++){
			enemies[i].state = FlyingObject.DELETE;
		}
		
		for (int i = 0; i<bullet.length; i++){
			bullet[i].state = FlyingObject.DELETE;
		}
		state = START;
	}
}
