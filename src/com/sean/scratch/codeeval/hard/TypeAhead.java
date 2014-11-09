package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeAhead
{
	public static final String text =
		"Mary had a little lamb its fleece was white as snow;\n" +
		"And everywhere that Mary went, the lamb was sure to go.\n" +
		"It followed her to school one day, which was against the rule;\n" +
		"It made the children laugh and play, to see a lamb at school.\n" +
		"And so the teacher turned it out, but still it lingered near,\n" +
		"And waited patiently about till Mary did appear.\n" +
		"\"Why does the lamb love Mary so?\" the eager children cry; " + 
		"\"Why, Mary loves the lamb,\nyou know\" the teacher did reply.";
	
	public static String predict(int length, String str)
	{
		String [] strs = text.split("\\s+");
		
		String [] matches = str.split("\\s+");
		
		HashMap<String,Double> table = new HashMap<String,Double>();
		
		int occurrences = 0;
		
		for( int i = 0 ; i < strs.length - length; i++ )
		{
			int j = 0;
			
			for( ; j < matches.length; j++ )
			{
				if ( i + j >= strs.length || ! strs[i + j].replaceAll("\\W+", "").equals(matches[j]) )
				{
					break;
				}
			}
			
			if ( j == matches.length )
			{
				occurrences++;
				
				String next = strs[i+length-1].replaceAll("\\W+", "");
				
				Double freq = table.get(next);
				
				if ( freq == null )
				{
					table.put(next, 1.0);
				}
				else
				{
					table.put(next, table.get(next) + 1 );
				}
			}
		}
		
		
		
		return sortTable(table, occurrences);
	}
	
	public static String sortTable(HashMap table, int occurrences)
	{
		StringBuilder sb = new StringBuilder();
		
		List<Map.Entry<String, Double>> l = new ArrayList(table.entrySet());
	    Collections.sort(l, new Comparator<Map.Entry<String, Double>>(){
	         public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
	            int x = o2.getValue().compareTo(o1.getValue());
	            
	            if ( x == 0 )
	            {
	            	x = o1.getKey().compareTo(o2.getKey());
	            }
	            
	            return x;
	        }});

		for( int i = 0; i < l.size(); i++ )
		{
			String key = l.get(i).getKey();
			
			sb.append( key + "," + String.format("%.3f", (l.get(i).getValue()/occurrences)));
			
			if ( i < l.size() - 1 )
			{
				sb.append(";");
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String [] args)
	{
		if ( args.length >= 1 )
		{
			String fileName = args[0];
			
			BufferedReader bReader = null;
			
			try
			{
				bReader = new BufferedReader(new FileReader(fileName) );
				
				String line = null;
				
				while( ( line = bReader.readLine() ) != null )
				{
					if ( !line.equals("") )
					{
						String [] strings = line.split(",");
						
						System.out.println( TypeAhead.predict(Integer.parseInt(strings[0]), strings[1].trim()));
					}
				}
			}
			catch(FileNotFoundException fnfe)
			{
				System.err.println("File not found: " + fnfe.getMessage());
			}
			catch(IOException ioe)
			{
				System.err.println("Error reading file: " + ioe.getMessage() );
			}
			finally
			{
				try
				{
					bReader.close();
				}
				catch(Exception e){}
			}
		}

	}
}
