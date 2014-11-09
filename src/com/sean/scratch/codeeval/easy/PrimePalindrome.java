package com.sean.scratch.codeeval.easy;


public class PrimePalindrome
{
	// Sieve of Eratosthenes
	public static int primePalindrome( int numOfPrimes, int n, int start, int maxPrime )
	{
		int palindrome = -1;

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
		
		for( int i = maxPrime; i >= 0; i-- )
		{
			if ( A[i] && palindrome(i) )
			{
				palindrome = i;
				break;
			}
		}
		
		return palindrome;
	}
	public static boolean palindrome(int x)
	{
		char [] arr = Integer.toString(x).toCharArray();
		
		for( int i = 0, j = arr.length - 1; i < arr.length && i <= j; i++,j--)
		{
			if ( arr[i] != arr[j] )
			{
				return false;
			}
		}
		return true;
	}
	public static void main(String [] args)
	{
		System.out.println(PrimePalindrome.primePalindrome(1000, 11000, 2, 1000) );
	}
}
