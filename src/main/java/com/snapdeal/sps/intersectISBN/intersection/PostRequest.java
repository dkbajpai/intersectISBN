package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

public class PostRequest {

	public static void main(String[] args) throws HttpException, IOException {

		URL url = new URL("http://52.74.25.212:10160/login_security_check?");

		String userAgent = "Mozilla/5.0";

		// String encodedData = URLEncoder.encode(rawData, "UTF-8");
		String rawData = "j_username="
				+ URLEncoder.encode("comsqa@snapdeal.com", "UTF-8") + "&"
				+ "j_password=" + URLEncoder.encode("qatesting", "UTF-8");
		System.out.println("rawData..");
		System.out.println(rawData);

		/*
		 * CloseableHttpClient httpclient = HttpClients.createDefault();
		 * HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");
		 */

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Host", "52.74.25.212:10160");
		con.setRequestProperty("Referer", "http://52.74.25.212:10160/login");
		con.setRequestProperty("Origin", "http://52.74.25.212:10160");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		con.setRequestProperty("Content-Length",
				String.valueOf(rawData.length()));
		con.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/40.0.2214.94 Chrome/40.0.2214.94 Safari/537.36");

		OutputStream os = con.getOutputStream();
		os.write(rawData.getBytes());
		os.flush();
		os.close();

		//System.out.println("cookie");
		Map<String, List<String>> map = con.getHeaderFields();
		System.out.println(map.get("Cookie"));
		for(String list: map.keySet()) {
			
			System.out.println(list+":"+map.get(list));
		}
		
		System.out.println();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}

	}
}
