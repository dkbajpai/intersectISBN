package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class PostRequest {
	
	class RequestParam {
		public String url;
		public String cookie;
		public RequestParam(String url, String cookie) {
			super();
			this.url = url;
			this.cookie = cookie;
		}
	}

	public static void httpURL() throws Exception {
		URL url = new URL("http://52.74.25.212:10160/login_security_check?");

		String userAgent = "Mozilla/5.0";

		// String encodedData = URLEncoder.encode(rawData, "UTF-8");
		String rawData = "j_username="
				+ URLEncoder.encode("comsqa@snapdeal.com", "UTF-8") + "&"
				+ "j_password=" + URLEncoder.encode("qatesting", "UTF-8");
		System.out.println("rawData..");
		System.out.println(rawData);

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
		con.connect();

		OutputStream os = con.getOutputStream();
		os.write(rawData.getBytes());
		os.flush();
		os.close();

		// System.out.println("cookie");
		Map<String, List<String>> map = con.getHeaderFields();
		System.out.println(map.get("Cookie"));
		for (String list : map.keySet()) {

			System.out.println(list + ":" + map.get(list));
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

	public static String httpApacheLogin(String url) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		post.setHeader("Connection", "keep-alive");
		
		// post.setHeader("Host", "52.74.25.212:10160");
		// post.setHeader("Referer", "http://52.74.25.212:10160/login");
		// post.setHeader("Origin", "http://52.74.25.212:10160");
		post.setHeader("Accept-Language", "en-US,en;q=0.8");
		post.setHeader(
				"User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/40.0.2214.94 Chrome/40.0.2214.94 Safari/537.36");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("j_username", "comsqa@snapdeal.com"));
		params.add(new BasicNameValuePair("j_password", "qatesting"));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);
		//System.out.println("...." + entity.getContent());
		// Execute and get the response.
		HttpResponse response = httpclient.execute(post);

		System.out.println("Response Code:"
				+ response.getStatusLine().getStatusCode());

		BufferedReader bis = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		Header[] headers = response.getAllHeaders();

		String returnURL = null;
		String cookie = null;
		for (Header header : headers) {
			System.out.println(header.getName() + ":" + header.getValue());
			
			if(header.getName().equalsIgnoreCase("Location")) {
				returnURL = header.getValue();
			}
			else if(header.getName().equalsIgnoreCase("Set-Cookie")) {
				cookie = header.getValue();
			}
		}
		
		

		// PrintWriter out = new PrintWriter(System.out);
		if (response.getEntity() != null) {
			System.out.println("if");
			System.out.println(".." + response.getEntity().getContentLength()
					+ "..." + response.getEntity().getContentType());
			String line;
			while ((line = bis.readLine()) != null) {
				System.out.print(line);
			}
		} else {
			System.out.println("else");
		}
		
		return cookie;
	}
	
	public static void httpApacheUpload(String cookie){
		
	}

	public static void main(String[] args) throws Exception {

		String cookie = httpApacheLogin("http://52.74.25.212:10160/login_security_check?");
		
		// httpURL();
	}
}
