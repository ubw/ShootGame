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
	private FlyingObject enemies[] = {};//ΪʲôҪѡ�������ķ�ʽ��ʼ��������������飿
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
		
        /* 1.��Ҫ��һ������������ 2.��Ҫ��������װ�ص������
        * MouseAdapter ��һ�������࣬��Ϊ����һ����������������Ҫ����һ�������ڲ���
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
        
		// ����һ����ʱ��
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			/* ��ʱ����Ҫ��ʱ�����µĵ��ˣ��µ��ӵ�����Щ����浽�ģ��о��ŵ�����ȽϺ��ʰ���������ֱ���ں���������ɾ����ɾ��Ԫ�ء����鳤�Ȳ�����仯
			 * Խ��������������´ζ�ʱ����ʱɾ��������ôɾ
			 */
			@Override
			public void run() {
				if( state == START && !hero.checkDelete()){
					// �볡
					enterAction();
					// �������ƶ�
					stepAction();
					// ɾ������Ҫ�Ķ���
					deleteAction();
					// �ж��Ƿ���ײ
					judgeAction();
				}
				
				jFrame.repaint();
			}
			
		}, 200, 10);;
		
	}
	
	@Override
	public void paint(Graphics g){
		// �����
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
		// ���˵ĸ��ʲ�ͬ
		if(i <= 12){
			return new Airplane();
		} else if (i >=16){
			return new Bee();
		} else {
			return new Bigairplane();
		}
		
	}
	
	public void enterAction(){
		//�����볡
		if((FrequencyCnt++)%enemiesFrequency == 0){
			// ��������
			enemies=Arrays.copyOf(enemies, enemies.length+1);
			// ����µ�һ������
			enemies[enemies.length -1] = nextOne();
		}
		
		//�ӵ��볡
		if((FrequencyCnt)%bulletFrequency == 0){

			Bullet[] nextbullet2 = hero.loadBullet();
			// System.arraycopy д����������ϲ�����Ҫ�����ݣ��ٸ��Ƶ�����ȥ
			bullet=Arrays.copyOf(bullet, bullet.length+nextbullet2.length);
			System.arraycopy(nextbullet2, 0, bullet, bullet.length-nextbullet2.length, nextbullet2.length);
			
		}
	}
	
	public void stepAction(){
		// ����ƶ�
		sky.step();
		// �����ƶ�
		System.out.println("enemies length: " + enemies.length);
		for( int i = 0; i<enemies.length; i++){
			if (i<2)
			{
				
			}
			if(enemies[i].checkAlive()){
				enemies[i].step();
			}
		}
		// �ӵ���
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
		// �ӵ��͵�����ײ
		for(int i = 0; i < bullet.length; i++){
			for(int j = 0; j< enemies.length; j++){
				if(bullet[i].hit(enemies[j])){
					
					if (bullet[i].checkAlive() && enemies[j].checkAlive()) {
						// ����
						enemies[j].goToDeath();
						bullet[i].goToDeath();
						
						// ����ǵ��˼ӷ�
						if (enemies[j] instanceof Enemy) {
							Enemy enemy = (Enemy)enemies[j];
							score += enemy.getScore();
						}
						// ������۷���������ӵ�
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
		
		// Ӣ�ۻ��͵л�����ײ
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
	
	//static ������ û�ж�����ֱ�ӵ��Ǿ�̬��������Ϊ��static������ʵ��������static�������෽��
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);//�������ӵ����
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����Ĭ�Ϲر�ģʽ��û�����У��رմ��ڳ��򲻽���
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null); //���ھ���
        frame.setVisible(true); //Ĭ�ϴ��ڲ��ɼ�����1������Ϊ�ɼ���2���������paint();������Ĭ�ϵ�
             
        world.action(frame);//paint���첽ִ�У�paint���ܻغ�actionͬʱ���У���ɿ�ָ�������
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
