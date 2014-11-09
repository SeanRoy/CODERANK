package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LowestUniqueNumber
{
	public static int lowestUnique(String line)
	{
		String [] strDigits = line.split("\\s+");
		
		int [] frequencies = new int[ 10 ];
		
		for( String d : strDigits )
		{
			frequencies[ Integer.parseInt(d) ]++;
		}
		
		int winningCard = 0;
		
		for( int i = 1; i < frequencies.length; i++ )
		{
			if ( frequencies[i] == 1 )
			{
				winningCard = i;
				break;
			}
		}
		
		if ( winningCard > 0 )
		{
			String card = Integer.toString(winningCard);
			
			for( int i = 0; i < strDigits.length; i++ )
			{
				if ( card.equals(strDigits[i]) )
				{
					return i+1;
				}
			}
		}
		
		return 0;
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
					System.out.println(LowestUniqueNumber.lowestUnique(line));
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
