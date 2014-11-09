package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NumberOfOnes
{
	public static int numberOfOnes(String line)
	{
		int sum = 0;
		
		try
		{
			line = Integer.toBinaryString(Integer.parseInt(line));
			
			for( char c : line.toCharArray() )
			{
				if ( c == '1')
				{
					sum++;
				}
			}
		}
		catch(NumberFormatException nfe)
		{
			
		}
		
		return sum;
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
					System.out.println( NumberOfOnes.numberOfOnes(line));
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
