package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BeautifulStrings
{

	public static int [] getFrequencies( String line )
	{
		char [] chars = line.toCharArray();
		int []frequencies = new int [26];
		
		for( int i = 0; i < chars.length; i++ )
		{
			int index = ( ( int ) chars[i] ) - ( ( int ) 'a');
			
			if ( index >= 0 && index < 26 )
			{
				frequencies[index]++;
			}
		}
		
		return frequencies;
	}
	
	public static int getMax(String line)
	{
		char [] chars = line.toCharArray();
		int[] frequencies = getFrequencies(line);
		
		Arrays.sort(frequencies);
		
		int maxSum = 0;
		int multiplier = 26;
		
		for( int i = frequencies.length - 1 ; i >= 0; i-- )
		{
			maxSum += frequencies[i] * multiplier--;
		}
		
		return maxSum;
		
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
					line = line.toLowerCase().replaceAll("\\s+", "");
					
					System.out.println(BeautifulStrings.getMax(line));
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
