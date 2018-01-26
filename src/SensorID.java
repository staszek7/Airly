import com.google.gson.annotations.SerializedName;

public class SensorID {
    @SerializedName("currentMeasurements") private DataOutput dataOutput;

    public void printCurrentInfo () {
        dataOutput.printer();
    }
}
