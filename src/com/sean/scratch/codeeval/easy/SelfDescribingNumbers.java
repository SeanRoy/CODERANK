package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SelfDescribingNumbers
{
	public static int selfDescribingNumber(String line)
	{
		int [] frequencies = new int[10];
		
		for( int i = 0; i < line.length(); i++ )
		{
			frequencies[ ((int) line.charAt(i)) - ((int) '0')]++;
		}
		
		for( int i = 0; i < line.length(); i++ )
		{
			int value = ((int) line.charAt(i)) - ((int) '0');
			
			if ( value != frequencies[i] )
			{
				return 0;
			}
		}
		
		return 1;
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
					System.out.println(SelfDescribingNumbers.selfDescribingNumber(line));
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
