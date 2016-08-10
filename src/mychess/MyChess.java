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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyChess {

    GUI gui;
    Board board;
    Scanner in=new Scanner(System.in);
    static boolean isBlackTurn=false;
    public MyChess() {
        board=new Board();
        gui = new GUI(board,this);
    }
    
    void start() {
        board.reset();
        gui.repaint();
        
        
    }
    
    void play()
    {
        if(isBlackTurn)System.out.println("Black's Turn"); else System.out.println("White's Turn");
        System.out.println("PLease enter the coordinates of the piece to be moved in order: x :  y : ");
        int prevx=in.nextInt();
        int prevy=in.nextInt();
        
        System.out.println("Please enter the coordinates of the new location of the piece in order: x :  y : ");
        int newx=in.nextInt();
        int newy=in.nextInt();
                //gui.repaint();
            move(prevx,prevy,newx,newy);
    }
    
    void move(int prevx,int prevy,int newx,int newy)
    {
            
        if(!(prevx>=0 && newx>=0 && prevx<8 && newx<8 && prevy>=0 && newy>=0 && prevy>=0 && newy>=0 )) {
            System.out.println("Please enter the valid coordinates");;
            return;
        }
        if(board.tiles[prevx][prevy].piece==null)
        {
            System.out.println("There is no piece to move! ");;
            return;
        }
        if((isBlackTurn && board.tiles[prevx][prevy].piece.color.equals("White"))||(!isBlackTurn && board.tiles[prevx][prevy].piece.color.equals("Black")))
        {
            System.out.println("You can move only your colour pieces");
            return;
        }
            if(!board.tiles[prevx][prevy].piece.isValid(board, prevx, prevy, newx, newy))
            {
                System.out.println("Invalid");
                 return;
            }   
                if(board.tiles[newx][newy].piece!=null && board.tiles[newx][newy].piece.getClass()==King.class)
                {
                    System.out.println("Game Over");
                    if(isBlackTurn)System.out.println("White wins... Congrats!!!");
                    else System.out.println("Black wins... Congrats!!!");
                    return;
                }
                board.tiles[newx][newy].piece=board.tiles[prevx][prevy].piece;
                System.out.println("Moved");
                board.tiles[prevx][prevy].piece=null;
                isBlackTurn=!isBlackTurn;
    
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final MyChess chess = new MyChess();
        chess.start();
        new Thread(){

            @Override
            public void run() {
                try {
                    while(true) {
                    chess.gui.repaint();
                    sleep(100);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyChess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }.start();
        while(true)
        {
            chess.play();
            
        }
    }
    
}