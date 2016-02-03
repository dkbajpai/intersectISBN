package com.snapdeal.sps.intersectISBN.intersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
	
public class FG {

	public static void main(String args[]) throws IOException {

		Set<String> rejectionsReasons = new HashSet<String>();
		List<String> isbns = new ArrayList<String>();
		File[] dir = new File("/home/divya/Documents/bool2/books_all/").listFiles();
		int count = 0;
		FileWriter writer = new FileWriter(new File("/home/divya/Documents/bool2/new.csv"));
		System.out.println("writer::::"+writer.toString());
		outer:
		for(File file: dir) {
			System.out.println(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line;
			reader.readLine();
			writer.append("------------------");
			while((line=reader.readLine()) != null) {
				String [] cols = line.split("[,]");
				rejectionsReasons.add(cols[7]);
				//cols[3].equalsIgnoreCase("rejected") &&
				//Unsupported image file extension
				//cols[7].contains("Unsupported image file extension")
				//
				if(cols[7].equals("Unsupported image file extension")) {
					//isbns.add(cols[0]);
					//writer.append(cols[0]+","+cols[7]+","+cols[1]+"\n");
					writer.append(line+"\n");
					if((count++) >= 100){
						break outer;
					}
				}
				
				
			}
			reader.close();
		}
		writer.close();		
		//writer = new FileWriter(new File("/home/divya/Documents/bool2/rejectionsReasons.csv"));
		//FileWriter writer2 = new FileWriter(new File("/home/divya/Documents/bool2/isbns.csv"));
		
		//System.out.println(rejectionsReasons.size());
		//System.out.println(isbns.size());
		//System.out.println(count);
		/*for(String val: rejectionsReasons) {
			writer.append(val+"\n");
		}*/
		
		/*for(String val:isbns) {
			writer2.append(val+"\n");
		}*/
		
		//writer.close();
		//writer2.close();
		
		}
}
