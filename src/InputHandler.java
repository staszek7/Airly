import com.google.gson.Gson;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;

public class InputHandler {
    private String args[];
    //z konsoli (--X)
    @Option(name = "--api-key")
    private String myApikey = "f3703718d3284713ad96c0b337d9e4b4";

    @Option(name = "--sensor-id")
    private String sensorId = null;

    @Option(name = "--latitude")
    private String latitude = null;

    @Option(name = "--longitude")
    private String longitude = null;

    private String myUrl;

    public InputHandler(String args[]) {
        this.args = args;
    }

    private void checkArguments() throws IOException {
        if (args.length == 0) {
            throw new IOException("No argument is given");                                  //sprawdza argumenty (wywolanie w argsParser)
        } else if (sensorId == null && latitude == null && longitude == null) {
            throw new IOException("Provide sensor id or latitude&longitude to get info");
        } else if (sensorId == null && latitude == null && longitude != null) {
            System.out.println("Provide latitude to get nearest sensor's info");
        } else if (sensorId == null && latitude != null && longitude == null) {
            System.out.println("Provide longitude to get nearest sensor's info");
        }
    }


    public SensorID argsParser() throws Exception {                                         //funkcja parsowania danych wejsciowych
        CmdLineParser args_Pars = new CmdLineParser(this);

        try {
            args_Pars.parseArgument(args);                                                  //obsługa błędu argumentów
            checkArguments();                                                               //wywolanie funkcji z gory, sprawdzanie arg
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            args_Pars.printUsage(System.err);
            return null;
        }
        return parseInfo();
    }


    public SensorID parseInfo() throws Exception {                                           //parsowanie danych na podstawie argumentow
        DataReader dataReader = new DataReader();
        Gson gson = new Gson();                                                              //nowy obiekt gsona

        if (!sensorId.equals("")) {                                                          //tutaj tworzy linki które beda
            myUrl = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" + sensorId;   //przetwarzane w DataReader
        } else if (!latitude.equals("") && !longitude.equals("")) {
            myUrl = "https://airapi.airly.eu/v1/mapPoint/measurements?latitude=" + latitude + "&longitude=" + longitude;
        }

        return gson.fromJson(dataReader.getSensorInfo(myUrl, myApikey), SensorID.class);
    }
}
