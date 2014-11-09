package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class InterruptedBubbleSort
{
	public static int [] bubbleSort(int start, BigDecimal end, int [] nums)
	{
		if ( start < nums.length - 1 && end.compareTo(new BigDecimal(start)) > 0)
		{
			for( int j = 0; j < nums.length - 1; ++j)
			{
				if ( nums[j+1] < nums[j] )
				{
					swap( nums, j+1, j );
				}
			}
			
			bubbleSort(++start, end, nums);
		}
		
		return nums;
	}
	
	public static void swap( int [] nums, int indexA, int indexB )
	{
		int temp = nums[indexA];
		
		nums[indexA] = nums[indexB];
		
		nums[indexB] = temp;
	}
	public static int [] toIntArray(String [] arr)
	{
		int [] intArray = new int[ arr.length ];
		
		for(int i = 0; i < arr.length; i++ )
		{
			intArray[i] = Integer.parseInt( arr[i] );
		}
		
		return intArray;
	}
	public static void printArray(int [] arr)
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0; i < arr.length; i++ )
		{
			sb.append(arr[i]);
			
			if ( i < arr.length - 1 )
			{
				sb.append(" ");
			}
		}
		
		System.out.println(sb.toString());
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
					String [] strings = line.split("\\|");
					
					int [] arr = InterruptedBubbleSort.toIntArray(strings[0].trim().split("\\s++"));
					BigDecimal iterations = new BigDecimal(strings[1].trim());
					
					InterruptedBubbleSort.printArray(InterruptedBubbleSort.bubbleSort(0, iterations, arr));
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
