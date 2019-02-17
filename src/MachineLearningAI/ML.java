package MachineLearningAI;

import Game.Board;
import Game.Box;

import java.io.IOException;
import java.util.ArrayList;

public class ML {
    private Board currentBoard;
    private static PythonInterface server = new PythonInterface();

    // Each box: Claimed: add 0, 1, 2
    // North > South > East > West
    private ArrayList<Integer> grabData() {
        ArrayList<Integer> data = new ArrayList<>(0);
        for(int i = 0; i < currentBoard.getBoardSize(); i++) {
            for(int j = 0; j < currentBoard.getBoardSize(); j++) {
                Box b = currentBoard.getBox(i,j);
                data.add(b.getClaimed());
                for(Box.Side s : Box.Side.values()) {
                    data.add((b.getSide(s) ? 1 : 0));
                }
            }
        }
        return data;
    }

    public void makePlay(Board currentBoard) {
        this.currentBoard = currentBoard;
        ArrayList<Integer> send = new ArrayList<>(0);

        send = grabData();
        server.setSendData(send);

        try {
            server.openServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // play function call.
    }
}
