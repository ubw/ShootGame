package cn.tedu.shoot;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel{
	private Sky sky;
	private Hero hero;
	private FlyingObject[] enemies = {};//为什么要选用这样的方式初始化，如何增加数组？
	private Bullet[] bullet = {};
	
	public void action(){
		FlyingObject a1 = new Airplane();
		a1 = new Airplane();
	}
	
	//static 方法中 没有对象不能直接掉非静态方法，因为非static方法是实例方法，static方法是类方法
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        frame.add(world);//将面板添加到相框
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭模式，没有这行，关闭窗口程序不结束
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null); //窗口居中
        frame.setVisible(true); //默认窗口不可见，设置为可见
        
        
        world.action();
	}

}
