package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumOfIntegers
{
	public static int [] toIntArray(String [] nums)
	{
		int [] numbers = new int[ nums.length ];
		
		for( int i = 0; i < nums.length; i++ )
		{
			numbers[i] = Integer.parseInt(nums[i]);
		}
		
		return numbers;
	}
	public static int getMaxSum(int [] arr)
	{
		int sum = arr[0];
		int temp = 0;
		
		for( int i = 0; i < arr.length; i++ )
		{
			temp = arr[i];
			
			if ( temp > sum )
			{
				sum = temp;
			}
			
			for( int j = i+1; j < arr.length; j++ )
			{
				temp += arr[j];
				
				if ( temp > sum )
				{
					sum = temp;
				}
			}
		}
		
		return sum;
	}
	public static String printArray(int [] arr)
	{
		StringBuilder sb = new StringBuilder();
		for( int x : arr )
		{
			sb.append(x + " ");
		}
		return sb.toString();
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
					if ( ! line.equals("" ) )
					{
						String [] nums = line.replaceAll("\\s+", "").split(",");
						
						int [] arr = SumOfIntegers.toIntArray(nums);
						
						System.out.println(SumOfIntegers.getMaxSum(arr));
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
