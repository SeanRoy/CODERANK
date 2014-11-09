package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UpperCaseLowerCaseRatio
{
	public static final String formatString = "%1$.2f";
	public static String upperLower(String line)
	{
		double upper = 0;
		double lower = 0;
		double total = 0;
		
		for( int i = 0; i < line.length(); i++ )
		{
			if ( Character.isUpperCase(line.charAt(i)) )
			{
				upper++;
			}
			else
			{
				lower++;
			}
			
			total++;
		}
		
		double upperPercent = (upper / total) * 100;
		double lowerPercent = (lower / total) * 100;
		
		return "lowercase: " + String.format(formatString, lowerPercent) + 
			   " uppercase: " + String.format(formatString, upperPercent);
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
					System.out.println(UpperCaseLowerCaseRatio.upperLower(line));
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
