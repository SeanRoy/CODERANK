package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MaxRangeSum
{
	public static int maxInvest(int nDays, int [] earnings)
	{
		int max = 0;
		
		for( int i = 0; i <= earnings.length - nDays; i++ )
		{
			int sum = 0;
			
			for( int j = i; j < i + nDays; j++ )
			{
				sum += earnings[j];
			}
			
			if ( sum > max )
			{
				max = sum;
			}
		}
		
		return max;
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
					if ( ! line.equals( "" ) )
					{
						String [] params = line.split(";");
						int nDays = Integer.parseInt(params[0]);
						String [] earningStr = params[1].split("\\s+");
						int [] earnings = new int[ earningStr.length];
						
						for( int i = 0 ; i < earningStr.length; i++ )
						{
							earnings[i] = Integer.parseInt(earningStr[i]);
						}
						
						System.out.println(maxInvest(nDays, earnings));
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
