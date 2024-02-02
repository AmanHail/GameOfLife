
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLife extends JFrame implements ActionListener

{
    private LifeCanvas display;
    private LifeControls controls;
    private Timer t;
    private int timeStep;
    private boolean isRunning;
    
    public GameOfLife() {
    	isRunning = false;
        display = new LifeCanvas(this);
        display.setBackground(Color.black);
        controls = new LifeControls(this);
        
        Container c = getContentPane();
        c.add(display, BorderLayout.CENTER);
        c.add(controls, BorderLayout.SOUTH);
        
        
       timeStep = 3000;
       t = new Timer(timeStep, this);
    }
    
    public void setSpeed(int millisecs)
    {
        timeStep = millisecs;
        t.setDelay(timeStep);
        t.restart();
    }
    
    public void next()
    {
        display.next();
        repaint();
    }
    
    public void stop()
    {
        t.stop();
        isRunning = false;
    }
    
    public void start()
    {
        t.start();
        isRunning = true;
    }
    
    /**
	 * Is the program running?
	 * @return true: program is running
	 */
	public boolean isRunning() {
		return isRunning;
	}
     
   
    
    public void actionPerformed(ActionEvent e)
    {
         next();
    }

    public static void main(String[] args)
    {
      GameOfLife window = new GameOfLife();
      window.setTitle("Game of Life 1.0");
      window.setBounds(100, 100, 600, 400);
      window.setDefaultCloseOperation(EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setVisible(true);
    }
    
}
