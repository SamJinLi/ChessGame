package chessgame;

import java.util.LinkedList;

import chessgame.util.InBetweenChecker;

public class Queen extends Piece {
    public Queen(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "queen", ps);
    }
    @Override
    public boolean isMoveLegal(int xp, int yp) {
        // TODO Auto-generated method stub
        if(super.xp != xp && super.yp != yp)
        {
            System.out.println("rook false");
            return false;
        }
        if(InBetweenChecker.isTherePieceInBetween(xp, yp,this))
        {
            return false;
        }
        if(Math.abs(super.xp - xp) != Math.abs(super.yp - yp))
        {
            return false;
        }
        if(InBetweenChecker.checkDiagnol(xp, yp, this))
        {
            return false;
        }
        
        return true;
    }
}
