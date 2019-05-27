package com.rogercruz.restclientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTfulClientApp {

	public static void main(String[] args) {
		try {
			String url = "URL de servicio RESTful";
			HttpURLConnection cx = RESTfulClientApp.metodoGet(url);
			if (cx.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(cx.getInputStream()));
				String salida;
				StringBuilder sb = new StringBuilder();
				while ((salida = br.readLine()) != null) {
					sb.append(salida);
				}
				System.out.println("Salida json " + sb.toString());
			}
			System.out.println("Codigo response " + cx.getResponseCode());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
