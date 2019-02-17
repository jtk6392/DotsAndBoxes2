package MachineLearningAI;

import py4j.GatewayServer;

import java.util.ArrayList;

public class PythonInterface {
    private ArrayList<Integer> sendData;
    private ArrayList<Integer> recieveData;

    public ArrayList<Integer> getRecieveData() {
        return recieveData;
    }

    public void setRecieveData(ArrayList<Integer> recieveData) {
        this.recieveData = recieveData;
    }

    public void setSendData(ArrayList<Integer> sendData) {
        this.sendData = sendData;
    }

    public boolean openServer() {
        if(sendData.size() > 0) {
            GatewayServer gatewayServer = new GatewayServer(new PythonInterface());
            gatewayServer.start();
            if(recieveData.size() > 0){
                sendData = new ArrayList<>(0);
                return true;
            }
        }
    }

}
