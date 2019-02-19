package Game;

public class Board implements Cloneable {

    /**
     * Sets the current player.
     */
    private Users currentPlayer = Users.PLAYER1;

    /**
     * Creates the box array for the i and j index.
     */
    private Box[][] boxArray;

    /**
     * Creates score declaration.
     */
    private int[] score;

    /**
     * Creates a standard size for the board if the user does not select
     * a size.
     * Board Size: 4 x 4
     */
    public Board() {
        this(4);
    }

    /**
     * score[0]: total player 1
     * score[1]: total player 2
     * score[2]: total score
     *
     * @param n The length of the sides of the board.
     */
    public Board(int n) {
        boxArray = new Box[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boxArray[i][j] = new Box(i, j);
            }
        }
        score = new int[3];
        score[2] = n * n;  // total score
    }

    /**
     * returns the dimesion of the board
     *
     * @return int
     */
    public int getBoardSize() {
        return this.boxArray.length;
    }

    /**
     * returns score array
     *
     * @return int[]
     */
    public int[] getScore() {
        return this.score;
    }

    /**
     * sets the score to the desired array
     *
     * @param score new array to overwrite old one
     */
    public void setScore(int[] score) {
        this.score = score;
    }


    /**
     * Returns a box at a given index in the board.
     *
     * @param i the column of the box
     * @param j the row of the box.
     * @return A box.
     */
    public Box getBox(int i, int j) {
        return boxArray[i][j];
    }


    /**
     * overwrite a box at i, j with Box b
     *
     * @param i 1 dimension in array
     * @param j 2 dimension in array
     * @param b b to replace old box
     */
    public void setBox(int i, int j, Box b) {
        this.boxArray[i][j] = b;
    }


    /**
     * Checks if a box at a specific index has an adjacent side.
     *
     * @param i the column of the box
     * @param j the row of the box
     * @param s the side to check
     * @return True if there is a box adjacent to the current box.
     */
    public boolean hasPartner(int i, int j, Box.Side s) {
        return hasPartner(getBox(i, j), s);
    }

    /**
     * Checks if a box at a specific index has an adjacent side.
     *
     * @param b the box to check for adjacent
     * @param s the side to check.
     * @return True if there is a box adjacent to the current box.
     */
    public boolean hasPartner(Box b, Box.Side s) {
        switch (s) {
            case EAST:
                return b.getxVal() < getBoardSize() - 1; //returns true if east edge of board
            case WEST:
                return b.getxVal() > 0;//returns true if west edge of board
            case NORTH:
                return b.getyVal() > 0;//returns true if north edge of board
            case SOUTH:
                return b.getyVal() < getBoardSize() - 1;//return true if south edge of board
            default:
                return false; // Should never reach
        }
    }

    /**
     * Overload of getPartner
     *
     * @param i the column of the box
     * @param j the row of the box
     * @param s the side to check
     * @return The 'partner' box
     */
    public Box getPartner(int i, int j, Box.Side s) {
        return getPartner(getBox(i, j), s);
    }

    /**
     * Gets the partner to the box.
     *
     * @param b the box to check for partners
     * @param s the side to check.
     * @return a box object that is adjacent to b.
     */
    public Box getPartner(Box b, Box.Side s) {
        switch (s) {
            case EAST:
                return this.getBox(b.getxVal() + 1, b.getyVal());
            case WEST:
                return this.getBox(b.getxVal() - 1, b.getyVal());
            case NORTH:
                return this.getBox(b.getxVal(), b.getyVal() - 1);
            case SOUTH:
                return this.getBox(b.getxVal(), b.getyVal() + 1);
            default:
                return null; // Should never reach
        }
    }


    /**
     * Checks for the shared side and sees if the side is the boarder of the box
     *
     * @param s the side being checked
     * @param b box
     */
    public void claimSide(Box.Side s, Box b) {
        b.setSide(s);
        b.checkClaimed(this.currentPlayer);
        if (hasPartner(b, s)) {
            Box partner = getPartner(b, s);
            switch (s) {
                case NORTH:
                    partner.setSide(Box.Side.SOUTH);
                    break;
                case SOUTH:
                    partner.setSide(Box.Side.NORTH);
                    break;
                case EAST:
                    partner.setSide(Box.Side.WEST);
                    break;
                case WEST:
                    partner.setSide(Box.Side.EAST);
                    break;
            }
            partner.checkClaimed(this.currentPlayer);

        }
    }

    /**
     * updates the score after checking a boxes state
     * @param i 1 dimension of box
     * @param j 2 dimension of box
     * @return
     */
    public boolean updateScore(int i, int j) {
        int claim = boxArray[i][j].getClaimed();
        if (claim != 0) {
            this.score[claim - 1]++;
            return true;
        }
        return false;
    }


    /**
     * This checks the number of boxes
     * When there are no moves are
     * <p>
     * if player 1 claimed = player 2 claim then it equals the number of boxes
     */
    public static void Win(Users p1, Users p2, Box b) {

    }

    /**
     * prints the entire board
     */
    public void completePrint() {
        for (int j = 0; j < this.boxArray.length; j++) {
            String line1 = "";
            String line2 = "";
            String line3 = "";
            for (int i = 0; i < this.boxArray.length; i++) {
                Box currentBox = getBox(i, j);
                line1 += currentBox.getSide(Box.Side.NORTH) ? "---" : "   ";
                line2 += currentBox.getSide(Box.Side.WEST) ? "|" : " ";
                line2 += currentBox.getClaimed() == 0 ? "0" : currentBox.getClaimed() == 1 ? "1" : "2";
                line2 += currentBox.getSide(Box.Side.EAST) ? "|" : " ";
                line3 += currentBox.getSide(Box.Side.SOUTH) ? "---" : "   ";
            }
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
        }
    }


    /**
     * Returns a string representation of the board.
     *
     * @return An n*n string representing the board for debugging.
     */
    @Override
    public String toString() {
        String outString = "";
        int[] claims = new int[this.boxArray.length * this.boxArray.length];
        for (int i = 0; i < this.boxArray.length; i++) {
            for (int j = 0; j < this.boxArray.length; j++) {
                claims[i + j] = this.boxArray[i][j].getClaimed();
            }
        }
        int k = 0;
        for (int i = 0; i < claims.length; i++) {
            outString += claims[i] + " ";
            k++;
            if (k == this.boxArray.length) {
                outString += "\n";
                k = 0;
            }
        }
        return outString;
    }


    /**
     * Sets the state of the size of the box to true
     * and update and check and see if a box if finished.
     * If user tries to claim a side taken return false
     *
     * @param bs     box side
     * @param i      index of the row
     * @param j      index of the column
     * @return true or false
     */
    public boolean play(Box.Side bs, int i, int j) {
        boolean boxSide = boxArray[i][j].getSide(bs);
        Box box1 = boxArray[i][j];
        boolean score2 = false;
        if (!boxSide) {

            claimSide(bs, box1);
            boolean score1 = this.updateScore(i, j);


            if (this.hasPartner(box1, bs)) {
                Box box2 = this.getPartner(box1, bs);
                score2 = this.updateScore(box2.getxVal(), box2.getyVal());
            }

            if (!score1 && !score2) {
                this.currentPlayer = this.currentPlayer == Users.PLAYER1 ? Users.PLAYER2 : Users.PLAYER1;
            }

            return true;
        }
        return false;
    }

    /**
     * first test case, expected result:
     * test
     * 2 0 0 0
     * 0 0 0 0
     * 0 0 0 0
     * 0 0 0 0
     *
     * 2 1 0 0
     * 0 0 0 0
     * 0 0 0 0
     * 0 0 0 0
     *
     * ------
     * |2||1||0  0
     * ------
     * ------
     * 0  0  0  0
     *
     *
     * 0  0  0  0
     *
     *
     * 0  0  0  0
     */
    public static void test1() {
        Board b = new Board();
        System.out.println("test");

        Users p1 = Users.PLAYER1;
        Users p2 = Users.PLAYER2;

        b.play(Box.Side.EAST, 0, 0);
        b.play(Box.Side.NORTH, 0, 0);
        b.play(Box.Side.SOUTH, 0, 0);
        b.play(Box.Side.WEST, 0, 0);
        System.out.println(b);

        b.play(Box.Side.NORTH, 1, 0);
        b.play(Box.Side.EAST, 1, 0);
        b.play(Box.Side.SOUTH, 1, 0);
        System.out.println(b);
        b.completePrint();
    }


    /**
     * Test function for board.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        test1();
    }
}