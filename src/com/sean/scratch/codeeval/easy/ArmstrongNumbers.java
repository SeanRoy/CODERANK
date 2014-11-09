package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArmstrongNumbers
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
				
				while( ( line = bReader.readLine() ) != null )
				{
					int n = line.length();
					double digit = Double.parseDouble(line);
					double sum = 0;
					
					for( int i = 0; i < n; i++ )
					{
						int x = ( ( int ) line.charAt(i) ) - ( (int) '0' );
						
						sum += Math.pow(x, n);
					}
					
					if ( sum == digit )
					{
						System.out.println("True");
					}
					else
					{
						System.out.println("False");
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
