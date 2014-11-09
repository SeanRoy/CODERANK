package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiplesOfNumber
{
	public static int findItOld(int base, int pow)
	{
		while( pow < base )
		{
			pow = pow << 1;
		}
		
		return pow;
	}
	public static int findIt(int base, int pow)
	{
		int i = 0;
		
		if ( base >= 0 )
		{
			while( i * pow < base )
			{
				i++;
			}
		}
		else
		{
			while( i * pow > base )
			{
				i--;
			}
		}
		
		return i * pow;
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
					if ( !line.equals("") )
					{
						String [] pieces = line.split(",");
						System.out.println(findIt(Integer.parseInt(pieces[0]), Integer.parseInt(pieces[1])));
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
