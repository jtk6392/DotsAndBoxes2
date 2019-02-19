//package MachineLearningAI;
//
//import Game.Board;
//import Game.Box;
//import Game.Users;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class ML {
//    private Board currentBoard;
//    private static PythonInterface server = new PythonInterface();
//    private Users player = Users.PLAYER1;
//
//    // Each box: Claimed: add 0, 1, 2
//    // North > South > East > West
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
//    public void makePlay(Board currentBoard) {
//        this.currentBoard = currentBoard;
//        ArrayList<Integer> send = new ArrayList<>(0);
//
//        send = grabData();
//        server.setSendData(send);
//
//        try {
//            server.openServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ArrayList<Integer> moves = server.getRecieveData();
//        currentBoard.play(Box.Side.values()[moves.get(2)], this.player, moves.get(0), moves.get(1));
//    }
//
//    public static void main(String[] args) {
//        ML bob = new ML();
//        Board b = new Board();
//
//        bob.makePlay(b);
//    }
//}
