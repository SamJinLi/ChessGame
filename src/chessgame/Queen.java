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
        if((super.xp != xp && super.yp != yp) && (Math.abs(super.xp - xp) != Math.abs(super.yp - yp)))
        {
            //System.out.println("queen false 1");
            return false;
        }
        boolean isDiagnol = Math.abs(super.xp - xp) == Math.abs(super.yp - yp), condition = InBetweenChecker.checkDiagnol(xp, yp, this);
        //System.out.println("TEST :: isdiagnol = " + isDiagnol + ", condition = " + condition + ", super.xp = " + super.xp + ", super.yp = " + super.yp + ", xp = " + xp + ", yp = " + yp);
        if(isDiagnol && !condition)
        {
            //System.out.println("hahaahahah");
            return true;
        }
        if((InBetweenChecker.isTherePieceInBetween(xp, yp,this)))
        { 
            //System.out.println("queen false 2");
            return false;
        }
        // if(Math.abs(super.xp - xp) != Math.abs(super.yp - yp))
        // {
        //     //System.out.println("queen false 3");
        //     return false;
        // }
        
        return true;
    }
}
