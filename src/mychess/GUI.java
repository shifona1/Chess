/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychess;

/**
 *
 * @author shifona
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
public class GUI extends JFrame implements MouseMotionListener, MouseListener 
{
    static int side=50;
    static int offsetx=20;
    static int offsety=50;
    int highlighter_x=-1,highlighter_y=-1,click_x=-1,click_y=-1;
    int prevx,prevy,newx,newy;
    boolean willMove=false;
    
    Board board;
    MyChess chess;
    public GUI(Board b,MyChess chess)
    {
        super("MyChess");
        board=b;//JFrame boardui=new JFrame("MyChess");
        this.chess=chess;
        setSize(8*side+40,8*side+70);
        addMouseMotionListener(this);
        addMouseListener(this);
        setVisible(true);
        
            
    }
    public void paint(Graphics f)
	{
            super.paint(f);
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    if(board.tiles[i][j].color.equals("Black"))
                    {
                        f.setColor(new Color(40,40,40));
                   
                    }
                    else if(board.tiles[i][j].color.equals("White"))
                        f.setColor(Color.WHITE);
                    f.fillRect(j*side+offsetx,i*side+offsety,side,side);
                        if(board.tiles[i][j].piece!=null)
                        {                            
                            f.drawImage(board.tiles[i][j].piece.img, j*side+25,i*side+50-2, side-10, side-10, null);
                                    
                        }
                   
                        }
            if(highlighter_x>=0&& highlighter_x<8 && highlighter_y >=0 && highlighter_y <8)
            {
                f.setColor(new Color(255,255,0,80));
                f.fillRect(highlighter_y*side+offsetx,highlighter_x*side+offsety,side,side);
            }
            if(willMove)
            if(click_x>=0&& click_x<8 && click_y >=0 && click_y <8 && board.tiles[click_x][click_y].piece!=null)
            {
                willMove=true;
                f.setColor(new Color(0,0,255,100));
                f.fillRect(click_y*side+offsetx,click_x*side+offsety,side,side);
            }
//            else
//            {
//                
//            }
        }
    
    public void mouseMoved(MouseEvent me)
    {
        highlighter_x=me.getY()-offsety;
        highlighter_y=me.getX()-offsetx;
        highlighter_x=(highlighter_x)/side;
        highlighter_y=(highlighter_y)/side;
    }
    
    public void mouseDragged(MouseEvent e) 
	{}
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        click_y=(e.getX()-offsetx)/side;
        click_x=(e.getY()-offsety)/side;
        System.out.println(click_x+" "+click_y);
        if(!willMove)
        {
            prevx=click_x;
            prevy=click_y;
            willMove=true;
        }
        else
        {
            newx=click_x;
            newy=click_y;
            willMove=false;
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                   chess.move(prevx, prevy, newx, newy);
               }
            });
         }
        //repaint (); 
    }
    public void mousePressed(java.awt.event.MouseEvent ev) { } 

    public void mouseReleased(java.awt.event.MouseEvent ev) { } 

    public void mouseEntered(java.awt.event.MouseEvent ev) { } 

    public void mouseExited(java.awt.event.MouseEvent ev) { } 
    
}
