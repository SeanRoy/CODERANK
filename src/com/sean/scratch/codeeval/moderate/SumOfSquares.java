package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumOfSquares
{
	public static int sumOfSquares(int x)
	{
		int ret = 0;
		
		for (int i = ( ( int ) Math.sqrt(x)); i >= 0; i-- )
		{
			double y = Math.sqrt(x - i * i);
			
			if ( y == (int) y )
			{		
				if ( x * x == y )
				{
					ret += 2;
				}
				else
				{
					ret++;
				}
			}
		}
		
		return ret/2;
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

				// Skip the first line.
				String line = bReader.readLine();
				
				while( ( line = bReader.readLine() ) != null )
				{
					System.out.println(SumOfSquares.sumOfSquares(Integer.parseInt(line)));
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
