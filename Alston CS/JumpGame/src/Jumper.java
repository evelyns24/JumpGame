//Sarina Qu & Evelyn Si

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.ArrayList;
 
public class Jumper
{
    //instance variable
    private int xCoord, yCoord;
    private int vx;
    private boolean dead;
    
    public Jumper()
    {
        xCoord=280;
        yCoord=615;
        vx=0;
        dead=false;
    }
    //accessor methods
    public int getX()
    {
        return xCoord;
    }
    public int getY()
    {
        return yCoord;
    }
    public boolean isDead()
    {
        return dead;
    }
    
    
    //mutator methods
    public void setX(int x)
    {
        xCoord=x;
    }
    public void setY(int y)
    {
        yCoord=y;
    }


    public void draw(Graphics g)
    {
        Graphics2D g2d=(Graphics2D)g;
        ImageIcon pic=new ImageIcon(Jumper.class.getResource("DoodleJump.png"));
        Image doodle=pic.getImage();
        g2d.drawImage(doodle,xCoord,yCoord,60,60,null);
    }
    public void act(int w)
    {
        xCoord+=vx; 
    }
    
    //helper methods 
    //finds the horizontal(x) distance from player to block
    private int xDistance(Blocks b)
    {
        int blockX=b.getX();
        return blockX-xCoord;
    }
    //horizontal distance from player to monster
   private int xDistance(Monster m)
    {
        int monsterX=m.getX();
        return monsterX-xCoord;
    }
   //finds the vertical(y) distance from player to block
    private int yDistance(Blocks b)
    {
        return b.getY()-yCoord;
    }
    //vertical didtance from player to monster
    private int yDistance(Monster m)
    {
        int monsterY=m.getY();
        return monsterY-yCoord;
    }
    
    
    //returns true if player lands on a block
    public boolean landOnBlock(Blocks b)
    {
        //when player is landing on a block
        if (xDistance(b)>=-90 && xDistance(b)<=50 && yDistance(b)<=60 && yDistance(b)>=0) 
        {
            //making plaer's vx equal that of the block
            vx=b.getvx();
            return true;
        }
        return false;
    }
    //checks if player is right under a monster, if true then set dead to true
    public void touchesMonster(Monster m)
    {
        if (xDistance(m)<=50 &&xDistance(m)>=-50 && yDistance(m)>=-100 && yDistance(m)<=100)
        {
            dead=true;
        }
    }
    
}