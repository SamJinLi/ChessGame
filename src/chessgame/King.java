package chessgame;

import java.util.LinkedList;

public class King extends Piece {
    public King(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "king", ps);
    }

    @Override
    public boolean isMoveLegal(int xp, int yp) {
        if(Math.abs(super.xp - xp) > 1 || Math.abs(super.yp - yp) > 1)
        {
            return false;
        }
        for(Piece p : ps)
        {
            if(p.isWhite != isWhite && p.isMoveLegal(xp, yp))
            {

                return false;
            }
        }
        return true;
    }
}
