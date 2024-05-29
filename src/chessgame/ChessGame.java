/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;

/**
 *
 * @author Khouiled
 */
public class ChessGame {
    
    public static boolean developing = true;
    public static LinkedList<Piece> ps=new LinkedList<>();
    public static Piece selectedPiece = null;
    public static int scaleFactor = ChessboardPanel.squareSize;
    public static Scanner scan = new Scanner(System.in);
    public static int previousX = 0, previousY = 0;

    public static boolean isWhiteMoving = true, wChecked = false, bChecked = false;

    public static void main(String[] args) throws IOException {
        Piece brook=new Rook(0, 0, false, ps);
        Piece bkinght=new Knight(1, 0, false, ps);
        Piece bbishop=new Bishop(2, 0, false, ps);
        Piece bqueen=new Queen(3, 0, false, ps);
        Piece bking=new King(4, 0, false, ps);
        Piece bbishop2=new Bishop(5, 0, false, ps);
        Piece bkight2=new Knight(6, 0, false, ps);
        Piece brook2=new Rook(7, 0, false, ps);
        Piece bpawn1=new Pawn(1, 1, false, ps);
        Piece bpawn2=new Pawn(2, 1, false, ps);
        Piece bpawn3=new Pawn(3, 1, false, ps);
        Piece bpawn4=new Pawn(4, 1, false, ps);
        Piece bpawn5=new Pawn(5, 1, false, ps);
        Piece bpawn6=new Pawn(6, 1, false, ps);
        Piece bpawn7=new Pawn(7, 1, false, ps);
        Piece bpawn8=new Pawn(0, 1, false, ps);
        
        Piece wrook=new Rook(0, 7, true, ps);
        Piece wkinght=new Knight(1, 7, true, ps);
        Piece wbishop=new Bishop(2, 7, true, ps);
        Piece wqueen=new Queen(3, 7, true, ps);
        Piece wking=new King(4, 7, true, ps);
        Piece wbishop2=new Bishop(5, 7, true, ps);
        Piece wkight2=new Knight(6, 7, true, ps);
        Piece wrook2=new Rook(7, 7, true, ps);
        Piece wpawn1=new Pawn(1, 6, true, ps);
        Piece wpawn2=new Pawn(2, 6, true, ps);
        Piece wpawn3=new Pawn(3, 6, true, ps);
        Piece wpawn4=new Pawn(4, 6, true, ps);
        Piece wpawn5=new Pawn(5, 6, true, ps);
        Piece wpawn6=new Pawn(6, 6, true, ps);
        Piece wpawn7=new Pawn(7, 6, true, ps);
        Piece wpawn8=new Pawn(0, 6, true, ps);
        
        JFrame frame = new JFrame("Chessboard");
        frame.setBounds(10, 10, 550, 560);
        frame.setUndecorated(false);
        frame.setAlwaysOnTop(true);
        
        ChessboardPanel pn = new ChessboardPanel();
        pn.updateKingIndex();
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece != null)
                {
                    selectedPiece.dragging(e.getX() -35 - (ChessboardPanel.squareSize/10), e.getY()-60 - (ChessboardPanel.squareSize/10));
                    pn.repaint();
                    frame.repaint();
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {}
            
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX(), e.getY());
                if( !developing && ((!selectedPiece.isWhite && isWhiteMoving) || (selectedPiece.isWhite && !isWhiteMoving)))
                {
                    selectedPiece = null;
                }
                System.out.println(selectedPiece != null ? ((selectedPiece.isWhite ? "white " : "black ") + selectedPiece.getName()) : "no selectedpiece -- is null");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                if(selectedPiece!=null)
                {
                    int xp = (e.getX()- 8)/(ChessboardPanel.squareSize);
                    int yp = (e.getY() - 31)/(ChessboardPanel.squareSize);
                    boolean moveWasLegal = selectedPiece.move(xp, yp);
                    System.out.println("was move legal chessgame: " + moveWasLegal);
                    if(moveWasLegal)
                    {
                        isWhiteMoving =  !isWhiteMoving;
                        for(Piece p : ps)
                        {
                            p.canBeTaken = isWhiteMoving && p.isWhite ? false : !isWhiteMoving && !p.isWhite ? false : p.canBeTaken;
                        }
                    }
                    pn.repaint();
                    frame.repaint();
                    pn.updateKingIndex();
                }
                else
                {
                    System.out.println("selectedPieceIsNull");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static Piece getPiece(int x, int y)
    {
        int xp = (x - 8)/(ChessboardPanel.squareSize);
        int yp = (y - 31)/(ChessboardPanel.squareSize);
        System.out.println("getPiece--X: " + xp + ", y: " + yp);
        for(Piece p : ps)
        {
            if(p.xp == xp && p.yp == yp)
            {
                return p;
            }
        }   
        return null;
    }

    public static Piece getPieceByPosition(int xp, int yp)
    {
        for(Piece p : ps)
        {
            if(p.xp == xp && p.yp == yp)
            {
                return p;
            }
        }   
        return null;
    }
}
