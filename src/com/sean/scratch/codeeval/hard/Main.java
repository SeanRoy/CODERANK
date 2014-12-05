package com.sean.scratch.codeeval.hard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main
{
	public static HashMap<Character,Integer> map = new HashMap<Character,Integer>();
	static
	{
		map.put('0', 1);
		map.put('1', 2);
		map.put('2', 0);
	}
	public static int predict(long index)
	{
		StringBuilder sb = new StringBuilder();
		sb.append('0');
	
		int length = sb.length();
		
		while( index > length )
		{
			for( int i = 0; i < length; i++ )
			{
				sb.append(map.get(sb.charAt(i)));
			}
			
			length = sb.length();
		}
		
		return sb.charAt((int)index) - '0';
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
