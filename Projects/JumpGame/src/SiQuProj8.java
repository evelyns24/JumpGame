//Sarina Qu & Evelyn Si

import javax.swing.JOptionPane;
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
//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
//for time use
import java.util.Date;
//for music
import java.applet.*;
public class SiQuProj8 extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private ArrayList <Blocks> block;
    private ArrayList <Monster> m;
    private ArrayList <Blocks> newBlocks;
    private Jumper doodle;
    private int score;
    private int difficulty;
    private boolean jump;
    private Date startTime;
    private int time;
    private AudioClip backgroundMusic;
    private AudioClip jumpSound;
    private AudioClip popSound;
    //Default Constructor
    public SiQuProj8() 
    {
        //initializing instance variables
        WIDTH = 600;
        HEIGHT = 700;
        
        block=new ArrayList<Blocks>();
        newBlocks=new ArrayList<Blocks>();
        m=new ArrayList<Monster>();
        
        startTime=new Date();
        time=0;
        
        jump=true;
        
        score=0;
        difficulty=0;
        
        
        int ranY=675;
        int ranX=270;
        //adding first block 
        block.add(new Blocks(270,675)); //player's intial position
        //random positions for each block
        for(int i=0; i<100; i++) 
        {
            int ran=(int)(Math.random()*8+1);
            int ran3; //will be added to ranX
            if(ran%2==0)
                ran3=(int)(Math.random()*-250+50); 
            else
                ran3=(int)(Math.random()*150+50);
            //checking if the next block will be in the screen
            if(ranX+ran3>0 && ranX+ran3+100<600)
            {
                ranY-=100;
                ranX+=ran3;
                if(i%2==0)
                {
                    //adding a moving block
                    if (i<20)
                    {
                        block.add(new Blocks(ranX,ranY,2));
                    }
                    else if (i<40)
                    {
                        block.add(new Blocks(ranX,ranY,4));
                    }
                    else if (i<60)
                    {
                        block.add(new Blocks(ranX,ranY,6));
                    }
                    else if (i<80)
                    {
                        block.add(new Blocks(ranX,ranY,8));
                    }
                    else 
                    {
                        block.add(new Blocks(ranX,ranY,10));
                    }
                }
                else if (ran%4==0)
                {
                    //adding a moving monster
                    m.add(new Monster(ranX, ranY, 3));
                }
                else
                {
                    block.add(new Blocks(ranX,ranY));
                }
            }
            else
            {
                i--;
            }
        }
        
        doodle=new Jumper();

        //Setting up the GUI
        //gui box
        JFrame gui = new JFrame();

        //make sure program can close
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Title of game
        gui.setTitle("DOODLE JUMP");

        //setting size for gui
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));

        //mase so that gui cant be resized
        gui.setResizable(false);

        //adding this class to the gui
        gui.getContentPane().add(this);

        //declare buttons here
        //packing everything together
        gui.pack();

        //made so that the gui opens in the senter of the screen
        gui.setLocationRelativeTo(null);

        //made so that gui is visible
        gui.setVisible(true);

        //stating that this object will listen to the keyboard
        gui.addKeyListener(this);
        
        //stating that this object will listen to the Mouse
        gui.addMouseListener(this);

        //stating that this object will acknowlegde when the Mouse moves
        gui.addMouseMotionListener(this);
        //playing music
        backgroundMusic = Applet.newAudioClip(this.getClass().getResource("music.wav"));
        backgroundMusic.loop();
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {
        //getting the key pressed
        int key = e.getKeyCode();

        //moving the doodle
        if (key==37||key==65) //left
        {
            if(!(doodle.getX()<=block.get(0).getX()-10)) //prevent it from getting off the block
            {   
                doodle.setX(doodle.getX()-10);
            }
        }
        else if (key==39||key==68) //right
        {
            if (!(doodle.getX()+60>=block.get(0).getX()+110)) //prevent it from getting off the block
            {   
                doodle.setX(doodle.getX()+10);
            }
        }
        else if (key==38||key==87)//up
        {
            //jump sound effect
            jumpSound = Applet.newAudioClip(this.getClass().getResource("jumpEffect.wav"));
            jumpSound.play();
            
            doodle.setY(doodle.getY()-100);
            if(doodle.landOnBlock(block.get(1)))
            {
                //add 2 if blocks moves
                if(block.get(1).getvx()>0)
                    score+=2;
                //add 1 if blocks do not move
                else
                    score++;
                //shifting the player
                doodle.setY(doodle.getY()+100);
                block.remove(0);

                //shifting the blocks
                for(int i=0; i<block.size(); i++)
                { 
                    Blocks b=block.get(i);
                    b.setY(b.getY()+100);
                }  
                
                //shifting the monsters
                for (int i=0; i<m.size(); i++)
                {
                    Monster mon=m.get(i);
                    mon.setY(mon.getY()+100);
                }
                
                //changing value of difficulty
                if(block.size()>=80)
                {
                    difficulty=1;
                }
                else if (block.size()>=60)
                {
                    difficulty=2;
                }
                else if (block.size()>=40)
                {
                    difficulty=3;
                }
                else if (block.size()>=20)
                {
                    difficulty=4;
                }
                else
                {
                    difficulty=5;
                }
            } 
            else
            {
                jump=false;
            }
        }
        //restarts game
        if(key==10)
        {
            SiQuProj8 game=new SiQuProj8();
            game.start(60);
            backgroundMusic.stop();
        }
        //turn music off
        if(key==27)
        {
            backgroundMusic.stop();
        }
        //turn music on
        if(key==16)
        {
            backgroundMusic.loop();
        }
    }
    //All your UI drawing goes in here
    public void paintComponent(Graphics g) 
    {
        //displaying the score
        Font font1 = new Font("Arial", Font.BOLD, 20);
        g.setFont(font1);
        g.setColor(Color.BLACK);
        g.drawString("Score: "+score,480,30);
        //displaying music on and off
        g.drawString("Esc:Music Off",460,50);
        g.drawString("Shift:Music On",450,70);
        //displaying the difficulty
        g.drawString("Difficulty: "+difficulty,5,30);
        //displaying messages
        if(score==0)
        {
            g.drawString("Have Fun!",220,30);
        }
        if(time==300)
        {
            g.drawString("Are you even playing anymore?",160,30);
        }
        if(score==100)
        {
            g.drawString("Congrats on 100 points!",185,30);
        }
        //calling the draw methods
        doodle.draw(g);
        for (int i=0; i<m.size(); i++)
        {
            m.get(i).draw(g);
        }
        for (int i=0; i<block.size(); i++)
        {
            block.get(i).drawSelf(g);
        }
        //game over
        if(doodle.isDead() || !jump)
        {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            Font font2 = new Font("Arial", Font.BOLD, 20);
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER",225,325);
            g.drawString("Total Score: "+score, 225, 350);
            g.drawString("Total Time: "+ time+"s", 225, 375);
            g.drawString("Better luck next time!!", 180, 400);
            g.drawString("Press Enter to Restart", 180, 420);
            backgroundMusic.stop();
        }
        //win
        else if(block.size()==1)
        {
            Font font2 = new Font("Arial", Font.BOLD, 20);
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Congratualtions You Won",180,325);
            g.drawString("Total Score: "+score, 225, 350);
            g.drawString("Total Time: "+ time+"s", 225, 375);
            g.drawString("You have a lot of patience",180,400);
            g.drawString("Press Enter to Restart", 180, 420);  
            backgroundMusic.stop();
        }
        //display time
        g.drawString("Time: "+time+"s", 5, 60);
    }
     public void draw(Graphics g)
    {
    }
    public void loop() 
    {
        doodle.act(WIDTH);
        
        for(int i=0; i<m.size(); i++)
        {
            m.get(i).act(WIDTH, HEIGHT);
            doodle.touchesMonster(m.get(i));
        } 
        
        for (int i=0; i<block.size(); i++)
        {            
            block.get(i).act(WIDTH, HEIGHT);
            doodle.landOnBlock(block.get(i));
        }
        //current time
        Date cur=new Date();
        if(!(doodle.isDead() || !jump))
        {
            //finding the time bw the start and current time
            int h1=cur.getHours(),h2=startTime.getHours();
            int m1=cur.getMinutes(), m2=startTime.getMinutes();
            int s1=cur.getSeconds(), s2=startTime.getSeconds();
            int totalSeconds1=s1+(m1*60)+(h1*3600);
            int totalSeconds2=s2+(m2*60)+(h2*3600);
            time=totalSeconds1-totalSeconds2;
        }

        //Do not write below this
        repaint();
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
        int eX=e.getX();
        int eY=e.getY();
        for(int i=0; i<m.size(); i++)
        {
            Monster cur=m.get(i);
            //if player clicks on a monster = moves the monster out of the screen and replaces it with a block
            if(eX>=cur.getX() && eX<=cur.getX()+60)
            {
                if(eY>=cur.getY() && eY<=cur.getY()+60)
                {
                    //add 2 points if player clicks on the moster
                    score+=2;
                    int index=0;
                    for(int j=0; j<block.size(); j++)
                    {
                        if(block.get(j).getY()==cur.getY()+100)
                        {
                            index=j;
                            j=block.size();
                            //pop effect
                            popSound=Applet.newAudioClip(this.getClass().getResource("popEffect.wav"));
                            popSound.play();
                        }
                    }
                    block.add(index+1,new Blocks(cur.getX(),cur.getY(),5));
                    block.get(index+1).changeColor();
                    
                    //make the monsters out of screen
                    m.get(i).setX(-100);
                    m.get(i).setvx(0);
                }
            }
        }
        
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
                    loop();
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
        //calling the startScreen class
        startScreen g = new startScreen();
    }
}

