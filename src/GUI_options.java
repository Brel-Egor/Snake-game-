import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUI_options {

	public int fl;
	public Dimension dim;
	public JFrame jframe;
	public GUI_main mainframe;

	public GUI_options(GUI_main n) {
		mainframe = n;
		ImageIcon icon_1 = new ImageIcon(getClass().getResource("/Zmeya-na-sero-reshetchatom-fone.(oboibox.ru).jpg"));
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(false);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height
				/ 2 - jframe.getHeight() / 2);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(null);

		JButton bt_tomain = new JButton("TO MAIN");
		bt_tomain.setBounds(235, 592, 328, 51);
		jframe.getContentPane().add(bt_tomain);
		bt_tomain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainframe.jframe.show();
				jframe.setVisible(false);
			}
		});

		JButton bt_laf = new JButton("CHANGE_STYLE");
		bt_laf.setBounds(235, 492, 328, 50);
		jframe.getContentPane().add(bt_laf);
		bt_laf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fl == 0) {
					try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						
						e1.printStackTrace();
					} catch (UnsupportedLookAndFeelException e1) {
						
						e1.printStackTrace();
					}
					fl=1;
					SwingUtilities.updateComponentTreeUI(jframe);
					SwingUtilities.updateComponentTreeUI(mainframe.jframe);
				
					SwingUtilities.updateComponentTreeUI(mainframe.snake.jframe);
					return;
				}
				if (fl == 1) {
					try {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
					
						e1.printStackTrace();
					} catch (UnsupportedLookAndFeelException e1) {
						
						e1.printStackTrace();
					}
					fl=0;
					SwingUtilities.updateComponentTreeUI(jframe);
					SwingUtilities.updateComponentTreeUI(mainframe.jframe);
					
					SwingUtilities.updateComponentTreeUI(mainframe.snake.jframe);
					return;
				}
			}
		});
		JButton btnNewButton = new JButton("Sorter");
		btnNewButton.setBounds(235, 268, 328, 58);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainframe.sorter.jframe.show();
				jframe.setVisible(false);
			}
		});
		jframe.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel();
		label.setBounds(0, 0, 900, 700);
		label.setIcon(new ImageIcon(icon_1.getImage().getScaledInstance(850, 700, icon_1.getImage().SCALE_DEFAULT)));
		jframe.getContentPane().add(label);
		
		

	}
}
