package training.chessington.model.pieces;

import training.chessington.model.PlayerColour;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;
    protected boolean moved;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
        this.moved = false;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }

    @Override
    public int getForwardStep() {
        return (getColour() == PlayerColour.WHITE ? -1 : 1);
    }

    @Override
    public boolean hasMoved() {
        return moved;
    }

    @Override
    public void setMoved() {
        moved = true;
    }
}
