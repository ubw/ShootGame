package cn.tedu.shoot;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel{
	private Sky sky;
	private Hero hero;
	private FlyingObject[] enemies = {};//ΪʲôҪѡ�������ķ�ʽ��ʼ��������������飿
	private Bullet[] bullet = {};
	
	public void action(){
		FlyingObject a1 = new Airplane();
		a1 = new Airplane();
	}
	
	//static ������ û�ж�����ֱ�ӵ��Ǿ�̬��������Ϊ��static������ʵ��������static�������෽��
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);//�������ӵ����
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����Ĭ�Ϲر�ģʽ��û�����У��رմ��ڳ��򲻽���
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null); //���ھ���
        frame.setVisible(true); //Ĭ�ϴ��ڲ��ɼ�������Ϊ�ɼ�
        
        
        world.action();
	}

}
