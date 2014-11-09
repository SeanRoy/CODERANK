package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordToDigit
{
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
				
				HashMap<String,Integer> map = new HashMap<String,Integer>();
				map.put("one", 1);
				map.put("two", 2);
				map.put("three", 3);
				map.put("four", 4);
				map.put("five", 5);
				map.put("six", 6);
				map.put("seven", 7);
				map.put("eight", 8);
				map.put("nine", 9);
				map.put("zero", 0);
				
				while( ( line = bReader.readLine() ) != null )
				{
					StringBuilder sb = new StringBuilder();
					
					String [] words = line.split(";");
					
					for( String word : words )
					{
						sb.append(map.get(word.trim().toLowerCase()));
					}
					
					System.out.println(sb.toString());
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
