package chessgame;

import java.util.LinkedList;

public class Pawn extends Piece {
    public boolean isStarting = true;
    public Pawn(int xp, int yp, boolean isWhite, LinkedList<Piece> ps)
    {
        super(xp, yp, isWhite, "pawn", ps);
        isStarting = true;
    }   
    @Override
    public boolean move(int xp, int yp) {
        // TODO Auto-generated method stub
        boolean result = super.move(xp, yp);
        if(super.isWhite && super.yp == 0 || !super.isWhite && super.yp == 7)
        {
            //System.out.println("promoted!");
            super.ps.add(new Queen(xp, yp, super.isWhite, ps));
            super.kill();
        }
        return result;
    }
    @Override
    public boolean isMoveLegal(int xp, int yp)
    {
        Piece other = ChessGame.getPieceByPosition(xp, yp);
        // //System.out.println("isStarting = " + isStarting + ", super.xp =" + super.xp + ", super.yp =" + super.yp + "xp:" + xp + ", yp: " + yp);
        // String name = (this.isWhite ? "white " : "black ") + this.name + " "; 
        // //System.out.println(name + "isStarting = " + isStarting + ", super.xp =" + super.xp + ", super.yp = " + super.yp + "xp:" + xp + ", yp: " + yp + "super.iswhite = " + super.isWhite + ", canbetaken = " + super.canBeTaken);
        if(isStarting && Math.abs(super.yp - yp) < 3 && super.xp == xp)
        {
            if(Math.abs(super.yp - yp) ==2)
            {
                // //System.out.println(name + "can be taken set to true");
                super.canBeTaken = true;
            }
            isStarting = false;
            return true;
        }
        if(!isStarting && other == null && ((super.isWhite && super.yp - yp == 1) || (!super.isWhite && yp - super.yp ==1)) && super.xp == xp)
        {
            return true;
        }
        if(Math.abs(super.xp - xp) == 1 && other != null && ((super.isWhite && super.yp - yp == 1) || (!super.isWhite && yp - super.yp == 1)))
        {
            //System.out.println("eat true");
            isStarting = false;
            return true;
        }
       Piece toTheRight = ChessGame.getPieceByPosition(super.xp + 1, super.yp);
       String toTheRightString = toTheRight != null ? toTheRight.name : "null";
       boolean toTheRightCanBeTaken = toTheRight != null ? toTheRight.canBeTaken : false;
       Piece toTheLeft =  ChessGame.getPieceByPosition(super.xp - 1, super.yp);
       String toTheLeftString = toTheLeft != null ? toTheLeft.name : "null";
       boolean toTheLeftCanBeTaken = toTheLeft != null ? toTheLeft.canBeTaken : false;
       boolean isNextToATakablePawn = (toTheRightString.equals("pawn") && toTheRight.isWhite != super.isWhite) || (toTheLeftString.equals("pawn") && toTheLeft.isWhite != super.isWhite);
       //System.out.println(name + "tothe right" + toTheRightString + ", totheLeft + " + toTheLeftString + ", totheright canbetaken = " + toTheRightCanBeTaken + ", totheleft canbetaken = " + toTheLeftCanBeTaken + ", condition : " + super.xp);
    //System.out.println("test: " + (yp == (super.yp-1)));    
    if
        (
            (toTheLeftCanBeTaken || toTheRightCanBeTaken) && 
            (super.isWhite && super.yp == 3 && 
                isNextToATakablePawn &&
                yp == (super.yp-1) && Math.abs(super.xp - xp) == 1
            )
            ||
            (!super.isWhite && super.yp == 4 &&
                isNextToATakablePawn &&
                yp == (super.yp+1) &&
                Math.abs(super.xp - xp) == 1
            )
        )
        {
            if(toTheLeftCanBeTaken)
            {
                toTheLeft.kill();
            }
            else
            {
                toTheRight.kill();
            }
            return true;
        }
        
        // if(ChessGame.checked)
        // {
        //     return false;
        // }
        // return true;
        return false;
    }
}
