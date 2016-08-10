/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychess;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author shifona
 */
public abstract class Piece {
    String color;
    Image img;
    public Piece(String c)
    {
        color=c;
        
        
        loadImage(this.getClass().getSimpleName().toLowerCase());
    }
    
    public void loadImage(String name) {
        try {
         img = ImageIO.read(new File("images/"+name.toLowerCase()+"_"+color.toLowerCase()+".png"));
        } 
        catch (IOException e) 
            {
                img=null;
                e.printStackTrace();
            }
    }
        abstract boolean isValid(Board b,int prevx,int prevy, int newx,int newy);

    
    //abstract String getPiece();
    
    
}

class Rook extends Piece
{
    public Rook(String c)
    {
        super(c);

    }
    
    Image getImage()
    {
       return img;
        
    }
    
    boolean isValid(Board b,int prevx,int prevy, int newx,int newy)
    {
        if(!(newx==prevx && newy==prevy))
        {
            if(newx==prevx)
            {
                int c=newy>prevy?1:-1;
                for(int i=prevy;c>0?i<newy:i>newy;i+=c)
                {
                    if(b.tiles[newx][i].piece!=null)
                    {
                        System.out.println("Cant move over other pieces");
                        return false;
                    }
                }
                if(b.tiles[newx][newy].piece!=null && b.tiles[newx][newy].piece.color.equals(color))return false;
                return true;
            }
            if(newy==prevy)
            {
                int c=newx>prevx?1:-1;
                for(int i=prevx;c>0?i<newx:i>newx;i+=c)
                {
                    if(b.tiles[i][newy].piece!=null)
                    {
                        System.out.println("Cant move over other pieces");
                        return false;
                    
                    }
                }
                if(b.tiles[newx][newy].piece!=null && b.tiles[newx][newy].piece.color.equals(color))return false;
                return true;
            }   
          
        }
        System.out.println("Row or column should be same");
        return false;
        
    }
}

class Knight extends Piece
{
    public Knight(String c)
    {
        super(c);
    }
      Image getImage()
    {
       return img;
        
    }
      boolean isValid(Board b,int prevx,int prevy, int newx,int newy)
    {
        if((Math.abs(newx-prevx)==2 && Math.abs(newy-prevy)==1) || (Math.abs(newx-prevx)==1 && Math.abs(newy-prevy)==2))
            if(b.tiles[newx][newy].piece==null || !b.tiles[newx][newy].piece.color.equals(color))
           return true;
        
        System.out.println("Inalid Knight move");
        return false;
        
    }
     
}
 
class Bishop extends Piece
{
    public Bishop(String c)
    {
        super(c);
    }
      Image getImage()
    {
       return img;
        
    }
    
    boolean isValid(Board b,int prevx,int prevy, int newx,int newy)
    {
        if(!(newx==prevx && newy==prevy))
        {
            if(Math.abs(newx-prevx)== Math.abs(newy-prevy) )//&& b.tiles[newx][newy].piece==null
            {
                int c=newx>prevx?1:-1;
                int d=newy>prevy?1:-1;
                int i=prevx,j=prevy;
                while((newx>prevx?i<newx:i>newx)&&(newy>prevy?j<newy:j>newy))
                {
                    if(b.tiles[i][j].piece!=null)
                    {
                        System.out.println("Cant move over other pieces");
                        return false;
                    }
                    i+=c;
                    j+=d;
                }
                if(b.tiles[newx][newy].piece!=null && b.tiles[newx][newy].piece.color.equals(color))return false;
                return true;
            }
        }
        
        System.out.println("Invalid Bishop move");
        
        return false;
        
    }  
}

class King extends Piece
{
    public King(String c)
    {
        super(c);
    }
     
     Image getImage()
    {
       return img;
        
    }
     
     boolean isValid(Board b,int prevx,int prevy, int newx,int newy)
    {
        if(Math.abs(newx-prevx)<=1 && Math.abs(newy-prevy)<=1)
            if(b.tiles[newx][newy].piece==null || !b.tiles[newx][newy].piece.color.equals(color))
           return true;
        
        
        System.out.println("Inalid King move");
        
        return false;
        
    }
     
}
    
class Queen extends Piece
{
    public Queen(String c)
    {
        super(c);
    }
     
     Image getImage()
    {
       return img;
        
    }
     boolean isValid(Board b,int prevx,int prevy, int newx,int newy)
    {
        if((new Rook(color)).isValid(b,prevx,prevy,newx,newy)||(new Bishop(color)).isValid(b,prevx,prevy,newx,newy))
            return true;
        return false;
    }
}

class Pawn extends Piece
{
    public Pawn(String c)
    {
        super(c);
    }
    
     Image getImage()
    {
       return img;
        
    }
     
    boolean isValid(Board b,int prevx,int prevy, int newx,int newy)
    {
        if(color.equals("Black"))
        {
        if(newx-1==prevx && newy== prevy && b.tiles[newx][newy].piece==null)
           return true;
        if(newx-1==prevx && Math.abs(newy-prevy)==1 && b.tiles[newx][newy].piece!=null )
            if(b.tiles[newx][newy].piece.color!=color)
                return true;
            else return false;
        return false;
        }
        else
        {
             if(newx+1==prevx && newy== prevy && b.tiles[newx][newy].piece==null)
           return true;
        if(newx+1==prevx && Math.abs(newy-prevy)==1 && b.tiles[newx][newy].piece!=null )
            if(b.tiles[newx][newy].piece.color!=color)
                return true;
            else return false;
        return false;
        }
        
    }
}