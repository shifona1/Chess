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
public class Board {
    Tile[][] tiles=new Tile[8][8];
    Board()
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
        {
            tiles[i][j]=new Tile(j*GUI.side,i*GUI.side,((i+j)%2==0));
        }
    }
    
    void reset()
    {
        tiles[0][0].piece=new Rook("Black");
        tiles[0][7].piece=new Rook("Black");
        tiles[0][1].piece=new Knight("Black");
        tiles[0][6].piece=new Knight("Black");
        tiles[0][2].piece=new Bishop("Black");
        tiles[0][5].piece=new Bishop("Black");
        tiles[0][3].piece=new King("Black");
        tiles[0][4].piece=new Queen("Black");
        for(int j=0;j<8;j++)
        {
            tiles[1][j].piece=new Pawn("Black");
        }
        
        
        tiles[7][0].piece=new Rook("White");
        tiles[7][7].piece=new Rook("White");
        tiles[7][1].piece=new Knight("White");
        tiles[7][6].piece=new Knight("White");
        tiles[7][2].piece=new Bishop("White");
        tiles[7][5].piece=new Bishop("White");
        tiles[7][3].piece=new King("White");
        tiles[7][4].piece=new Queen("White");
        for(int j=0;j<8;j++)
        {
            tiles[6][j].piece=new Pawn("White");
        }
      
                
        
    }
    
}
