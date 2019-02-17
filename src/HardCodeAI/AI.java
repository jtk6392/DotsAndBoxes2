package HardCodeAI;

import Game.Board;
import Game.Box;
import Game.Users;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * A class that represents the AI enemy in dots and boxes.
 */
public class AI {
    private Board currentBoard;
    private Users player;
    private final static Random rng = new Random();

    /**
     * creates an instance of AI
     *
     * @param currentBoard the Board that the AI and player use
     * @param player player that makes the move
     */
    public AI(Board currentBoard, Users player) {
        this.currentBoard = currentBoard;
        this.player = player;
    }

    /**
     * checks to see if a move will create a new box with 3 claimed edges
     *
     * @param i    x coordinate of box
     * @param j    y coordinate of box
     * @param side side that we want to test
     * @return boolean good move = true
     */
    private boolean checkMove(int i, int j, Box.Side side) {
        boolean result = true;
        Box currentBox = currentBoard.getBox(i, j);
        if (currentBoard.hasPartner(currentBox, side)) {
            Box newBox = currentBoard.getPartner(currentBox, side);
            result = newBox.getClaimedSides() + 1 < 3;
        }
        return result && currentBox.getClaimedSides() + 1 < 3;
    }

    /**
     * tests to see if a box has only one unclaimed edge
     *
     * @param b Box b to check
     * @return true or false if box only has one unclaimed side
     */
    private boolean scorableBox(Box b) {
        return b.getClaimedSides() == 3;
    }

    /**
     * tests to see if a box has only one unclaimed edge
     *
     * @param i x coordinate of box
     * @param j y coordinate of box
     * @return true or false if box only has one unclaimed side
     */
    private boolean scorableBox(int i, int j) {
        return scorableBox(currentBoard.getBox(i, j));
    }


    /**
     * looks through board array and determines if there is at least one box that satisfies scorableBox
     *
     * @return true or false if move is viable
     */
    private ArrayList<Box> determinePointMove() {
        ArrayList<Box> pointMoves = new ArrayList<>(0);
        for (int i = 0; i < currentBoard.getBoardSize(); i++) {
            for (int j = 0; j < currentBoard.getBoardSize(); j++) {
                if (scorableBox(i, j)) {
                    pointMoves.add(this.currentBoard.getBox(i, j));
                }
            }
        }
        return pointMoves;
    }

    /**
     * looks through Board array and determines if there is at least one move that satisfies checkMove
     *
     * @return true or false if move is viable
     */
    private boolean determineSafeMove() {
        for (int i = 0; i < currentBoard.getBoardSize(); i++) {
            for (int j = 0; j < currentBoard.getBoardSize(); j++) {
                for (Box.Side s : Box.Side.values()) {
                    if (checkMove(i, j, s)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * selects a random move that will not cause the other player to have a scorable box
     */
    private boolean randomMove() {
        int i, j, max, s;
        Box.Side side = null;
        max = this.currentBoard.getBoardSize();
        i = rng.nextInt(max);
        j = rng.nextInt(max);
        s = rng.nextInt(4);
        switch (s) {
            case 0:
                side = Box.Side.EAST;
                break;
            case 1:
                side = Box.Side.SOUTH;
                break;
            case 2:
                side = Box.Side.WEST;
                break;
            case 3:
                side = Box.Side.NORTH;
        }
        if (checkMove(i, j, side)) {
            this.currentBoard.play(side, this.player, i, j);
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns ENUM for open side given a box
     * precondition: the box only has one open side
     *
     * @param b a box
     * @return the side
     */
    public Box.Side getOpenSide(Box b) {
        for (Box.Side side : Box.Side.values()) {
            if (!b.getSide(side)) {
                return side;
            }
        }
        return null;
    }

    public int playChain(Box b) {
        return playChain(b, -1);
    }

    public void advancedStrat(Box b) {
        Box.Side startSide = getOpenSide(b);
        Box partner = currentBoard.getPartner(b, startSide);
        Box.Side partnerSide;

        switch (startSide) {
            case EAST:
                partnerSide = Box.Side.WEST;
                break;
            case WEST:
                partnerSide = Box.Side.EAST;
                break;
            case NORTH:
                partnerSide = Box.Side.SOUTH;
                break;
            case SOUTH:
                partnerSide = Box.Side.NORTH;
                break;
                
        }

        for (Box.Side side : Box.Side.values()) {
            if (!partner.getSide(side) && side != ) {

            }
        }        Box.Side partnerSide =hfhgf

        currentBoard.play(partnerSide, this.player, newBox.get)
    }

    public int playChain(Box b, int stop) {
        int numMoves = 0;
        boolean lastMove = false;
        while (!lastMove && numMoves < stop) {
            Box.Side side = getOpenSide(b);
            Box tempBox = b;
            if (currentBoard.hasPartner(b, side)) {
                Box nextBox = currentBoard.getPartner(b, side);
                b = nextBox;
                lastMove = false;
            } else {
                lastMove = true;
            }
            this.currentBoard.play(side, this.player, b.getxVal(), b.getyVal());

            numMoves += 1;
        }
        if (numMoves == stop) {

        }

        return numMoves;
    }

    public Board duplicateBoard(Board gameBoard) {
        int n = gameBoard.getBoardSize();
        Board clone = new Board();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone.setBox(i, j, gameBoard.getBox(i, j));
            }
        }
        clone.setScore(gameBoard.getScore());
        return clone;
    }

    /**
     * complete AI logic
     *
     * @param currentBoard board that the AI plays on
     */
    public void makePlay(Board currentBoard) {
        this.currentBoard = currentBoard;
        boolean madeMove = false;
        while (!madeMove) {
            ArrayList<Box> pointMoves = determinePointMove();

            if (pointMoves.size() <= 0 && !determineSafeMove() && !madeMove) {
                //
                madeMove = true;
            }

            if (pointMoves.size() > 0 && !determineSafeMove() && !madeMove) {

                madeMove = true;
            }

            if (pointMoves.size() > 0 && determineSafeMove() && !madeMove) {
                for (Box move : pointMoves) {
                    Box.Side side = getOpenSide(move);
                    this.currentBoard.play(side, this.player, move.getxVal(), move.getyVal());
                }
                madeMove = true;
            }

            if (pointMoves.size() <= 0 && determineSafeMove() && !madeMove) {
                randomMove();
                madeMove = true;
            }
        }
    }

    public static void main(String[] args) {
        boolean madePlay;
        AI HAL = new AI(new Board(2), Users.PLAYER1);
        for (int i = 0; i < 4; i++) {
            madePlay = false;
            while (!madePlay) {
                madePlay = HAL.randomMove();
            }
        }
        HAL.currentBoard.completePrint();
    }
}

