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

        Coordinates oneSpaceForward = from.plus(getForwardStep(), 0);
        if (board.hasEmptySpace(oneSpaceForward))
            allowedMoves.add(new Move(from, oneSpaceForward));

        Coordinates twoSpacesForward = from.plus(2 * getForwardStep(), 0);
        if (board.hasEmptySpace(twoSpacesForward) && !hasMoved() && isInInitialRow(from))
            allowedMoves.add(new Move(from, twoSpacesForward));

        return allowedMoves;
    }

    private boolean isInInitialRow(Coordinates position) {
        if (getColour() == PlayerColour.WHITE)
            return position.getRow() == 6;
        else
            return position.getRow() == 1;
    }
}
