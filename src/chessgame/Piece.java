/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.LinkedList;

import chessgame.ChessboardPanel;

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
        // //System.out.println("move xp: " + xp + ", yp: " + yp + ", other: " + (other == null ? "null" : ((other.isWhite ? "white " : "black ") + other.name)));
        // //System.out.println("isWhiteMoving == " + ChessGame.isWhiteMoving + ", isWhite == " + isWhite);
        if(ChessGame.isWhiteMoving && (!isWhite) && !ChessGame.developing)
        {
            return false;
        }
        if(other != null && other.isWhite == isWhite){
            System.err.println("Same color");
            return false;
        }
        boolean isMoveLegal = isMoveLegal(xp, yp);
        // //System.out.println("isMoveLegal piece + " + isMoveLegal);
        // int tempxp = this.xp, tempyp = this.yp;
        if(((ChessGame.wChecked && isWhite) || (ChessGame.bChecked && !isWhite)) && ((other!= null && other.isWhite != isWhite && isMoveLegal) || (other == null && xp < 8 && yp < 8 && isMoveLegal)))
        {
            //System.out.println("before..." + tempxp + ", yp = " + tempyp);
            this.xp = xp;
            this.yp = yp;
            x = xp*ChessboardPanel.squareSize;
            y = yp*ChessboardPanel.squareSize;
            //System.out.println("this supposed to be updated, this.xp = " + xp + ", yp = " + yp);
            if(other!= null && other.isWhite != isWhite && isMoveLegal)
            {
                other.kill();
            }
            // boolean isThereStillCheck = isThereStillCheck();
            // if(isThereStillCheck)
            // {
            //     this.xp = tempxp;
            //     this.yp = tempyp;
            //     x = xp * ChessboardPanel.squareSize;
            //     y = yp * ChessboardPanel.squareSize;
            //     System.out.println("HEY! Still checked");
            //     return false;
            // }
        }
        if(other!= null && other.isWhite != isWhite && isMoveLegal)
        {
            other.kill();
            this.xp=xp;
            this.yp=yp;
            x = xp*ChessboardPanel.squareSize;
            y = yp*ChessboardPanel.squareSize;
            // //System.out.println("kill piece: " + (other.isWhite ? "white " : "black ") + other.name + " and moved " + (this.isWhite ? "white " : "black ") + this.getName() + " to " + xp + yp);
            // if(isThereStillCheck())
            // {
            //     this.xp = tempxp;
            //     this.yp = tempyp;
            //     x = xp*ChessboardPanel.squareSize;
            //     y = yp*ChessboardPanel.squareSize;
            //     return false;
            // }
            return true;
        }
        if(other == null && xp < 8 && yp < 8 && isMoveLegal)
        {
            this.xp=xp;
            this.yp=yp;
            x = xp*ChessboardPanel.squareSize;
            y = yp*ChessboardPanel.squareSize;
            // //System.out.println("moved " + (this.isWhite ? "white " : "black ") + this.getName() + " to " + xp + yp);
            // if(isThereStillCheck())
            // {
            //     this.xp = tempxp;
            //     this.yp = tempyp;
            //     x = xp*ChessboardPanel.squareSize;
            //     y = yp*ChessboardPanel.squareSize;
            //     return false;
            // }
            return true;
        }
        return false;
    }

    // public boolean checkForCheckThisPiece()
    // {
    //     if(wKingIndex > -1 && bKingIndex > -1)
    //     {
    //         int wxp = ps.get(wKingIndex).xp, wyp = ps.get(wKingIndex).yp, bxp = ps.get(bKingIndex).xp, byp = ps.get(bKingIndex).yp;
    //         if(isWhite)
    //         {
    //             if(isMoveLegal(bxp, byp))
    //             {
    //                 ChessGame.bChecked = true;
    //                 System.out.println("White checked black!");
    //                 return true;
    //             }
    //         }
    //         else if(!isWhite)
    //         {
    //             if(isMoveLegal(wxp, wyp))
    //             {
    //                 ChessGame.wChecked = true;
    //                 System.out.println("Black checked white!");
    //                 return true;
    //             }
    //         }
    //     }
    //     else
    //     {
    //         System.err.println("ERROR: KING NOT FOUND");
    //     }
    //     return false;
    // }

    // public boolean isThereStillCheck()
    // {
    //     if(wKingIndex > -1 && bKingIndex > -1)
    //     {
    //         int wxp = ps.get(wKingIndex).xp, wyp = ps.get(wKingIndex).yp, bxp = ps.get(bKingIndex).xp, byp = ps.get(bKingIndex).yp;
    //         if(isWhite)
    //         {
    //             for(Piece p : ps)
    //             {
    //                 if(p.isWhite != isWhite && !p.name.equals("king") && p.isMoveLegal(bxp, byp))
    //                 {
    //                     System.out.println(p.isWhite + " " + p.name + " is checking");
    //                     return true;
    //                 }
    //             }
    //         }
    //         else if(!isWhite)
    //         {
    //             for(Piece p : ps)
    //             {
    //                 if(p.isWhite != isWhite && !p.name.equals("king") && p.isMoveLegal(bxp, byp))
    //                 {
    //                     return true;
    //                 }
    //             }
    //         }
    //     }
    //     else
    //     {
    //         System.err.println("ERROR: KING NOT FOUND");
    //     }
    //     System.out.println("Should un-check");
    //     ChessGame.wChecked = false;
    //     ChessGame.bChecked = false;
    //     return false;
    // }

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
