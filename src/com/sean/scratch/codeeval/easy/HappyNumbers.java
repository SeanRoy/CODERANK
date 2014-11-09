package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HappyNumbers
{
	public static int happyOrNot(String line)
	{
		char [] digits = line.toCharArray();
		
		int [] nums = new int[ digits.length ];
		
		for(int i = 0; i < digits.length; i++)
		{
			nums[i] = ( ( int ) digits[i] ) - ( ( int ) '0' );
		}
		
		return happyOrNot(nums);
	}
	public static int happyOrNot(int [] nums)
	{
		int sum = 0;
		
		for( int n : nums )
		{
			sum += Math.pow(n, 2);
		}
		
		if ( sum == 1 )
		{
			return 1;
		}
		else
		{
			try
			{
				return happyOrNot(Integer.toString(sum));
			}
			catch( java.lang.StackOverflowError e )
			{
				return 0;
			}
		}
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
					System.out.println(HappyNumbers.happyOrNot(line));
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
