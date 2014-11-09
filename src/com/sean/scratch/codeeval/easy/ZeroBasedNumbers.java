package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class ZeroBasedNumbers
{
	public static BigInteger zeroBasedNumber(String line)
	{
		String [] codes = line.split("\\s+");
		
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0; i < codes.length; i++ )
		{
			boolean zeroes = true;
			
			if ( codes[i].equals("00") )
			{
				zeroes = false;
			}
			
			i++;
			
			String numbers = codes[i];
			
			for( int j = 0; j < numbers.length(); j++ )
			{
				if ( zeroes )
				{
					sb.append("0");
				}
				else
				{
					sb.append("1");
				}
			}
		}
		
		return new BigInteger(sb.toString(), 2);
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
					System.out.println(ZeroBasedNumbers.zeroBasedNumber(line));
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
