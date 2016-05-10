

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;



 
public class Snake implements ActionListener, KeyListener
{
	public int dir;
	public int time;
	public int ch_x;
	public int ch_y;
	public int syn;
	public int i=0,j=1,k=2,z=3;
	private File savefile;
	
	private File loadfile;
	
	public Thread client;
	
	public Thread server;
	
	public int sim=0;
	
    public int speed=10;
    
    public int is_man=0;
    
    public JTextField textField;
    
    public JTextField textField_1;
	
	public JFrame main;
	
	public JFrame jframe;

	public RenderPanel renderPanel;



	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	public ArrayList<Integer> simulate = new ArrayList<Integer>();

	public static  int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

	public int ticks = 0, direction = DOWN, score, tailLength = 10;

	public Point head, cherry;

	public Random random;

	public boolean over = false, paused;

	public Dimension dim;

	public Snake(JFrame e)
	{
		main=e;
		renderPanel = new RenderPanel(this);
		renderPanel.setLayout(null);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(false);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel);
		textField = new JTextField();
		textField.setBounds(641, 52, 138, 20);
		textField.setEnabled(false);
		renderPanel.add(textField);
		textField_1 = new JTextField();
		textField_1.setBounds(641, 115, 138, 20);
		textField_1.setEnabled(false);
		renderPanel.add(textField_1);
		JButton bt_exit = new JButton("TO MAIN");
		bt_exit.setBounds(641, 232, 89, 23);
		bt_exit.addKeyListener(renderPanel);
        bt_exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	if(over==false)
            	{
            		over=true;
            		
            		
            		main.show();
            		jframe.setVisible(false);
            	}
            	if(over==true)
            	{
            		
            		main.show();
            		jframe.setVisible(false);
            	}
            }
        });
		renderPanel.add(bt_exit);
	
        
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}

	public void startGame()
	{
		server = new Thread(new Runnable()
	    {
	        public void run() 
	        {
	            game();
	        }
	    });
		client = new Thread(new Runnable()
	    {
	        public /*synchronized*/ void run() 
	        {
		        	while(true)
		        	{
		        		
			        		if(syn==1)
			        		{
			        			renderPanel.repaint();
			        			syn=0;
			        		}
			        		if(syn==2)
			        		{
			        			renderPanel.repaint();
			        			break;
			        		}
			        }
	        }
	    });
		i=0;j=1;k=2;z=3;
		syn=0;
		over = false;
		paused = false;
		score = 0;
		tailLength = 1;
		ticks = 0;
		direction = DOWN;
		head = new Point(1, 1);
		random = new Random();
		snakeParts.clear();
		simulate.clear();
		cherry = new Point(random.nextInt(59), random.nextInt(66));
		jframe.setVisible(true);
		if(sim==0)
		{
			this.saveToFile();
		}
		if(sim==1)
		{
			this.simulateFromFile();
			direction=simulate.get(i);
			cherry.x=simulate.get(k);
			cherry.y=simulate.get(z);
			
		}
		server.start();
		client.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		
	}
	
	public /*synchronized*/ void game() 
	{
		int fl=0;
		
		while(true)
		{
			try {
				server.sleep(speed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}	
			syn=1;
			try {
				client.join(1);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			ticks++;
			if(sim==1 && z<=simulate.size()-1)
			{
				if(simulate.get(j)+1==ticks)
				{
				
					direction=simulate.get(i);
					cherry.x=simulate.get(k);
					cherry.y=simulate.get(z);
					i=i+4;
					j=j+4;
					k=k+4;
					z=z+4;
				
				}
			}
			if(is_man==0 && sim==0)
			{
				intellect();
			}
			
			if (ticks % 2 == 0 && head != null && !over && !paused)
			{
				

				snakeParts.add(new Point(head.x, head.y));

				if (direction == UP)
				{
					if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
					{
						head = new Point(head.x, head.y - 1);
					}
					else
					{
						over = true;
						if(sim==0)
						{
							endwritegame();
						}
					}
				}

				if (direction == DOWN)
				{
					if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
					{
						head = new Point(head.x, head.y + 1);
					}
					else
					{
						over = true;
						if(sim==0)
						{
							endwritegame();
						}
					}
				}

				if (direction == LEFT)
				{
					if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
					{
						head = new Point(head.x - 1, head.y);
					}
					else
					{
						over = true;
						if(sim==0)
						{
							endwritegame();
						}
					}
				}

				if (direction == RIGHT)
				{
					if (head.x + 1 < 60 && noTailAt(head.x + 1, head.y))
					{
						head = new Point(head.x + 1, head.y);
					}
					else
					{
						over = true;
						if(sim==0)
						{
							endwritegame();
						}
					}
				}

				if (snakeParts.size() > tailLength)
				{
					snakeParts.remove(0);

				}

				if (cherry != null)
				{
					if (head.equals(cherry))
					{
						score += 10;
						tailLength++;
						if(sim==0)
						{
							cherry.setLocation(random.nextInt(59), random.nextInt(66));
							this.saveToFile();
						}
					}
				}
			}
			if(over == true)
			{
				syn=2;
				if(fl==1)
				{
					break;
				}
				fl=1;
			}
		}
	}
	public void setSaveFile(File f)
	{
		savefile=f;
	}
	
	public void setReadFile(File f)
	{
		loadfile=f;
	}
	
	public boolean noTailAt(int x, int y)
	{
		
		for (int i=0;i<snakeParts.size();i++)
		{
			Point point=snakeParts.get(i);
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	
	public void intellect()
	{
		if(head.x<cherry.x && direction!=LEFT)
		{
			direction=RIGHT;
			if(sim==0 && dir!=direction)
			{
				this.saveToFile();
			}
		}
		if(head.x==cherry.x && head.y>cherry.y && direction!=DOWN)
		{
			direction=UP;
			if(sim==0 && dir!=direction)
			{
				this.saveToFile();
			}
		}
		if(head.x==cherry.x && head.y<cherry.y && direction!=UP)
		{
			direction=DOWN;
			if(sim==0 && dir!=direction)
			{
				this.saveToFile();
			}
		}
		if(head.x>cherry.x && direction!=RIGHT)
		{
			direction=LEFT;
			if(sim==0 && dir!=direction)
			{
				this.saveToFile();
			}
		}
		if(head.x==cherry.x && head.y>cherry.y && direction!=DOWN)
		{
			direction=UP;
			if(sim==0 && dir!=direction)
			{
				this.saveToFile();
			}
		}
		if(head.x==cherry.x && head.y<cherry.y && direction!=UP)
		{
			direction=DOWN;
			if(sim==0 && dir!=direction)
			{
				this.saveToFile();
			}
		}	
	}
	public void saveToFile()
	{
		try {

            if(!savefile.exists()){
            	savefile.createNewFile();
            }
     

            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savefile.getAbsolutePath(), true), "UTF-8"));

     
            try {
            	

            	
            		dir=direction;
            		out.println(Integer.toString(this.direction));

            		out.println(Integer.toString(this.ticks));
            		out.println(Integer.toString(cherry.x));
            		out.println(Integer.toString(cherry.y));
            	
            } finally {
            	
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	public void endwritegame()
	{
		try {

            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savefile.getAbsolutePath(), true), "UTF-8"));

            try {                
            		out.println("xxx");
                    out.println(score);
            } finally {
            	
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
	}
	public void simulateFromFile(){
		String s;
		try {

            BufferedReader in = new BufferedReader(new FileReader( loadfile.getAbsoluteFile()));
            
            try {

            	  while(true)
            	  {
            		  s=in.readLine();
            		  if(s.compareTo("xxx")==0)
            		  {
            			  break;
            		  }
            		  simulate.add(Integer.parseInt(s));
            	  }     
              
            } finally {

            	
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
	}


}