package br.com.starwars.planeta.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;



public class ConsultaFilmeWS {
	
	
	/**
	 * Consulta PLANETAS e FIILMES e compara com o planeta informado
	 * @param nomePlaneta
	 * @return
	 * @throws Exception
	 */
	public static Collection<String> consultaFilmes(String nomePlaneta) throws Exception{
		
		Collection<String> coll = new ArrayList<String>();
		
	try {
			
			String url = "https://swapi.co/api/films/?format=json";
						
			URL obj = new URL(url);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			// int responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			
			// array de filmes
			org.json.JSONObject object = new org.json.JSONObject(response.toString());
			int qtdFilmes = object.getInt("count");
			
			for (int i = 0; i < qtdFilmes; i++) {
				// recupera os filmes
				org.json.JSONArray jArrayFilmes = (org.json.JSONArray)object.get("results");
				org.json.JSONObject objFilmes = jArrayFilmes.getJSONObject(i);
				
				// recupera os planetas e compara com o planeta cadastrado
				JSONArray jArrPlanetas = objFilmes.getJSONArray("planets");				
				for (int j = 0; j < jArrPlanetas.length(); j++) {
					if(nomePlaneta.equals(consultaNomePlaneta(jArrPlanetas.getString(j))))
					coll.add(objFilmes.getString("title"));
				}
				if(coll.isEmpty()) {
					coll.add("Esse planeta não apareceu em nenhum filme ainda.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}
	
	
	/**
	 * Recupera o nome do PLANETA
	 * @param urlSwapi
	 * @return
	 */
	private static String consultaNomePlaneta(String urlSwapi) throws Exception {
		
		String nomePlaneta = "";
		
		try {
			
			urlSwapi = urlSwapi+"?format=json";
			
			
			URL obj = new URL(urlSwapi);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			org.json.JSONObject object = new org.json.JSONObject(response.toString());
			nomePlaneta = object.getString("name");
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nomePlaneta;
		
	}
	
}
