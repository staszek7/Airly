import java.util.*;

public class DataOutput {
    private double pm10, pm25, airQualityIndex, pressure, humidity, temperature;

    AsciiFun pic = new AsciiFun();
    private static double round(double dataInput, int precision) {
        long wsp = (long)Math.pow(10, precision);
        double roundOff = (double) Math.round(dataInput * wsp) / wsp;
        return roundOff;
    }
    public void printer() {
        Date currentDate = new Date();                                                      //data
        final String DEGREE  = "\u00b0";
        System.out.println("\n \n");
        System.out.println("Curent Date and Time - " + currentDate.toString());
        System.out.print("Pm 10: " + round(pm10, 2)+" μg/m^3\n");
        System.out.print("Pm 2.5: " + round(pm25, 2)+" μg/m^3\n");
        System.out.print("Air quality index by CAQI: " + round(airQualityIndex, 2)+'\n');
        double picParameter = round(airQualityIndex, 2);

        if(picParameter >=90){pic.worst();}                                                 //obrazki
        else if(picParameter <90 && picParameter >75){pic.bad();}
        else if(picParameter <=75 && picParameter >60){pic.semi();}
        else if(picParameter <=60 && picParameter >40){pic.good();}
        else if(picParameter <=40 && picParameter >20){pic.better();}
        else if(picParameter <=20 && picParameter >0){pic.the_best();}


        System.out.println("----------------------------------------------------------\n"); //temperatura itp
        System.out.print("Pressure: " + ((double)round(pressure,0)/100)+"hPa\n");
        System.out.print("Humidity: " + round(humidity, 2)+"%\n");
        System.out.print("Temperature: " + round(temperature, 2)+DEGREE+"C");


    }
}
