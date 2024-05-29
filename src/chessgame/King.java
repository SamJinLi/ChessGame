package chessgame;

import java.util.LinkedList;

public class King extends Piece {
    public King(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "king", ps);
    }

    @Override
    public boolean move(int xp, int yp) {
        boolean result = super.move(xp, yp);
        for(Piece p : ps)
        {
            int tempxp = super.xp, tempyp = super.yp;
            if(p.isWhite != isWhite && p.isMoveLegal(xp, yp))
            {
                System.out.println(p.isWhite + " " + p.name + " should be checking king");
                super.xp = tempxp;
                super.yp = tempyp;
                super.x = xp * ChessboardPanel.squareSize;
                super.y = yp * ChessboardPanel.squareSize;
                return false;
            }
        }
        return result;
    }

    @Override
    public boolean isMoveLegal(int xp, int yp) {
        if(Math.abs(super.xp - xp) > 1 || Math.abs(super.yp - yp) > 1)
        {
            return false;
        }
        return true;
    }
}
