package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();

        Coordinates oneForward = from.plus(getForwardStep(), 0);
        if (board.hasEmptySpace(oneForward))
            allowedMoves.add(new Move(from, oneForward));

        Coordinates twoForward = from.plus(2 * getForwardStep(), 0);
        if (board.hasEmptySpace(oneForward) && board.hasEmptySpace(twoForward) && !hasMoved() && isInStartingRow(from))
            allowedMoves.add(new Move(from, twoForward));

        Coordinates[] forwardDiagonals = {from.plus(getForwardStep(), -1), from.plus(getForwardStep(), 1)};
        for (Coordinates forwardDiagonal: forwardDiagonals)
            if (board.hasOccupiedSpace(forwardDiagonal) && board.get(forwardDiagonal).getColour() != this.getColour())
                allowedMoves.add(new Move(from, forwardDiagonal));

        return allowedMoves;
    }

    private boolean isInStartingRow(Coordinates position) {
        if (getColour() == PlayerColour.WHITE)
            return position.getRow() == Board.getRowFromTop(1);
        else
            return position.getRow() == 1;
    }
}
