package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DOUBLEDIGITS
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
					if ( ! line.equals( "" ) )
					{
						int sum = 0;
						
						char [] arr = line.toCharArray();
						
						for( int i = 0; i < arr.length - 1; i++ )
						{
							if ( ( int ) ( arr[i] - '0' ) > 0 )
							{
								sum++;
							}
							
							int doubleDigitVal = ( (int) arr[i] - '0' + (int) arr[i+1] - '0' );
							
							if ( doubleDigitVal > 0 && doubleDigitVal < 27 )
							{
								sum++;
							}
						}
						
						System.out.println(sum);
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
