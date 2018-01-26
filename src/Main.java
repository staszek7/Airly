public class Main {

    public static void main(String args[]) throws Exception {

        InputHandler inputHandler = new InputHandler(args);         //główny program
        SensorID sensorID = inputHandler.argsParser();

        if(sensorID == null)
            return;

        sensorID.printCurrentInfo();

    }

}
