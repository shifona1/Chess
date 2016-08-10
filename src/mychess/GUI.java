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
public class GUI extends JFrame
{
    static int side=50;
    Board board;
    public GUI(Board b)
    {
        super("MyChess");
        board=b;//JFrame boardui=new JFrame("MyChess");
        setSize(8*side+40,8*side+70);
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
                    else
                        f.setColor(Color.white);
                        f.fillRect(j*side+20,i*side+50,side,side);
                        if(board.tiles[i][j].piece!=null)
                        {                            
                            f.drawImage(board.tiles[i][j].piece.img, j*side+25,i*side+50-2, side-10, side-10, null);
                                    
                        }
                   
                }
            
        }
    
}
