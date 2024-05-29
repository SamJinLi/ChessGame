package chessgame;

import java.util.LinkedList;

import chessgame.util.InBetweenChecker;

public class Bishop extends Piece {
    public Bishop(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "bishop", ps);
    }

    @Override
    public boolean isMoveLegal(int xp, int yp) {
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
