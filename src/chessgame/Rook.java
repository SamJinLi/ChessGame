package chessgame;

import java.util.LinkedList;
import chessgame.util.InBetweenChecker;

public class Rook extends Piece {
    public Rook(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "rook", ps);
    }   
    @Override
    public boolean isMoveLegal(int xp, int yp) {
        // TODO Auto-generated method stub
        // System.out.println("rook destination -- xp: " + xp + ", yp: " + yp);
        if(super.xp != xp && super.yp != yp)
        {
            System.out.println("rook false");
            return false;
        }
        if(InBetweenChecker.isTherePieceInBetween(xp, yp,this))
        {
            return false;
        }

        // if(ChessGame.checked)
        // {
        //     return false;
        // }
        return true;
    }
}
