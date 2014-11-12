package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://www.codeeval.com/open_challenges/43/
 * @author Sean
 *
 */
public class JollyJumpers
{
	public static String jolly(String [] arr)
	{
		boolean [] test = new boolean [Integer.parseInt(arr[0])];
		
		for( int i = 2; i < arr.length; i++ )
		{
			int diff = Math.abs( 
				( Integer.parseInt(arr[i]) - Integer.parseInt(arr[i-1]) ) );
			
			if ( diff < 1 || diff >= test.length || test[diff] )
			{			
				return "Not jolly";
			}
			
			test[diff] = true;
		}
		
		return "Jolly";
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
						System.out.println(JollyJumpers.jolly(line.split("\\s+")));
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
