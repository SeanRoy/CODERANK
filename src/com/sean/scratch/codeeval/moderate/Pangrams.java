package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pangrams
{
	public static String getUnusedLetters(char [] chars)
	{
		StringBuilder sb = new StringBuilder();
		
		int [] letters = new int[26];
		
		int count = 0;
		
		for( int i = 0; i < chars.length; i++ )
		{
			int value = chars[i] - (int) 'a';
			
			if ( value >= 0 && value < 26 )
			{
				letters[value]++;
				
				if ( letters[value] == 1 )
				{
					count++;
					
					if ( count == 26 )
					{
						return "NULL";
					}
				}
			}
		}
		
		for( int i = 0; i < letters.length; i++ )
		{
			if ( letters[i] == 0 )
			{
				sb.append( (char) ( i + (int) 'a') );
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
					if ( !line.equals("" ) )
					{
						line = line.toLowerCase();
						
						System.out.println(Pangrams.getUnusedLetters(line.toCharArray()));
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
