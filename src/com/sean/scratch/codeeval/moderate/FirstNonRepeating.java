package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FirstNonRepeating
{
	public static void firstNonRepeating(String line)
	{
		int [] counts = new int [ Character.MAX_CODE_POINT + 1 ];

		char [] chars = line.toCharArray();
		
		Character firstNonRepeating = null;
		int indexOfFirstNonRepeating = 0;
		
		for( int i = 0; i < chars.length; i++ )
		{
			counts[Character.codePointAt(chars, i)]++;
			
			if ( firstNonRepeating == null )
			{
				firstNonRepeating = chars[i];
				indexOfFirstNonRepeating = i;
			}
			else if ( firstNonRepeating == chars[i] )
			{
				firstNonRepeating = null;
				
				for( int j = indexOfFirstNonRepeating; j <= i; j++ )
				{
					if ( counts[Character.codePointAt(chars, j)] == 1 )
					{
						firstNonRepeating = chars[j];
						indexOfFirstNonRepeating = j;
						break;
					}
				}
			}
		}
		
		System.out.println( firstNonRepeating );
	}
		
	public static void naiveFirstNonRepeating(String line)
	{
		int [] counts = new int [ Character.MAX_CODE_POINT + 1 ];

		char [] chars = line.toCharArray();
		
		Character firstNonRepeating = null;
		
		for( int i = 0; i < chars.length; i++ )
		{
			counts[Character.codePointAt(chars, i)]++;
		}
		
		/** n^2 solution **/
		for( int i = 0; i < chars.length; i++ )
		{
			if ( counts[Character.codePointAt(chars, i)] == 1 )
			{
				System.out.println( chars[i] );
				break;
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
					FirstNonRepeating.naiveFirstNonRepeating(line);					
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
