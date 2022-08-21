//Sarina Qu & Evelyn Si

import java.awt.Graphics;
import java.awt.Color;
public class Blocks
{
    private int x;
    private int y;
    private int vx;
    private int len;
    private int width;
    private Color col;
    public Blocks(int xCoor, int yCoor) //blocks that dont move
    {
        x=xCoor;
        y=yCoor;
        vx=0;
        len=25;
        width=100;
        col=Color.GREEN;
    }
    public Blocks(int xCoor, int yCoor, int vofx) //blocks that move left and right
    {
        x=xCoor;
        y=yCoor;
        vx=vofx;
        len=25;
        width=100;
        col=Color.BLUE;
    }
    
    //accessor methods
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getvx()
    {
        return vx;
    }
    public void setVX(int v)
    {
        vx=v;
    }
    public void setY(int newY)
    {
        y=newY;
    }
    
    //changing the color of the block
    public void changeColor()
    {
        col=Color.MAGENTA;
    }
    
    public void act(int w, int h)
    {
        int nextX=x+vx;
        //collision with left and right walls
        if(nextX+width>w)
        {
            vx*=-1;
        }
        else if(nextX<0)
        {
            vx*=-1;
        }
        x+=vx;
    }
    public void drawSelf(Graphics g)    
    {
        g.setColor(col);
        g.fillRect(x,y,width,len);
    }
}
