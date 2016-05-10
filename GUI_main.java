
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;








import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;




public class GUI_main {
	
	public Snake snake;
	
	public sorter sorter;
	
	public GUI_options optionsframe;
	
	public JFrame jframe;

	public Dimension dim;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_main frame = new GUI_main();
					frame.optionsframe = new GUI_options(frame);
					frame.snake= new Snake(frame.jframe);
					frame.sorter=new sorter(frame.optionsframe);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_main() {
		ImageIcon icon_1 = new ImageIcon(getClass().getResource("/Zmeya-na-sero-reshetchatom-fone.(oboibox.ru).jpg"));
		ImageIcon icon_2 = new ImageIcon(getClass().getResource("/label_snake.jpg"));
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(null);
		
		JButton bt_start = new JButton("START");
		bt_start.setBounds(251, 118, 296, 60);
		jframe.getContentPane().add(bt_start);
		bt_start.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	snake.sim=0;
            	JFileChooser dialog2 = new JFileChooser();
        		dialog2.showSaveDialog(jframe);
        		snake.setSaveFile(dialog2.getSelectedFile());
        		jframe.setVisible(false);
            	snake.startGame(); 
           
            }
        });
		
		JButton bt_record = new JButton("LOAD");
		bt_record.setBounds(251, 268, 296, 60);
		jframe.getContentPane().add(bt_record);
        bt_record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				snake.sim=1;
				JFileChooser dialog1 = new JFileChooser();
		        dialog1.showOpenDialog(jframe);
		        snake.setReadFile(dialog1.getSelectedFile());
		        jframe.setVisible(false);
        		snake.startGame();
			}
		});
		
		JButton bt_options = new JButton("OPTIONS");
		bt_options.setBounds(251, 420, 296, 60);
		jframe.getContentPane().add(bt_options);
		bt_options.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	optionsframe.jframe.show();
                jframe.setVisible(false);  
            }
        });
		
		JButton bt_exit = new JButton("EXIT");
		bt_exit.setBounds(251, 570, 296, 60);
		jframe.getContentPane().add(bt_exit);
		
		bt_exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	System.exit(0);   
            }
        });
		
		JRadioButton btnPlayer = new JRadioButton("PLAYER",false);
		btnPlayer.setBounds(638, 118, 109, 23);
		jframe.getContentPane().add(btnPlayer);
		
		JRadioButton btnComputer = new JRadioButton("COMPUTER",true);
		btnComputer.setBounds(638, 155, 109, 23);
		jframe.getContentPane().add(btnComputer);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnPlayer);
		group.add(btnComputer);
		
		btnPlayer.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	snake.is_man=1;     
            }
        });
		
		btnComputer.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	snake.is_man=0;   
            }
        });
		
		JRadioButton rdbtnLowSpeed = new JRadioButton("LOW SPEED",false);
		rdbtnLowSpeed.setBounds(638, 268, 109, 23);
		jframe.getContentPane().add(rdbtnLowSpeed);
		
		JRadioButton rdbtnTopSpeed = new JRadioButton("TOP SPEED",true);
		rdbtnTopSpeed.setBounds(638, 305, 109, 23);
		jframe.getContentPane().add(rdbtnTopSpeed);
		
		ButtonGroup group_2 = new ButtonGroup();
		group_2.add(rdbtnLowSpeed);
		group_2.add(rdbtnTopSpeed);
		
		rdbtnLowSpeed.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	
            	snake.speed=30;
            }
        });
		
		rdbtnTopSpeed.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	 
            	snake.speed=10;//10
            }
        });
		
		JLabel lb_name = new JLabel();
		lb_name.setBounds(251, 0, 296, 67);
		icon_2.getImage();
		lb_name.setIcon(new ImageIcon(icon_2.getImage().getScaledInstance(296, 67, Image.SCALE_DEFAULT)));
		jframe.getContentPane().add(lb_name);
		
		JLabel label = new JLabel();
		label.setBounds(0, 0, 900, 700);
		icon_1.getImage();
		label.setIcon(new ImageIcon(icon_1.getImage().getScaledInstance(850, 700, Image.SCALE_DEFAULT)));
		jframe.getContentPane().add(label);
		
		
		
		
		
	}
}
