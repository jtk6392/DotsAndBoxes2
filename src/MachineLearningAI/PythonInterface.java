//package MachineLearningAI;
//
//import py4j.GatewayServer;
//import java.util.ArrayList;
//
///**
// * An interface that allows python to interact with java.
// */
//
//public class PythonInterface {
//    /**
//     * Outbound data.
//     */
//    private ArrayList<Integer> sendData;
//    /**
//     * Inbound data.
//     */
//    private ArrayList<Integer> recieveData;
//
//    /**
//     * Interface constructor.
//     * @param sendData The data to send to python.
//     */
//    public PythonInterface(ArrayList<Integer> sendData) {
//        sendData = sendData;
//        this.recieveData = new ArrayList<>();
//    }
//
//    /**
//     * Returns an empty arraylist for use in the python script.
//     * @return an empty integer arraylist.
//     */
//    public ArrayList<Integer> freshList(){
//        return new ArrayList<>();
//    }
//
//    /**
//     * Sets the data returned from tensorflow, for use by the python script.
//     * @param recieveData A three element arraylist.
//     */
//    public void setRecieveData(ArrayList<Integer> recieveData){
//        this.recieveData = recieveData;
//    }
//
//    /**
//     * Sets the data to send to tensorflow.
//     * @param sendData the data to be used by the tensorflow script.
//     */
//    public void setSendData(ArrayList<Integer> sendData) {
//        this.sendData = sendData;
//    }
//
//    /**
//     * Gets the data returned from tensorflow.
//     * @return An arraylist of three elements that indicates the move that the AI is making.
//     */
//    public ArrayList<Integer> getRecieveData() {
//        return this.recieveData;
//    }
//
//    /**
//     * Gets the data for tensorflow, to be used within the python script.
//     * @return An integer arraylist containing a representation of the board.
//     */
//    public ArrayList<Integer> getSendData() {
//        return this.sendData;
//    }
//
//    /**
//     * Opens the connection to python for tensorflow.
//     * @param sendData The data to send to tensorflow.
//     */
//    public void openConnection(ArrayList<Integer> sendData) {
//        GatewayServer gatewayServer = new GatewayServer(new PythonInterface(sendData));
//        gatewayServer.start();
//        System.out.println("Gateway Server Started!");
//    }
//    public static void main(String[] args) {
//        System.out.println("hi");
//    }
//}