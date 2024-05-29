package chessgame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChessboardPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChessboard(g);
    }

    public static int squareSize = 64;
    public static Image imgs[]=new Image[12];
    public static BufferedImage all = new BufferedImage(400, 1200, BufferedImage.TYPE_BYTE_GRAY);
    
    
    private void drawChessboard(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        squareSize = Math.min(width, height) / 8;
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(new Color(235,235, 208));
                } else {
                    g.setColor(new Color(119, 148, 85));
                }
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
            }
        }
        try {
            all = ImageIO.read(new File("C:\\Users\\jlisa\\Documents\\GitHub\\ChessGame\\src\\chess.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(squareSize, squareSize, BufferedImage.SCALE_SMOOTH);
                ind++;
            }    
        }

        int draggingIndex = -1;
        Piece draggingPiece = null;
        for(Piece p : ChessGame.ps)
        {
                int index=0;
                if(p.name.equalsIgnoreCase("king")){
                    index=0;
                }
                if(p.name.equalsIgnoreCase("queen")){
                    index=1;
                }
                if(p.name.equalsIgnoreCase("bishop")){
                    index=2;
                }
                if(p.name.equalsIgnoreCase("knight")){
                    index=3;
                }
                if(p.name.equalsIgnoreCase("rook")){
                    index=4;
                }
                if(p.name.equalsIgnoreCase("pawn")){
                    index=5;
                }
                if(!p.isWhite){
                    index+=6;
                }
                p.updateCoordinates();
                if(p.isDragging)
                {
                    draggingIndex = index;
                    draggingPiece = p;
                }
                g.drawImage(imgs[index], p.x, p.y, this);
            }
            if(draggingIndex > -1 && draggingPiece != null)
            {
                g.drawImage(imgs[draggingIndex], draggingPiece.x, draggingPiece.y, this);
            }
        }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintComponent(g);
    }
    public void updateKingIndex()
    {
        for(int i = 0; i < ChessGame.ps.size(); i ++)
        {
        Piece temp = ChessGame.ps.get(i);
            if(temp != null && temp.name == "king" && temp.isWhite)
            {
                Piece.wKingIndex = i;
            }
            if(temp != null && temp.name == "king" && !temp.isWhite)
            {
                Piece.bKingIndex = i;
            }
        }
        //System.out.println("updated king index");
    }
}
