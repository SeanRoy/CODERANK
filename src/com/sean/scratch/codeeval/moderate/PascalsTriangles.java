package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Prints out a Pascal's triangle to depth 'depth' in row major form.
 * @author Sean
 *
 */
public class PascalsTriangles
{
	public static int getSizeOfArray(int depth)
	{
		int sum = 0;
		
		while( depth > 0 )
		{
			sum += depth--;
		}
		
		return sum;
	}
	public static String pascalsTriangle(int depth)
	{
		int [] arr = new int [getSizeOfArray(depth)];
		
		StringBuilder sb = new StringBuilder();
		
		int index = 0;
		for( int i = 0; i < depth; i++ )
		{
			for( int j = 0; j <= i; j++ )
			{
				// edges
				if ( j == 0 || j == i )
				{
					arr[index] = 1;
				}
				else
				{
					arr[index] = arr[index - (i+1)] + arr[index - (i)];
				}
				
				sb.append(arr[index++] + " ");
			}
		}
		
		return sb.toString().trim();
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
					System.out.println(PascalsTriangles.pascalsTriangle(Integer.parseInt(line)));
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
