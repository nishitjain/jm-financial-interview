package com.moviedata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Movie {
	
	public String httpGetCall(String str) {
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(str);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
			   result.append(line);
			}
		}catch(Exception e) {
			System.out.println("Could not connect: "+e);
		}
		return result.toString();
	}
	
	public Object[] getMovieTitles(String substr){
		Object[] titles = null;
		try {
			String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title="+substr;
			String result = httpGetCall(url);
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(result.toString());
			Long total = (Long) jsonObj.get("total");
			Long total_pages = (Long) jsonObj.get("total_pages");
			String total_str = total.toString();
			String total_pages_str = total_pages.toString();
			titles = new String[Integer.parseInt(total_str)];
			List<String> titleList  = new ArrayList<>();
			for(int i=1; i<=Integer.parseInt(total_pages_str); i++) {
				url = "https://jsonmock.hackerrank.com/api/movies/search/?Title="+substr+"&page="+i;
				result = httpGetCall(url);
				jsonObj = (JSONObject) parser.parse(result.toString());
				JSONArray jsonArray = (JSONArray) jsonObj.get("data");
				for(int j=0; j<jsonArray.size(); j++) {
					JSONObject obj = (JSONObject) jsonArray.get(j);
					String title = (String) obj.get("Title");
					titleList.add(title);
				}
			}
			Collections.sort(titleList);
			titles = titleList.toArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return titles;
	}
	
}
