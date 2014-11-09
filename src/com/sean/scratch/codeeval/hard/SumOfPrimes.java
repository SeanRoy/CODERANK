package com.sean.scratch.codeeval.hard;


public class SumOfPrimes
{
	// Sieve of Eratosthenes
	public static int sumOfPrimes( int numOfPrimes, int n, int start )
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
		
		for( int i = start; i < n; i++ )
		{
			if ( A[i] )
			{
				if ( numOfPrimes-- > 0 )
				{
					sum += i;
				}
			}
		}
		
		return sum;
	}
	public static void main(String [] args)
	{		
		System.out.println(SumOfPrimes.sumOfPrimes(1000, 11000, 2));

	}
}
