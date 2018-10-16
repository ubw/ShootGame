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
	private FlyingObject enemies[] = {};//ΪʲôҪѡ�������ķ�ʽ��ʼ��������������飿
	private Bullet[] bullet = {};
	public static final int WIDTH = 400;
	public static final int HEIGHT = 700;
	
	public void action(JFrame jFrame){
		// ����һ����ʱ��
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			/* ��ʱ����Ҫ��ʱ�����µĵ��ˣ��µ��ӵ�����Щ����浽�ģ��о��ŵ�����ȽϺ��ʰ���������ֱ���ں���������ɾ����ɾ��Ԫ�ء����鳤�Ȳ�����仯
			 * Խ��������������´ζ�ʱ����ʱɾ��������ôɾ
			 */
			@Override
			public void run() {
				// ����ƶ�
				sky.step();
				// �����ƶ�
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
		// �����
		sky.paintObject(g);
		hero.paintObject(g);
		for( int i = 0; i < enemies.length; i++){
			enemies[i].paintObject(g);
		}
	}
	
	public FlyingObject nextOne(){
		int i = new Random().nextInt(20);
		// ���˵ĸ��ʲ�ͬ
		if(i <= 12){
			return new Airplane();
		} else if (i >=17){
			return new Bee();
		} else {
			return new Bigairplane();
		}
		
	}
	
	public void enterAction(){
		// ��������
		enemies=Arrays.copyOf(enemies, enemies.length+1);
		// ����µ�һ������
		enemies[enemies.length -1] = nextOne();
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

}
