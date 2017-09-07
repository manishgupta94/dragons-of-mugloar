package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpClient implements HttpClient {

	public MyHttpClient() {}

	@Override
	public String makeGetRequest(String url) throws IOException {
		String result = "";
		URL urlObj = new URL(url);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlObj.openStream(), "UTF-8"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
		}
		return result;
	}

	@Override
	public String makePostRequest(String url, String data) throws IOException {
		String result = "";
		URL urlObj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8")) {
			writer.write(data);
			writer.flush();
		}
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
			String line;
			while ((line = reader.readLine()) != null)
				result += line;
		}
		return result;
	}
}
