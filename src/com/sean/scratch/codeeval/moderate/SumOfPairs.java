package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumOfPairs
{
	public static String sumOfPairs(int [] arr, int sum)
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0; i < arr.length-1; i++ )
		{
			for( int j = i+1; j < arr.length; j++ )
			{
				if ( arr[i] + arr[j] == sum )
				{
					if ( sb.length() > 0 )
					{
						sb.append(";");
					}
					
					sb.append(arr[i] + "," + arr[j] );
				}
			}
		}
		
		if ( sb.length() > 0 )
		{
			return sb.toString();
		}
		else
		{
			return "NULL";
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
					if ( ! line.equals("") )
					{
						String [] strings = line.split(";");
						String nums = strings[0];
						int sum = Integer.parseInt(strings[1]);
						
						String [] numbers = nums.split(",");
						int [] arr = new int[ numbers.length ];
						
						for( int i = 0; i < numbers.length; i++ )
						{
							arr[i] = Integer.parseInt(numbers[i]);
						}
						
						System.out.println(sumOfPairs(arr, sum));
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
