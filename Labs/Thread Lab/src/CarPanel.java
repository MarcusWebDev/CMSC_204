package application;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
   @author Marcus Brooks
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation()
	   {
	      class AnimationRunnable implements Runnable
	      {
	         public void run()
	         {
	            try
	            {
	            	while (true) {
	            		direction = carQueue.deleteQueue();
	            		System.out.println("Direction: " + direction);
	            		switch (direction) {
	            		case 0:
	            			if (y + 10 > 300) 
	            				y = y - 10;
	            			else
	            				y = y + 10;
	            			break;
	            		case 1:
	            			if (y - 10 < 0)
	            				y = y + 10;
	            			else
	            				y = y - 10;
	            			break;
	            		case 2:
	            			if (x + 10 > 200)
	            				x = x - 10;
	            			else
	            				x = x + 10;
	            			break;
	            		case 3:
	            			if (x - 10 < 0)
	            				x = x + 10;
	            			else
	            				x = x - 10;
	            			break;
	            		default: 
	            			System.out.println("Invalid direction");
	            		}
	            		repaint();
	            		Thread.sleep(delay*1000);
	            	}
	            }
	            catch (InterruptedException exception)
	            {
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
	      
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}