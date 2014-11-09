package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SmallestPeriod
{
	public static int findSmallestPeriod(char [] chars)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(chars[0]);
		
		for( int i = 1; i < chars.length; i++ )
		{
			char c = chars[i];
			
			if ( c == sb.charAt(0) )
			{
				boolean foundLoop = true;
				
				for( int j = 0; j < sb.length(); j++ )
				{
					if ( chars[j + i] != sb.charAt(j) )
					{
						foundLoop = false;
						break;
					}
				}
				
				if ( foundLoop )
				{
					break;
				}
			}
			else
			{
				sb.append(c);
			}
		}
		
		return sb.length();
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
					System.out.println(SmallestPeriod.findSmallestPeriod(line.toCharArray()));
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
