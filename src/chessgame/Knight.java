package chessgame;

import java.util.LinkedList;

public class Knight extends Piece{
    public Knight(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "knight", ps);
    }

    @Override
    public boolean isMoveLegal(int xp, int yp) {
        if
        (
            ((xp == super.xp + 2 || xp == super.xp -2) && (yp == super.yp + 1 || yp == super.yp -1)) ||
            ((yp == super.yp + 2 || yp == super.yp -2) && (xp == super.xp + 1 || xp == super.xp -1))
        )
        {
            return true;
        }
        return false;
    }
}
