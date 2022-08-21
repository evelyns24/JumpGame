//Sarina Qu & Evelyn Si

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


public class gameInstructions extends JComponent implements KeyListener, MouseListener, MouseMotionListener 
{
    //instance variables

    private int WIDTH;
    private int HEIGHT;

    //Default Constructor
    public gameInstructions() 
    {
        //initializing instance variables
        WIDTH = 600;
        HEIGHT = 700;
        
        //Setting up the GUI
        //gui box
        JFrame start = new JFrame("START SCREEN");

        //make sure program can close
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Title of game
        start.setTitle("DOODLE JUMP");

        //setting size for gui
        start.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));

        //mase so that gui cant be resized
        start.setResizable(false);

        //adding this class to the gui
        start.getContentPane().add(this);

        //declare buttons here
        
        //packing everything together
        start.pack();

        //made so that the gui opens in the senter of the screen
        start.setLocationRelativeTo(null);

        //made so that gui is visible
        start.setVisible(true);

        //stating that this object will listen to the keyboard
        start.addKeyListener(this);
        
        //stating that this object will listen to the Mouse
        start.addMouseListener(this);

        //stating that this object will acknowlegde when the Mouse moves
        start.addMouseMotionListener(this);
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {
        //getting the key pressed
        int key = e.getKeyCode();
        //start game
        if (key==69)
        {
            SiQuProj8 g = new SiQuProj8();
            g.start(60);
        }
    }

    //All your UI drawing goes in here
    public void paintComponent(Graphics g) 
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.BLACK);
        Font instruct=new Font("Arial",Font.BOLD,15);
        g.setColor(Color.BLACK);
        //game instructions
        g.drawString("There are 100 platforms, therefore, to win you ",WIDTH/12,HEIGHT/9);
        g.drawString("must reach the 100th platform", WIDTH/4, HEIGHT/7);
        g.drawString("To kill the monsters you need to double click on them", WIDTH/12, HEIGHT/5);
        g.drawString("A mouse pad would be easier to use", WIDTH/4, HEIGHT/4);
        g.drawString("The higher the difficulty level the faster the platforms move",WIDTH/20,210);
        g.drawString("The up, left, and right arrows control the jumper",WIDTH/10,240);
        g.drawString("The W, A, AND D key's may also be used",WIDTH/5,275);
        g.drawString("If the monster is successfully clicked 2 points is awarded",WIDTH/14,300);
        g.drawString("The moving blocks are worth 2 points",WIDTH/5,330);
        g.setColor(Color.RED);
        g.drawString("Press E to Start", WIDTH / 3, 370);
    }

    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e) 
    {

    }

    public void keyReleased(KeyEvent e) 
    {

    }

    public void mousePressed(MouseEvent e) 
    {

    }

    public void mouseReleased(MouseEvent e) 
    {

    }

    public void mouseClicked(MouseEvent e) 
    {
        
    }

    public void mouseEntered(MouseEvent e) 
    {
        
    }

    public void mouseExited(MouseEvent e) 
    {
        
    }

    public void mouseMoved(MouseEvent e) 
    {

    }

    public void mouseDragged(MouseEvent e) 
    {

    }

    public void start(final int ticks) 
    {

        Thread gameThread = new Thread() 
        {

            public void run() 
            {

                while (true) 
                {

                    

                    try 
                    {

                        Thread.sleep(1000 / ticks);

                    } 
                    catch (Exception e) 
                    {

                        e.printStackTrace();

                    }

                }

            }

        };

        gameThread.start();

    }

    public static void main(String[] args) 
    {

        gameInstructions g = new gameInstructions();

        g.start(60);

    }

}
