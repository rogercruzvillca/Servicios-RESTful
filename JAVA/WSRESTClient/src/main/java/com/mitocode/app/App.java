package com.mitocode.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.mitocode.model.Persona;

public class App {

	public static void main(String[] args) {
		try {
			//URL url = new URL("http://localhost:8080/WSREST/rest/persona/listar");
			//URL url = new URL("http://localhost:8080/WSREST/rest/persona/leer/6");
			URL url = new URL("http://localhost:8080/WSREST/rest/persona/modificar/1");
			HttpURLConnection cx = (HttpURLConnection) url.openConnection();
			//cx.setRequestMethod("GET");
			cx.setRequestMethod("PUT");
			//cx.setDoOutput(false);
			cx.setDoOutput(true);
			
			cx.setRequestProperty("Content-Type", "application/json");
			
			//  \" = ""
			String entrada = "{\"nombres\":\"Mitos\",\"apellidos\":\"Medinas\",\"fechaNac\":\"21/01/1991\",\"sexo\":\"M\",\"direccion\":\"xyz\"}";
			
			OutputStream os = cx.getOutputStream();
			os.write(entrada.getBytes());
			os.flush();
			
			if(cx.getResponseCode() != 200) {
				throw new RuntimeException("Error HTTP : " + cx.getResponseCode());
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
}
