package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FindNLongestStrings
{
	public static boolean isEmptyOrNull(String s)
	{
		return s == null || s.equals("");
	}
	public static void getLongest(String [] strings, String line)
	{
		getLongest(strings, line, 0);
	}
	public static void getLongest(String [] strings, String line, int index)
	{
		for( int i = index; i < strings.length; i++ )
		{
			if ( FindNLongestStrings.isEmptyOrNull(strings[i]) )
			{
				strings[i] = line;
			}
			else if ( line.length() > strings[i].length() )
			{
				String temp = strings[i];
				
				strings[i] = line;
			
				getLongest(strings, temp, ++index);
			}
			else
			{
				getLongest(strings, line, ++index);
			}
			
			break;
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
				
				String line = bReader.readLine();
				
				int num = Integer.parseInt(line);
				
				String [] strings = new String[num];
				
				while( ( line = bReader.readLine() ) != null )
				{
					FindNLongestStrings.getLongest(strings, line);
					
				}
				
				for( String s : strings )
				{
					System.out.println(s);
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
