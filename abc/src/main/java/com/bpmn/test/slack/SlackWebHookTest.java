package com.bpmn.test.slack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;

public class SlackWebHookTest {

	private static String webhook = "https://hooks.slack.com/services/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";
	
	@Test
	public void testSlackPostMessage() throws IOException{
		URL url = new URL(webhook);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); 
		
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setConnectTimeout(20000);
		
		String input = "{\"channel\": \"#random\", \"username\": \"rajiitg\", \"text\": \"This is posted to #random and comes from a bot named webhookbot.\", \"icon_emoji\": \":ghost:\"}";
		
		OutputStream os = connection.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		/*
		if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ connection.getResponseCode());
		}*/

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(connection.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		connection.disconnect();
	}
	
	
	public static void postSlackMessage(String text) throws IOException{
		URL url = new URL(webhook);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); 
		
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		
		String input = "{\"channel\": \"#random\", \"username\": \"rajiitg\", \"text\": \""+text+"\", \"icon_emoji\": \":ghost:\"}";
		
		OutputStream os = connection.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		
		if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ connection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(connection.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		connection.disconnect();
	}
}
