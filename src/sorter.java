import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;



import javax.swing.JScrollPane;



public class sorter extends JFrame {
	public GUI_options optframe;
	public Dimension dim;
	private JTable table;
	public JFrame jframe;
	private JTextArea textArea=new JTextArea();
	private JTextArea textArea_2=new JTextArea();
	public List<File> Files = new ArrayList<File>();
	public List<File> Files_2 = new ArrayList<File>();
	public List<String> File_inf = new ArrayList<String>();
	public List<Integer> File_inf_2 = new ArrayList<Integer>();
	public List<String> ps = new ArrayList<String>();
	
	public sorter(GUI_options n) {
		optframe=n;
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("SNAKE");
		jframe.setVisible(false);
		
		jframe.setSize(772, 410);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("JAVA SORT");
		btnNewButton.setBounds(334, 35, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sort_j();
		        show_text();
			}
		});
		jframe.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SCALA SORT");
		
		btnNewButton_1.setBounds(334, 70, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File_inf=Scala_sort.sortInc(File_inf);
		        show_text();
			}
		});
		jframe.getContentPane().add(btnNewButton_1);
		
		
        JButton btn_back = new JButton("BACK");
		
        btn_back.setBounds(334, 240, 89, 23);
        btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optframe.jframe.show();
				textArea.setText(null);
				Files.clear();
				File_inf.clear();
				Files_2.clear();
				File_inf_2.clear();
				jframe.setVisible(false);
			}
		});
		jframe.getContentPane().add(btn_back);
		
		JButton inform_bt = new JButton("INFORMATION");
		inform_bt.setBounds(334, 105, 89, 23);
		inform_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        try {
					init_2();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
		       
			}
		});
		jframe.getContentPane().add(inform_bt);
		
		JButton psevd_bt = new JButton("PSEVD");
		psevd_bt.setBounds(334, 140, 89, 23);
		psevd_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialog2 = new JFileChooser();
		        dialog2.showOpenDialog(jframe);
        		String s;
        		try {

                    BufferedReader in = new BufferedReader(new FileReader( dialog2.getSelectedFile().getAbsoluteFile()));
                    
                    try {

                    	  while(true)
                    	  {
                    		  s=in.readLine();
                    		  if(s.compareTo("XXX")==0)
                    		  {
                    			  break;
                    		  }
                    		  ps.add(s);
                    	  }     
                      
                    } finally {

                    	
                        in.close();
                    }
                } catch(IOException t) {
                    throw new RuntimeException(t);
                }
		       show_text_psevd(psevd.parse(ps));
			}
		});
		jframe.getContentPane().add(psevd_bt);
		
		JButton btnAddFile = new JButton("ADD FILE");
		btnAddFile.setBounds(334, 208, 89, 23);
		btnAddFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        try {
					init();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        show_text();
			}
		});

		jframe.getContentPane().add(btnAddFile);
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(10, 35, 314, 295);
		jframe.getContentPane().add(scrollBar);
		jframe.getContentPane().add(btnAddFile);
		JScrollPane scrollBar_2 = new JScrollPane(textArea_2);
		scrollBar_2.setBounds(430, 35, 314, 295);
		jframe.getContentPane().add(scrollBar_2);
		
	}
	
	public void init_2() throws IOException
	{
		
		Files_2.clear();
		File_inf_2.clear();
		File folder = new File("c://saves");
		 if (!folder.exists()) {
	            throw new IOException("Cannot acsess " +"c://saves");
	        }
		File[] listOfFiles = folder.listFiles();
		 
		 for (File path:listOfFiles) {
		      Files_2.add(path);
		    }
		int i=0;
		while(i<Files_2.size())
		{
		File_inf_2.clear();	
		
		String s;
		try {

            BufferedReader in = new BufferedReader(new FileReader( Files_2.get(i).getAbsoluteFile()));
            
            try {

            	  while(true)
            	  {
            		  s=in.readLine();
            		  if(s.compareTo("XXX")==0)
            		  {
            			  break;
            		  }
            		  File_inf_2.add(Integer.parseInt(s));
            		  
            	  }     
            	  show_text_inf(Files_2.get(i).getName(),information.parse(File_inf_2));
            } finally {

            	
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
	    }
		
		i++;
		}
	}
	public void init() throws IOException
	{
		Files.clear();
		File_inf.clear();
		File folder = new File("c://saves");
		if (!folder.exists()) {
			throw new IOException("Cannot acsess " +"c://saves");
		}
		File[] listOfFiles = folder.listFiles();
		 
		 for (File path:listOfFiles) {
		      Files.add(path);
		    }
		int i=0;
		while(i<Files.size())
		{
		File_inf.add(Files.get(i).getName());
		String s;
		try {

            BufferedReader in = new BufferedReader(new FileReader( Files.get(i).getAbsoluteFile()));
            
            try {

            	  while(true)
            	  {
            		  s=in.readLine();
            		  if(s.compareTo("XXX")==0)
            		  {
            			  s=in.readLine();
            			  File_inf.add(s);
            			  break;
            		  }
            	  }     
              
            } finally {

            	
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
	    }
		i++;
		}
	}
	public void show_text()
	{
		textArea.setText(null);
		int i=0;
		for(i=0;i<File_inf.size();i=i+2)
		{
			textArea.append(File_inf.get(i)+"  SCORE:"+File_inf.get(i+1)+'\n');
			
		}
	}
	public void show_text_psevd(java.util.List<String> z)
	{
		textArea_2.setText(null);
		int i=0;
		for(i=0;i<z.size();i=i+4)
		{
			textArea_2.append(z.get(i)+'\n');
			textArea_2.append("time from beginning:"+z.get(i+1)+'\n');
			textArea_2.append("x:"+z.get(i+2)+'\n');
			textArea_2.append("y:"+z.get(i+3)+'\n');
		}
	}
	public void show_text_inf(String t,java.util.List<Integer> z)
	{
		int i=0;
		textArea_2.append(" --------------------------------- "+'\n');
		textArea_2.append("  NAME"+t+'\n');
		textArea_2.append("  DOWN"+Integer.toString(z.get(0))+'\n');
		textArea_2.append("  UP"+Integer.toString(z.get(1))+'\n');
		textArea_2.append("  LEFT"+Integer.toString(z.get(2))+'\n');
		textArea_2.append("  RIGHT"+Integer.toString(z.get(3))+'\n');
		
	}
	
	public void sort_j()
	{
		String s1;
		String s2;
		for(int i=1;i<File_inf.size()-1;i=i+2)
		{
			for(int j=File_inf.size()-1;j>i;j=j-2)
			{
				if (Integer.parseInt(File_inf.get(j-2))>Integer.parseInt(File_inf.get(j)))
				{ 
					s1=File_inf.get(j-2);
					s2=File_inf.get(j-3);
					File_inf.set(j-2, File_inf.get(j));
					File_inf.set(j-3, File_inf.get(j-1));
					File_inf.set(j, s1);
					File_inf.set(j-1, s2);
				}

			}
		}
	}
	
}
