package chessgame.util;

import java.util.LinkedList;
import chessgame.*;

public class InBetweenChecker {
     public static boolean isTherePieceInBetweenVertical(int destinationYp, int startX, int startY, Piece thePiece)
     {
        int upOrDown = (destinationYp > startY) ? 7 : 0; 
        if(destinationYp > startY)
        {
            for(int i = startY; i < upOrDown; i ++)
            {
                Piece temp = ChessGame.getPieceByPosition(startX, i);
                if(temp != null && temp.isWhite == thePiece.isWhite)
                {
                    return false;
                }   
            }
        }
        else if (destinationYp < startY)
        {
            for(int i = startY; i > upOrDown; i --)
            {
                Piece temp = ChessGame.getPieceByPosition(startX, i);
                if(temp != null && temp.isWhite == thePiece.isWhite)
                {
                    return false;
                }   
            }
        }
        return true;
     }

     public static boolean isTherePieceInBetweenHorizontal(int destinationXp, int startX, int startY, Piece thePiece)
     {
        int upOrDown = (destinationXp > startX) ? 7 : 0; 
        if(destinationXp > startX)
        {
            for(int i = startX; i < upOrDown; i ++)
            {
                Piece temp = ChessGame.getPieceByPosition(i, startY);
                if(temp != null && temp.isWhite == thePiece.isWhite)
                {
                    return false;
                }   
            }
        }
        else if (destinationXp < startX)
        {
            for(int i = startX; i > upOrDown; i --)
            {
                Piece temp = ChessGame.getPieceByPosition(i, startY);
                if(temp != null && temp.isWhite == thePiece.isWhite)
                {
                    return false;
                }   
            }
        }
        return true;
     }

     public static boolean isTherePieceInBetween(int destinationXp, int destinationYp, Piece thePiece)
     {
        boolean isVertical = (destinationXp == thePiece.xp) ? true : false;
        System.out.println("inbetween is vertical = " + isVertical);
        if(isVertical)
        {
            if(destinationYp > thePiece.yp)
            {
                for(int i = thePiece.yp+1; i < 7; i ++)
                {
                    Piece other = ChessGame.getPieceByPosition(thePiece.xp, i);
                    if(other != null && destinationYp > i)
                    {
                        System.out.println("this is bad");
                        return true;
                    }
                }
            }
            else
            {
                System.out.println("destination < yp");
                for(int i = thePiece.yp-1; i > 0; i --)
                {
                    Piece other = ChessGame.getPieceByPosition(thePiece.xp, i);
                    System.out.println("isOther null? " + other == null);
                    if(other != null && destinationYp < i)
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            if(destinationXp > thePiece.xp)
            {
                for(int i = thePiece.xp + 1; i < 7 ; i ++)
                {
                    Piece other = ChessGame.getPieceByPosition(i, destinationYp);
                    if(other != null && destinationXp > i)
                    {
                        return true;
                    }
                } 
            }
            else
            {
                for(int i = thePiece.xp - 1; i > 0; i --)
                {
                    Piece other = ChessGame.getPieceByPosition(i, destinationYp);
                    if(other != null && destinationXp < i)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
     }

     public static boolean checkDiagnol(int destinationXp, int destinationYp, Piece thePiece)
     {
        boolean goingUp = destinationYp < thePiece.yp, goingLeft = destinationXp < thePiece.xp;
        if(goingUp)
        {
            if(goingLeft)
            {
                System.out.println("going up and left");
                for(int y = thePiece.yp - 1; y >= destinationYp; y --)
                {
                    for(int x = thePiece.xp - 1; x >= destinationXp; x --)
                    {
                        Piece other = ChessGame.getPieceByPosition(x, y);
                        if(other != null && destinationXp < x && destinationYp < y)
                        {
                            return true;
                        }
                    }
                }
            }
            else
            {
                System.out.println("going up and right");
                for(int y = thePiece.yp - 1; y >= destinationYp; y --)
                {
                    for(int x = thePiece.xp + 1; x <= destinationXp; x ++)
                    {
                        Piece other = ChessGame.getPieceByPosition(x, y);
                        if(other != null && destinationXp > x && destinationYp < y)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        else
        {
            if(goingLeft)
            {
                System.out.println("going down and left");
                for(int y = thePiece.yp + 1; y <= destinationYp; y ++)
                {
                    for(int x = thePiece.xp - 1; x >= destinationXp; x --)
                    {
                        Piece other = ChessGame.getPieceByPosition(x, y);
                        if(other != null && destinationXp < x && destinationYp > y)
                        {
                            return true;
                        }
                    }
                }
            }
            else
            {
                System.out.println("going down and right");
                for(int y = thePiece.yp + 1; y <= destinationYp; y ++)
                {
                    for(int x = thePiece.xp + 1; x <= destinationXp; x ++)
                    {
                        Piece other = ChessGame.getPieceByPosition(x, y);
                        if(other != null && destinationXp > x && destinationYp > y)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("gone through all diagnal check");
        return false;
     }
}
