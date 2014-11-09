package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SimpleSorting
{
	public static double [] quickSort(double [] nums)
	{
		if ( nums.length >= 2 )
		{
			double pivot = nums[nums.length / 2];
			int leftCount = 0;
			int rightCount = 0;
			
			for( int i = 0; i < nums.length; i++ )
			{
				if ( nums[i] < pivot )
				{
					leftCount++;
				}
			}
			
			rightCount = nums.length - leftCount - 1; // minus one for the pivot.;
			
			double [] leftNums = new double[ leftCount ];
			double [] rightNums = new double[ rightCount ];
			
			int l = 0;
			int r = 0;
			
			for( int i = 0; i < nums.length; i++ )
			{
				if ( nums[i] < pivot )
				{
					leftNums[l++] = nums[i];
				}
				else if ( nums[i] > pivot )
				{
					rightNums[r++] = nums[i];
				}
			}
			
			leftNums = quickSort(leftNums);
			rightNums = quickSort(rightNums);
			
			System.arraycopy(leftNums, 0, nums, 0, leftNums.length);
			nums[leftNums.length]= pivot; 
			System.arraycopy(rightNums, 0, nums, leftNums.length + 1, rightNums.length);
		}
		
		return nums;
	}
	
	public static String toString(double [] arr )
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0; i < arr.length; i++ )
		{
			sb.append(String.format("%1$.3f", arr[i]) );
			
			if ( i < arr.length - 1 )
			{
				sb.append(" ");
			}
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
					String [] doubleStrings = line.split("\\s+");
					double [] doubles = new double[doubleStrings.length];
					
					for( int i = 0; i<doubleStrings.length; i++ )
					{
						doubles[i] = Double.parseDouble(doubleStrings[i]);
					}
					
					System.out.println(toString(SimpleSorting.quickSort(doubles)));
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
