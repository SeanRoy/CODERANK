package com.sean.scratch;

public class Fib {
	
	/**
	 * 0 1 2 3 4 5 6  7  8   9 10 11
	 * 1 2 3 4 5 6 7  8  9  10 11 12
     * 1,1,2,3,5,8,13,21,34,55,89,144,
	 * @param n
	 * @return
	 */
	public static int fibbonacci(int n)
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
		System.out.println(Fib.fibbonacci(12));
	}

}

// 0 + 1, 0 + 1, 1 + 1, 1 + 2, 2 + 3, 
