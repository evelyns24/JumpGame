//Sarina Qu & Evelyn Si

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;


public class Monster
{
    private int xCoord, yCoord;
    private int vx;
    
    public Monster(int x, int y, int v)
    {
        xCoord=x;
        yCoord=y;
        vx=v;
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
    
    //mutator methods
    public void setX(int x)
    {
        xCoord=x;
    }
    public void setY(int y)
    {
        yCoord=y;
    }
    public void setvx(int v)
    {
        vx=v;
    }
    
    public void act(int w, int h)
    {
        int nextX=xCoord+vx;
        //collision with walls
        if(nextX+60>w)
        {
            vx*=-1;
        }
        else if(nextX<0)
        {
            vx*=-1;
        }
        xCoord+=vx;
    }
    
    public void draw(Graphics g)
    {
        //declaring a Graphics2D object called g2d
        Graphics2D g2d;
        //cast g to Graphics2D and store it into g2d
        g2d=(Graphics2D)g;
        //declare and instantiate an ImageIcon
        ImageIcon pic1=new ImageIcon(Monster.class.getResource("mon1.png"));
        //call the getImage method
        Image image1=pic1.getImage();
        //call the drawImage method on g2d
        g2d.drawImage(image1,xCoord,yCoord,60,60,null);
        
    }
}