package training.chessington.model;

import training.chessington.model.pieces.*;

public class Board {
    public static final int SIZE = 8;
    private Piece[][] board = new Piece[SIZE][SIZE];

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();

        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(getRowFromTop(0), PlayerColour.WHITE);
        board.setPawnRow(1, PlayerColour.BLACK);
        board.setPawnRow(getRowFromTop(1), PlayerColour.WHITE);

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    private void setPawnRow(int rowIndex, PlayerColour colour) {
        for (int col = 0; col < Board.SIZE; col++)
            board[rowIndex][col] = new Pawn(colour);
    }

    public static int getRowFromTop(int rowIndex) {
        return Board.SIZE - 1 - rowIndex;
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public boolean hasSpace(Coordinates coords) {
        return 0 <= coords.getRow() && coords.getRow() < Board.SIZE &&
                0 <= coords.getCol() && coords.getCol() < Board.SIZE;
    }

    public boolean hasEmptySpace(Coordinates coords) {
        return hasSpace(coords) && get(coords) == null;
    }

    public boolean hasOccupiedSpace(Coordinates coords) {
        return hasSpace(coords) && get(coords) != null;
    }

    public void move(Coordinates from, Coordinates to) {
        Piece piece = get(from);
        placePiece(to, piece);
        emptySpace(from);
        piece.setMoved();
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }

    public void emptySpace(Coordinates coords) {
        board[coords.getRow()][coords.getCol()] = null;
    }
}
