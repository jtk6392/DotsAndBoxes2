package MachineLearningAI;

import py4j.GatewayServer;

import java.io.IOException;
import java.util.ArrayList;

public class PythonInterface {
    private ArrayList<Integer> sendData;
    private ArrayList<Integer> recieveData;

    public ArrayList<Integer> getRecieveData() {
        return recieveData;
    }

    public ArrayList<Integer> freshList() { return new ArrayList<Integer>(0); }

    public void setRecieveData(ArrayList<Integer> recieveData) {
        this.recieveData = recieveData;
    }

    public void setSendData(ArrayList<Integer> sendData) {
        this.sendData = sendData;
    }

    public void openServer() throws IOException {

        if(sendData.size() > 0) {
            GatewayServer gatewayServer = new GatewayServer(new PythonInterface());
            gatewayServer.start();
            Process p = Runtime.getRuntime().exec("python3 ML.py");
        }
    }
}
