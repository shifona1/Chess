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
public class Tile {
    
    int x, y;
    String color;
    int side=GUI.side;
    Piece piece;
    public Tile(int x,int y, boolean b)
    {
        x=x;
        y=y;
        if(b)color="Black";
        else color="White";
        
    }
}
