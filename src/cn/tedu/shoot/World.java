package cn.tedu.shoot;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class World extends JPanel{
	private Sky sky = new Sky();
	private Hero hero = new Hero();
	private FlyingObject enemies[] = {};//为什么要选用这样的方式初始化，如何增加数组？
	private Bullet[] bullet = {};
	public static final int WIDTH = 400;
	public static final int HEIGHT = 700;
	
	public void action(JFrame jFrame){
		// 创建一个定时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			/* 定时器需要定时创建新的敌人，新的子弹，这些对象存到哪？感觉放到链表比较合适啊，新增就直接在后面新增，删除就删除元素。数组长度不方便变化
			 * 越界和死亡即可在下次定时任务时删除对象，怎么删
			 */
			@Override
			public void run() {
				// 天空移动
				sky.step();
				// 敌人移动
				enterAction();

				for(int i = 0; i<enemies.length; i++){
					enemies[i].step();
				}


				jFrame.repaint();
				//jFrame.setVisible(true);
				//jFrame.validate();
			}
			
		}, 200, 100);;
		
	}
	
	@Override
	public void paint(Graphics g){
		// 画天空
		sky.paintObject(g);
		hero.paintObject(g);
		for( int i = 0; i < enemies.length; i++){
			enemies[i].paintObject(g);
		}
	}
	
	public FlyingObject nextOne(){
		int i = new Random().nextInt(20);
		// 敌人的概率不同
		if(i <= 12){
			return new Airplane();
		} else if (i >=17){
			return new Bee();
		} else {
			return new Bigairplane();
		}
		
	}
	
	public void enterAction(){
		// 数组扩容
		enemies=Arrays.copyOf(enemies, enemies.length+1);
		// 添加新的一个敌人
		enemies[enemies.length -1] = nextOne();
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

}
