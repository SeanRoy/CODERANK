package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fibbonaci
{
	public static int fib(int n)
	{
		int result = 0;
			
		for ( int prev = 0, pprev = 0; n > 0; n-- )
		{
			
			if ( prev == 0 && pprev == 0 )
			{
				result = 1;
			}
			else
			{
				result = prev + pprev;
			}
			
			pprev = prev;
			
			prev = result;
		}
		
		return result;
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
					System.out.println(Fibbonaci.fib(Integer.parseInt(line)));
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
