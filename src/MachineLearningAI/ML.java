//package MachineLearningAI;
//
//import Game.Board;
//import Game.Box;
//import Game.Users;
//
//import py4j.GatewayServer;
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// * A class that represents a Machine Learning AI.
// */
//public class ML {
//    private Board currentBoard;
//    private Users player = Users.PLAYER1;
//
//    /**
//     * Each box: Claimed: add 0, 1, 2
//     * Order of information: North > South > East > West
//     * @return An arraylist of integers to be passed to tensorflow.
//     */
//    private ArrayList<Integer> grabData() {
//        ArrayList<Integer> data = new ArrayList<>(0);
//        for(int i = 0; i < currentBoard.getBoardSize(); i++) {
//            for(int j = 0; j < currentBoard.getBoardSize(); j++) {
//                Box b = currentBoard.getBox(i,j);
//                data.add(b.getClaimed());
//                for(Box.Side s : Box.Side.values()) {
//                    data.add((b.getSide(s) ? 1 : 0));
//                }
//            }
//        }
//        return data;
//    }
//
//    /**
//     * Connects to a python script to determine the play to be made.
//     * @param currentBoard The current board as it stands.
//     */
//    public void makePlay(Board currentBoard) {
//        this.currentBoard = currentBoard;
//
//        GatewayServer gatewayServer = new GatewayServer(new PythonInterface(grabData()));
//        gatewayServer.start();
//
//        ArrayList<Integer> moves = gatewayServer.getRecieveData();
//        currentBoard.play(Box.Side.values()[moves.get(2)], moves.get(0), moves.get(1));
//    }
//
//    public static void main(String[] args) {
//        ML bob = new ML();
//        Board b = new Board();
//
//        bob.makePlay(b);
//    }
//}
