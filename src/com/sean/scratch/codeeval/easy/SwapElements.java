package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SwapElements
{
	public static int [] getIndices(String x)
	{
		String [] startAndEnd = x.trim().split("-");
		int [] indices = new int[ startAndEnd.length ];
		
		indices[0] = Integer.parseInt(startAndEnd[0]);
		indices[1] = Integer.parseInt(startAndEnd[1]);
		
		return indices;
	}
	
	public static String process(String [] nums, String [] indices )
	{
		StringBuilder sb = new StringBuilder();
		
		for( String idx : indices )
		{	
			int [] startAndEnd = getIndices(idx);
			
			String temp = nums[startAndEnd[0]];
			nums[startAndEnd[0]] = nums[startAndEnd[1]];
			nums[startAndEnd[1]] = temp;
		}
		
		for( int i = 0; i < nums.length; i++ )
		{
			sb.append(nums[i]);
			
			if ( i < nums.length - 1 )
			{
				sb.append( " " );
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
					String [] pieces = line.split(":");
					String [] nums = pieces[0].trim().split("\\s+");
					String [] indices = pieces[1].trim().split(",");
					
					System.out.println(SwapElements.process(nums, indices));
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
