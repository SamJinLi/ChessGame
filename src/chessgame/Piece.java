/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.LinkedList;

import chessgame.ChessGame;

/**
 *
 * @author Khouiled
 */
public class Piece {
    public int xp;
    public int yp;

    public int x, y;

    public boolean isWhite, isDragging = false, canBeTaken = false;
    public LinkedList<Piece> ps;
    public String name;

    public static int wKingIndex = -1, bKingIndex = -1;
    public Piece(int xp, int yp, boolean isWhite,String n, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp*ChessboardPanel.squareSize;
        y = yp*ChessboardPanel.squareSize;
        this.isWhite = isWhite;
        this.ps=ps;
        name=n;
        ps.add(this);
    }
    
    public boolean move(int xp,int yp){
        // ps.stream().filter((p) -> (p.xp==xp&&p.yp==yp)).forEachOrdered((p) -> {
        //     p.kill();
        // });
        isDragging = false;
        Piece other = ChessGame.getPieceByPosition(xp, yp);
        // System.out.println("move xp: " + xp + ", yp: " + yp + ", other: " + (other == null ? "null" : ((other.isWhite ? "white " : "black ") + other.name)));
        // System.out.println("isWhiteMoving == " + ChessGame.isWhiteMoving + ", isWhite == " + isWhite);
        if(ChessGame.isWhiteMoving && (!isWhite) && !ChessGame.developing)
        {
            return false;
        }
        if(other != null && other.isWhite == isWhite){
            System.err.println("Same color");
            return false;
        }
        boolean isMoveLegal = isMoveLegal(xp, yp);
        // System.out.println("isMoveLegal piece + " + isMoveLegal);
        if(ChessGame.wChecked && isWhite && ((other!= null && other.isWhite != isWhite) || (other == null && xp < 8 && yp < 8)))
        {
            int tempxp = xp, tempyp = yp;
            this.xp = xp;
            this.yp = yp;
            if(isThereStillCheck())
            {
                this.xp = tempxp;
                this.yp = tempyp;
                System.out.println("HEY! Still checked");
                return false;
            }
        }
        if(other!= null && other.isWhite != isWhite && isMoveLegal)
        {
            other.kill();
            this.xp=xp;
            this.yp=yp;
            x = xp*ChessboardPanel.squareSize;
            y = yp*ChessboardPanel.squareSize;
            // System.out.println("kill piece: " + (other.isWhite ? "white " : "black ") + other.name + " and moved " + (this.isWhite ? "white " : "black ") + this.getName() + " to " + xp + yp);
            checkForCheckThisPiece();
            return true;
        }
        if(other == null && xp < 8 && yp < 8 && isMoveLegal)
        {
            this.xp=xp;
            this.yp=yp;
            x = xp*ChessboardPanel.squareSize;
            y = yp*ChessboardPanel.squareSize;
            // System.out.println("moved " + (this.isWhite ? "white " : "black ") + this.getName() + " to " + xp + yp);
            checkForCheckThisPiece();
            return true;
        }
        return false;
    }

    public void checkForCheckThisPiece()
    {
        if(wKingIndex > -1 && bKingIndex > -1)
        {
            int wxp = ps.get(wKingIndex).xp, wyp = ps.get(wKingIndex).yp, bxp = ps.get(bKingIndex).xp, byp = ps.get(bKingIndex).yp;
            if(isWhite)
            {
                if(isMoveLegal(bxp, byp))
                {
                    ChessGame.bChecked = true;
                }
            }
            else if(!isWhite)
            {
                if(isMoveLegal(wxp, wyp))
                {
                    ChessGame.wChecked = true;
                }
            }
        }
        else
        {
            System.err.println("ERROR: KING NOT FOUND");
        }
    }

    public boolean isThereStillCheck()
    {
        if(wKingIndex > -1 && bKingIndex > -1)
        {
            int wxp = ps.get(wKingIndex).xp, wyp = ps.get(wKingIndex).yp, bxp = ps.get(bKingIndex).xp, byp = ps.get(bKingIndex).yp;
            if(isWhite)
            {
                for(Piece p : ps)
                {
                    if(p.isWhite != isWhite && p.isMoveLegal(bxp, byp))
                    {
                        return true;
                    }
                }
                ChessGame.bChecked = false;
            }
            else if(!isWhite)
            {
                for(Piece p : ps)
                {
                    if(p.isWhite != isWhite && p.isMoveLegal(bxp, byp))
                    {
                        return true;
                    }
                }
                ChessGame.wChecked = false;
            }
        }
        else
        {
            System.err.println("ERROR: KING NOT FOUND");
        }
        return false;
    }

    public boolean isMoveLegal(int xp, int yp)
    {
        return true;
    }
    
    public void dragging(int x, int y)
    {
        isDragging = true;
        this.x = x;
        this.y = y;
    }
    public void kill(){
        ps.remove(this);
    }
    public String getName()
    {
        return name;
    }
    public void updateCoordinates()
    {
        if(!isDragging)
        {
            x = xp*ChessboardPanel.squareSize;
            y = yp*ChessboardPanel.squareSize;
        }
    }
}
