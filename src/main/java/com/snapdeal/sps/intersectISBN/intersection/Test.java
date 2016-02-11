package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

	public static void main(String args[]) throws IOException {
		String username = "divya.bajpai@snapdeal.com";
		String password = "snap@38@$%";
		
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(
					"http://10.41.92.43:11140/service/sacs/authenticateAndGetUser");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			JSONObject request = new JSONObject();
			request.put("email", username);
			request.put("password", password);
			request.put("responseProtocol", "PROTOCOL_JSON");
			request.put("requestProtocol", "PROTOCOL_JSON");
			JSONObject contextSRO = new JSONObject();
			contextSRO.put("requestId", "12345");
			contextSRO.put("appIdent", "SellerDispute");
			contextSRO.put("appIP", "54.254.163.29");
			contextSRO.put("apiVariantId", "A");

			request.put("contextSRO", contextSRO);

			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			out.write(request.toString());
			out.flush();
			out.close();
			StringBuilder sb = new StringBuilder();
			System.out.println("Response Code:" + connection.getResponseCode());
			boolean flag = false;
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "utf-8"));
				String line = null;

				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				System.out.println("Response sb:\n" + sb.toString());

				JSONObject response = new JSONObject(sb.toString());
				Set<String> responseKeys = response.keySet();
				
				for (String key : responseKeys) {
					System.out.println(key+":"+response.get(key));
					if (key.equalsIgnoreCase("successful")
							&& !response.getBoolean(key) == true) {
						System.out.println("Invalid user.");
						throw new Exception();
					}
					else if (key.equalsIgnoreCase("user")) {
						JSONObject user = response.getJSONObject(key);
						JSONArray jsonArray =  user.getJSONArray("resourcePermissions");
						
						for(int i = 0 ; i<jsonArray.length() ; i++) {
							JSONObject rp = jsonArray.getJSONObject(i);
							if(rp.getString("name").equalsIgnoreCase("Admin") || rp.getString("name").equalsIgnoreCase("Agent")) {
								flag = true;
								//System.out.println("......"+rp.getString("name"));
							}
						}
					}

				}

			} else {
				throw new Exception();
			}
			
			if (flag == false) {
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("false"); 
		}

		System.out.println("true");
	}
}
