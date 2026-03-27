package com.example;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;

    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;

        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir") + img_file));
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public boolean getColor() {
        return color;
    }

    public Image getImage() {
        return img;
    }

    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();

        g.drawImage(this.img, x, y, null);
    }

    // TO BE IMPLEMENTED!
    // return a list of every square that is "controlled" by this piece. A square is
    // controlled
    // if the piece capture into it legally.

    // Precondition: The board and starting square are valid and inside the board
    // limits.
    // Postcondition: Returns a list of all squares controlled by the piece from the
    // starting square.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

        ArrayList<Square> controlled = new ArrayList<>();

        //int row = start.getRow();
        //int col = start.getCol();

        // Only allow moves within board boundaries
       // int maxRow = board.length;
       // int maxCol = board[0].length;

        // Diagonal left
       // if (row + 1 < maxRow && col - 1 >= 0) {
            //controlled.add(board[row + 1][col - 1]);
        //}

        // Diagonal right
       // if (row + 1 < maxRow && col + 1 < maxCol) {
        //    controlled.add(board[row + 1][col + 1]);
      //  }
        if (start.getRow()<7){
            controlled.add(board[start.getRow()+2][start.getCol()+2]);
            controlled.add(board[start.getRow()+2][start.getCol()-2]);
        }

        return controlled;
    }

    // TO BE IMPLEMENTED!
    // implement the move function here
    // it's up to you how the piece moves, but at the very least the rules should be
    // logical and it should never move off the board!
    // returns an arraylist of squares which are legal to move to
    // please note that your piece must have some sort of logic. Just being able to
    // move to every square on the board is not
    // going to score any points.

    // Precondition: The board and starting square are valid and within the board’s
    // limits.
    // Postcondition: A list of possible legal moves from the starting square is
    // returned.
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        ArrayList<Square> moves = new ArrayList<>();
        if (color == true) {
            if (start.getRow() < 7) {

                Square up = b.getSquareArray()[start.getRow() + 1][start.getCol()];
                moves.add(up);

                // check diagonal left
                if (start.getCol()-2 >= 0 && start.getRow()+2<8 && b.getSquareArray()[start.getRow() + 2][start.getCol() - 2].isOccupied()
                        && b.getSquareArray()[start.getRow() + 2][start.getCol() - 2].getOccupyingPiece()
                                .getColor() != color) {
                    Square downLeft = b.getSquareArray()[start.getRow() + 2][start.getCol() - 2];
                    moves.add(downLeft);
                }
                // check diagonal right

                if (start.getCol()+2<8 && start.getRow()+2 <8 && b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].isOccupied()
                        && b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].getOccupyingPiece()
                                .getColor() != color) {
                    Square downRight = b.getSquareArray()[start.getRow() + 2][start.getCol() + 2];
                    moves.add(downRight);
                }

            }
        } else {

            if (start.getRow() > 0) {

                Square down = b.getSquareArray()[start.getRow() - 1][start.getCol()];
                moves.add(down);

                // check diagonal left
                if (start.getCol() > 0 && b.getSquareArray()[start.getRow() - 2][start.getCol() + 2].isOccupied()
                        && b.getSquareArray()[start.getRow() - 2][start.getCol() + 2].getOccupyingPiece()
                                .getColor() != color) {
                    Square upLeft = b.getSquareArray()[start.getRow() - 2][start.getCol() + 2];
                    moves.add(upLeft);
                }
                // check diagonal right

                if (start.getCol() > 0 && b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].isOccupied()
                        && b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].getOccupyingPiece()
                                .getColor() != color) {
                    Square upRight = b.getSquareArray()[start.getRow() + 2][start.getCol() + 2];
                    moves.add(upRight);
                }
            }

        }
        return moves;
    }
}
