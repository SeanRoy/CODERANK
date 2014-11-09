package com.sean.scratch.utils;

public abstract class ArrayUtils 
{
	public static void printIntArray(int [] array)
	{
		for( int i = 0; i < array.length; i++ )
		{
			System.out.print(array[i]);
			
			if ( i < array.length - 1 )
			{
				System.out.print(", ");
			}
			else
			{
				System.out.println();
			}
		}
	}
}
