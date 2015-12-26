package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import diversas.DirectionsGoogle;

public class GoogleMapsDirections {
	public DirectionsGoogle result;
	
	public void carregaDirections(String Origem, String Destino){
		Gson gson = new Gson();
		try {
			result = gson.fromJson(jsonCoord(URLEncoder.encode(Origem, "UTF-8"),URLEncoder.encode(Destino, "UTF-8")),DirectionsGoogle.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String jsonCoord(String Origem, String Destino) throws IOException {
		URL url = new URL(
				"http://maps.google.es/maps/api/directions/json?origin="+Origem+"&destination="+Destino+"&sensor=false");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String inputLine;
		String jsonResult = "";
		while ((inputLine = in.readLine()) != null) {
			jsonResult += inputLine;
		}
		in.close();
		return jsonResult;
	}

}
