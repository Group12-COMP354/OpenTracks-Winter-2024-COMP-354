package de.dennisguse.opentracks.data.models;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.dennisguse.opentracks.settings.UnitSystem;


public record Temperature(double temperature_c) {
    public static Temperature of(TrackPoint trackpoint) {
        double latitude = trackpoint.getLatitude();
        double longitude = trackpoint.getLongitude();
        String encodedLatitude = "";
        String encodedLongitude = "";

        try{
        encodedLatitude = URLEncoder.encode(Double.toString(latitude), "UTF-8");
        encodedLongitude = URLEncoder.encode(Double.toString(longitude), "UTF-8");}
        catch (UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }

        String apiEndpoint = "https://api.open-meteo.com/v1/forecast";

        String requestURL = apiEndpoint + "?latitude=" + encodedLatitude + "&longitude=" + encodedLongitude + "&current=temperature_2m";
        URL requestURLObject = null;
        try{
            requestURLObject = new URL(requestURL);
        }
        catch(MalformedURLException e){
            System.out.println(e.getMessage());
        }

        HttpURLConnection connection = null;
        try{
            connection = (HttpURLConnection) requestURLObject.openConnection();
            connection.setRequestMethod("GET");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        String response = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(response.toString());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject current = jsonObject.getAsJsonObject("current");
        double temperature = current.get("temperature_2m").getAsDouble();

        return new Temperature(temperature);
    }

    public double toF(){ return (temperature_c * 9.0/5.0) + 32;}

 
}
