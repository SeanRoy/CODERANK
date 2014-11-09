package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiplyLists
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
					String [] sides = line.split("\\|");
					
					String [] left = sides[0].trim().split("\\s+");
					String [] right = sides[1].trim().split("\\s+");
					
					StringBuilder sb = new StringBuilder();
					
					for( int i = 0; i < left.length; i++ )
					{
						sb.append( ( Integer.parseInt(left[i]) * Integer.parseInt(right[i]) ) );
						
						if ( i < left.length - 1 )
						{
							sb.append( " ");
						}
					}
					
					System.out.println(sb.toString());
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
