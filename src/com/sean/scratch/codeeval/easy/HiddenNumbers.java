package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HiddenNumbers
{
	public static String decode(String line)
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0; i < line.length(); i++ )
		{
			int numeric = ( (int) line.charAt(i) ) - ( (int) '0' );
			
			int alpha = ( ( int ) line.charAt(i) ) - ( (int) 'a');
			
			if ( ( numeric >= 0 && numeric < 10 ) )			 
			{
				sb.append(numeric);
			}
			else if ( alpha >= 0 && alpha < 10 )
			{
				sb.append(alpha);
			}
		}
		
		if ( sb.length() == 0 )
		{
			sb.append("NONE");
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
					System.out.println(HiddenNumbers.decode(line));
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
