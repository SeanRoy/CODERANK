package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RightMost
{
	public static int rightMost(String str, char chr)
	{
		char [] chars = str.toCharArray();
		
		for( int i = chars.length - 1; i >= 0; i-- )
		{
			if ( chars[i] == chr )
			{
				return i;
			}
		}
		
		return -1;
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
					StringBuilder builder = new StringBuilder();
					
					String [] pieces = line.split(",");
					
					System.out.println(rightMost(pieces[0],pieces[1].charAt(0)));
					
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
