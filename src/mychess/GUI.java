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
import java.util.logging.Level;
import java.util.logging.Logger;
public class GUI extends JFrame implements MouseMotionListener, MouseListener 
{
    static int side=70;
    static int offsetx=20;
    static int offsety=50;
    int highlighter_x=-1,highlighter_y=-1,click_x=-1,click_y=-1;
    int prevx,prevy,newx,newy,opacity=150;
    boolean willMove=false,valid=true;
    
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
                    if(i==highlighter_x && j==highlighter_y)
                    {
                        f.setColor(new Color(255,255,0,80));
                        f.fillRect(highlighter_y*side+offsetx,highlighter_x*side+offsety,side,side);
                    }  
                    if(willMove)
                if(i==click_x && j==click_y) 
                {
                    //willMove=true;
                    f.setColor(new Color(0,0,255,100));
                    f.fillRect(click_y*side+offsetx,click_x*side+offsety,side,side);
                }
                    final int newi=i,newj=j;
                    if(!willMove && !valid)
                        if(newi==click_x && newj==click_y)
                    {
                        f.setColor(new Color(255,0,0,opacity));
                        f.fillRect(click_y*side+offsetx,click_x*side+offsety,side,side);
                        valid=true;
                        
                    }
                    
                    
                        if(board.tiles[i][j].piece!=null)
                        {                            
                            f.drawImage(board.tiles[i][j].piece.img, j*side+25,i*side+50-2, side-10, side-10, null);
                                    
                        }
                   
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
        if(!chess.canTakeInput())
        {
            JOptionPane.showMessageDialog(null,"Its not your turn !");
            return;
        }    
        click_y=(e.getX()-offsetx)/side;
        click_x=(e.getY()-offsety)/side;
        System.out.println(click_x+" "+click_y);
        if(!willMove)
        {
            prevx=click_x;
            prevy=click_y;
            if(board.tiles[click_x][click_y].piece!=null)
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
                   valid=chess.move(prevx, prevy, newx, newy);
//                   new Thread(){
//
//                        @Override
//                        public void run() {
//                            try {
//                                //   super.run(); //To change body of generated methods, choose Tools | Templates.
//                                for(int i=1;i<=10;i++)
//                                {                                 
//                                  Thread.sleep(50);
//                                  opacity-=15;
//                                }
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            valid=true;
//                            }
//                    }.start();
                   
                   
               }
            });
            chess.conn.sendMsg(prevx+" "+prevy+" "+newx+" "+newy);
      
         }
        //repaint (); 
    }
    public void mousePressed(java.awt.event.MouseEvent ev) { } 

    public void mouseReleased(java.awt.event.MouseEvent ev) { } 

    public void mouseEntered(java.awt.event.MouseEvent ev) { } 

    public void mouseExited(java.awt.event.MouseEvent ev) { } 
    
}
