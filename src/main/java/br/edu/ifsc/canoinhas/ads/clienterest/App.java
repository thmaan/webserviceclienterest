package br.edu.ifsc.canoinhas.ads.clienterest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class App 
{
	
	public static void post() {
		try {
				
			URL url = new URL("http://localhost:5000/inserir-dados");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-type", "application/json");			
			conn.setDoOutput(true);
			
			String randomTemp = ""+ String.format("%.2f", getRandomNumber());
			String randomUmi = ""+ String.format("%.2f", getRandomNumber());;
			String randomLumi = ""+ String.format("%.2f", getRandomNumber());
			
			String id = "{\"id\":\"" + 1 + "\",";
			String temperatura ="\"temperatura\":\"" + randomTemp + "\",";
			String umidade ="\"umidade\":\"" + randomUmi + "\",";
			String luminosidade="\"luminosidade\":\"" + randomLumi + "\"}";
		
					
			String jsonInputString = id+temperatura+umidade+luminosidade;
						
			try {
				OutputStream os = conn.getOutputStream();
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	
	public static void get() {
		try {
		
			URL url = new URL("http://localhost:5000/dados-filtrados-datahora");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-type", "application/json");			
			conn.setDoOutput(true);
			String datahora = "{\"datahora\":\"" +"10/02/2021 02:44:00 PM"+ "\"}";
			//String id = "{\"id\":\"" + 1 + "\"}";
			String jsonInputString =datahora ;
			System.out.println(datahora);
			try {
				OutputStream os = conn.getOutputStream();
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	
	public static void delete() {
		try {
		URL url = new URL("http://localhost:5000/dados/3");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	} catch (MalformedURLException e) {

		e.printStackTrace();

	} catch (IOException e) {

		e.printStackTrace();

	}
	}
	public static void put() {
		try {
			URL url = new URL("http://localhost:5000/dados/1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-type", "application/json");			
			conn.setDoOutput(true);
			
			String randomTemp = ""+ String.format("%.2f", getRandomNumber());
			String randomUmi = ""+ String.format("%.2f", getRandomNumber());;
			String randomLumi = ""+ String.format("%.2f", getRandomNumber());
			
			String id = "{\"id\":\"" + 1 + "\",";
			String temperatura ="\"temperatura\":\"" + randomTemp + "\",";
			String umidade ="\"umidade\":\"" + randomUmi + "\",";
			String luminosidade="\"luminosidade\":\"" + randomLumi + "\"}";
					
			String jsonInputString = id+temperatura+umidade+luminosidade;

			try {
				OutputStream os = conn.getOutputStream();
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
	public static double getRandomNumber() {
		double min =1.0;
		double max =50.0;
		double number = (Math.random()*((max-min)+1))+min;
		
		return number;
	}
	public static void main( String[] args )
    {
		Thread thr1;
		
		thr1= Thread.currentThread();
		try {
			while(true) {
			post();
			Thread.sleep(10000);
			}
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		
		//get();
		
		
		//delete();
		//put();
        
    }
}
