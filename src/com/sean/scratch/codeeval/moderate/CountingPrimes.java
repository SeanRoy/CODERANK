package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://www.codeeval.com/open_challenges/63/
 * @author Sean
 *
 */
public class CountingPrimes
{
	// Sieve of Eratosthenes
		public static int sumOfPrimes( int n, int start )
		{
			int sum = 0;
			boolean [] prime = new boolean[n+1];
			for(int i = 0; i < prime.length; i++)
			{
				prime[i] = true;
			}
			
			for( int i = 2; i*i <= n; i++ )
			{
				if ( prime[i] )
				{
					for( int j = i; i * j <= n; j++)
					{
						prime[i*j] = false;
					}
				}
			}
			
			for( int i = start; i <= n; i++ )
			{
				if ( prime[i] )
				{
					sum++;
				}
			}
			
			return sum;
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
					if ( ! line.equals("") )
					{
						String [] ss = line.split(",");
						
						System.out.println(CountingPrimes.sumOfPrimes(Integer.parseInt(ss[1]), Integer.parseInt(ss[0])));
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
