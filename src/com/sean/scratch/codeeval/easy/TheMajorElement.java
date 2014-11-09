package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TheMajorElement
{
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
					int [] freq = new int[ 101 ];
					
					String [] nums = line.split(",");
					
					String major = "None";
					
					int check = nums.length >> 1;
					
					for( String num : nums )
					{
						int x = Integer.parseInt(num);
						
						freq[ x ]++;
						
						if ( freq[ x ] >= check )
						{
							major = num;
							break;
						}
					}
					
					System.out.println(major);
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
