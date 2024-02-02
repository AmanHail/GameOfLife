import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LifeCanvas extends JPanel
    implements MouseListener, MouseMotionListener
    
{
    private GameOfLife app;
    private TextArea textArea;
    private LifeGrid grid;
    private int cellUnderMouse;
    private int cellSize;
    
    /**
     * Constructor for objects of class LifeCanvas
     */
    public LifeCanvas(GameOfLife applet)
    {
       app = applet;
            
       grid = new LifeGrid(50,50);
       cellSize = 10;
       setLayout(new BorderLayout());
       textArea = new TextArea("Mouse Move",1,200,TextArea.SCROLLBARS_NONE);
       add(textArea, BorderLayout.SOUTH);
       textArea.setText("Welcome to Game of Life");
       addMouseListener(this);
       addMouseMotionListener(this);
       
    }
    

  public void mouseClicked(MouseEvent e)
  {
	  if (e.getButton() == MouseEvent.BUTTON1)
      {
          
          textArea.setText("saving cell");
          saveCellUnderMouse(e.getX(), e.getY());      

      }
      else if (e.getButton()== MouseEvent.BUTTON3)
      {
          textArea.setText("saving cell");
          saveCellUnderMouse(e.getX(), e.getY());
          
      }
      repaint();
   }
   
   public void mouseEntered(MouseEvent e)
   {
      textArea.setText("I'm in!!");
   }
   
   public void mouseExited(MouseEvent e) 
   {
      textArea.setText("I'm out!!");
   }
   public void mousePressed(MouseEvent e) 
   {
      textArea.setText("saving cell MousePressed");
      saveCellUnderMouse(e.getX(), e.getY());
   }
   public void mouseReleased(MouseEvent e) 
   {
      textArea.setText("drawing cell at " + e.getX() + "," + e.getY());
      draw(e.getX(), e.getY());
   }
      
  
  
 
  public void mouseMoved(MouseEvent e) 
  {
       textArea.setText("x="+e.getX() + " y="+e.getY());
  }
  public void mouseDragged(MouseEvent e) 
  {
      textArea.setText("dragging and drawing");
      draw(e.getX(), e.getY());
  }


    /**
     * Remember state of cell for drawing.
     * 
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     */
    public void saveCellUnderMouse(int x, int y) {
        try {
            cellUnderMouse = grid.getCell(x / cellSize, y / cellSize);
            textArea.setText("saving cell:" + cellUnderMouse);
        
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            
        }
        finally
        {
            
        }
    }
    
    /**
     * Draw in this cell.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void draw(int x, int y) {
        try {
            grid.setCell(x / cellSize, y / cellSize, 1-cellUnderMouse );
            textArea.setText("Set Cell to:" + (1-cellUnderMouse));
            repaint();
        } 
        catch (java.lang.ArrayIndexOutOfBoundsException e) {
                     
        }
        finally {
           
        }
    }
    
  public void next()
  {
      grid.evolve();
      repaint();
  }

  public void paintComponent(Graphics g)
  { 
    super.paintComponent(g);

        int width = grid.getNumCols();
        int height = grid.getNumRows();
        g.setColor(Color.white);
        for (int x = 1; x < width; x++) {
            g.drawLine(x * cellSize - 1, 0, x * cellSize - 1, cellSize * height - 1);
        }
        for (int y = 1; y < height; y++) {
            g.drawLine( 0, y * cellSize - 1, cellSize * width - 1, y * cellSize - 1);
        }
        g.setColor(Color.orange);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid.getCell(x, y)>0) {
                    g.fillRect(x * cellSize, y * cellSize, cellSize - 1, cellSize - 1);
                }
            }
        }
  }
}
