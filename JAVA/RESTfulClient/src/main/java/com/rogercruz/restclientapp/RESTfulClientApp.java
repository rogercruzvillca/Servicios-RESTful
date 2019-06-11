package com.rogercruz.restclientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTfulClientApp {

	public static void main(String[] args) {
		try {
			//URL url = new URL("http://localhost:8080/WSREST/rest/persona/listar");
			//URL url = new URL("http://localhost:8080/WSREST/rest/persona/leer/6");
			URL url = new URL("http://172.16.144.13:8002/pkg_expiry/add_to_white_list");
			HttpURLConnection cx = (HttpURLConnection) url.openConnection();
			//cx.setRequestMethod("GET");
			cx.setRequestMethod("POST");
			//cx.setDoOutput(false);
			cx.setDoOutput(true);
			
			cx.setRequestProperty("Content-Type", "application/json");
			cx.setRequestProperty("Authorization", "Basic cm9nZXIuY3J1ekBteWFwcHMuY29tLmJvOk15QXBwczIwMTkq");
			String entrada = "{\"msisdn\":\"72701261\",\"notes\":\"Prueba\",\"start_user\":\"admin\",\"start_channel\":\"API\"}";
			
			OutputStream os = cx.getOutputStream();
			os.write(entrada.getBytes());
			os.flush();
			
			if(cx.getResponseCode() != 200) {
				throw new RuntimeException("Error HTTP : " + cx.getResponseCode()+" "+cx.getResponseMessage());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(cx.getInputStream()));
			
			String salida;
			StringBuilder sb = new StringBuilder();
			while((salida = br.readLine()) != null) {
				sb.append(salida);
			}
			
			System.out.println(sb.toString());
			
			/*Gson gson = new Gson();
			//List<Persona> lista = gson.fromJson(sb.toString(), new TypeToken<List<Persona>>(){}.getType());
			Persona per = gson.fromJson(sb.toString(), Persona.class);
			
			System.out.println(per.getIdPersona());
			System.out.println(per.getApellidos());
			System.out.println(per.getNombres());
			System.out.println(per.getFechaNac());
			System.out.println(per.getSexo());*/
			//lista.forEach(x -> System.out.println(x.getIdPersona() + " - " + x.getNombres()));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static HttpURLConnection metodoGet(String url) throws IOException {
		URL urlRETS = new URL(url);
		HttpURLConnection cx = (HttpURLConnection) urlRETS.openConnection();
		cx.setRequestMethod("GET");
		cx.setDoOutput(false);
		cx.setRequestProperty("Content-Type", "application/json");
		cx.setRequestProperty("Authorization", "Basic cm9nZXIuY3J1ekBteWFwcHMuY29tLmJvOk15QXBwczIwMTkq");
		return cx;
	}

}
