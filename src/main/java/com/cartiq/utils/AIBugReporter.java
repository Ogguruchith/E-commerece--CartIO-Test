package com.cartiq.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class AIBugReporter {

	// ✅ Changed to gemini-1.5-flash-latest!
	private static final String API_URL = "https://generativelanguage.googleapis.com"
			+ "/v1beta/models/gemini-1.5-flash" + ":generateContent?key=";

	private static final String API_KEY = ConfigReader.getProperty("gemini.key");

	public static String analyzeBug(String testName, String errorMessage) {

		System.out.println("=== AIBugReporter Called ===");
		System.out.println("API Key: " + API_KEY);

		try {
			String prompt = "My Selenium test '" + testName + "' failed with error: " + errorMessage + ". Give me: "
					+ "1. Root cause " + "2. How to fix " + "3. How to prevent. " + "Keep it short and clear.";

			String requestBody = "{\"contents\":[{" + "\"parts\":[{" + "\"text\": \"" + prompt.replace("\"", "'")
					+ "\"}]}]}";

			URL url = new URI(API_URL + API_KEY).toURL();

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			OutputStream os = conn.getOutputStream();
			os.write(requestBody.getBytes());
			os.flush();
			os.close();

			int responseCode = conn.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			if (responseCode != 200) {
				Scanner errorScanner = new Scanner(conn.getErrorStream());
				StringBuilder errorResponse = new StringBuilder();
				while (errorScanner.hasNextLine()) {
					errorResponse.append(errorScanner.nextLine());
				}
				errorScanner.close();
				System.out.println("Error: " + errorResponse.toString());
				return "AI analysis unavailable!";
			}

			Scanner scanner = new Scanner(conn.getInputStream());
			StringBuilder response = new StringBuilder();
			while (scanner.hasNextLine()) {
				response.append(scanner.nextLine());
			}
			scanner.close();

			String result = response.toString();
			System.out.println("Full Response: " + result);

			int start = result.indexOf("\"text\": \"") + 10;
			int end = result.indexOf("\\n", start);
			if (end == -1) {
				end = result.indexOf("\"", start);
			}

			String aiResponse = result.substring(start, end);
			System.out.println("AI Analysis: " + aiResponse);

			return aiResponse;

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return "AI analysis unavailable!";
		}
	}
}
