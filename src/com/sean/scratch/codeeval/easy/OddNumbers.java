package com.sean.scratch.codeeval.easy;

public class OddNumbers
{
	public static void main(String [] args)
	{		
		boolean print = true;
		
		for( int i = 1; i < 100; i++ )
		{
			if ( print )
			{
				print = false;
				
				System.out.println(i);
			}
			else
			{
				print = true;
			}
		}
	}
}
