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
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class MyChess {

    GUI gui;
    Board board;
    Connection conn;
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
    
    boolean canTakeInput() {
        return isBlackTurn^conn.isServer;
           
    }
    boolean move(int prevx,int prevy,int newx,int newy)
    {
            
        if(!(prevx>=0 && newx>=0 && prevx<8 && newx<8 && prevy>=0 && newy>=0 && prevy>=0 && newy>=0 )) {
            System.out.println("Please enter the valid coordinates");;
            return false;
        }
        if(board.tiles[prevx][prevy].piece==null)
        {
            System.out.println("There is no piece to move! ");;
            return false;
        }
//        if((isBlackTurn && board.tiles[prevx][prevy].piece.color.equals("White"))||(!isBlackTurn && board.tiles[prevx][prevy].piece.color.equals("Black")))
//        {
//            JOptionPane.showMessageDialog(null,"Its not your turn !");
//            return false;
//        }
            if(!board.tiles[prevx][prevy].piece.isValid(board, prevx, prevy, newx, newy))
            {
                System.out.println("Invalid");
                 return false;
            }   
                if(board.tiles[newx][newy].piece!=null && board.tiles[newx][newy].piece.getClass()==King.class)
                {
                    System.out.println("Game Over");
                    
                    board.tiles[newx][newy].piece=board.tiles[prevx][prevy].piece;
                System.out.println("Moved");
                board.tiles[prevx][prevy].piece=null;
                if(isBlackTurn)JOptionPane.showMessageDialog(null, "Black Wins..... Congrats !!!!");
                    else JOptionPane.showMessageDialog(null, "White Wins..... Congrats !!!!");
                isBlackTurn=!isBlackTurn;
                    System.exit(0);
                }
                board.tiles[newx][newy].piece=board.tiles[prevx][prevy].piece;
                System.out.println("Moved");
                board.tiles[prevx][prevy].piece=null;
                
                isBlackTurn=!isBlackTurn;
                return true;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        final MyChess chess = new MyChess();
        chess.conn=new Connection(chess);
        
        chess.start();
        new Thread(){

            @Override
            public void run() {
                try {
                    while(true) {
                    chess.gui.repaint();
                    sleep(200);
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