import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class DataReader {                                                               //klasa DataReader, tylko 1 funkcja
    public String getSensorInfo(String myUrl, String myApikey) throws Exception {       //w tej klasie program łączy sie
                                                                                        // i pobiera dane ze strony
        StringBuilder string_build = new StringBuilder();                               //deklaracja obiektow potrzebnych
        String current_line;
        BufferedReader reader;
        URL link = new URL(myUrl);


        HttpURLConnection conn = (HttpURLConnection) link.openConnection();             //nowe połączenie, otwarcie połączenia
        conn.setRequestMethod("GET");                                                   //pobieranie danych
        conn.setRequestProperty("Accept", "application/json");                          //tu
        conn.setRequestProperty("apikey", myApikey);                                    //i tu - automatyczne odpowiedzi na zapytania od serweru http
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((current_line = reader.readLine()) != null) {                        //sciaganie danych do momentu aż sie nie skonczy
            string_build.append(current_line);                                      //append - dodawanie danych w miejscu zakonczenia
        }                                                                           //poprzedniej akcji
        reader.close();
        return string_build.toString();                                             //zwraca dane w postaci stringu
    }
}