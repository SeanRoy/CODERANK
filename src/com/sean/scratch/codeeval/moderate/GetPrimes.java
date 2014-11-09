package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetPrimes
{
	// Sieve of Eratosthenes
		public static void primes( int n, int start )
		{
			int sum = 0;
			boolean [] A = new boolean[n];
			for(int i = 0; i < A.length; i++)
			{
				A[i] = true;
			}
			
			int limit = (int) Math.sqrt((double)n);
			for( int i = start; i < limit; i++ )
			{
				if ( A[i] )
				{
					int j = (int) Math.pow(i, 2);
					for( int x = 0; j < n; j = (int) Math.pow(i, 2) + ++x * i )
					{
						A[j] = false;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			for( int i = start; i < A.length; i++ )
			{
				if ( A[i] )
				{
					sb.append(i + " ");
				}
			}
			
			System.out.println(sb.toString().trim().replaceAll("\\s+", ","));
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
						GetPrimes.primes(Integer.parseInt(line), 2);
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
