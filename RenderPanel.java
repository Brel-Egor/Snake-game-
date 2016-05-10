

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;



public class RenderPanel extends JPanel implements ActionListener, KeyListener
{
	
	public Snake snake;
	
    
	public RenderPanel(Snake snake2) {
		snake=snake2;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		

		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, 600, 700);

		g.setColor(Color.WHITE);

		for (int i=0;i<snake.snakeParts.size();i++)
		{
			Point point=snake.snakeParts.get(i);
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		g.setColor(Color.GREEN);
		
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		snake.textField.setText(""+ snake.score);
		
		snake.textField_1.setText(""+ snake.tailLength);
		g.setColor(Color.WHITE);

		String string = "Game Over";

		if (snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}

		string = "PAUSE";

		if (snake.paused && !snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();
		if(snake.is_man==1)
		{

			if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)
					&& snake.direction != snake.RIGHT) {
				snake.direction = snake.LEFT;
				if(snake.sim==0)
				{
					snake.saveToFile();
				}
			}

			if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT)
					&& snake.direction != snake.LEFT) {
				snake.direction = snake.RIGHT;
				if(snake.sim==0)
				{
					snake.saveToFile();
				}
			}

			if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP)
					&& snake.direction != snake.DOWN) {
				snake.direction = snake.UP;
				if(snake.sim==0)
				{
					snake.saveToFile();
				}
			}

			if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN)
					&& snake.direction != snake.UP) {
				snake.direction = snake.DOWN;
				if(snake.sim==0)
				{
					snake.saveToFile();
				}
			}
		}
		if (i == KeyEvent.VK_P)
		{
			if(!snake.paused)
			{
				snake.paused = !snake.paused;
			}
			else
			{
				snake.paused = !snake.paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
