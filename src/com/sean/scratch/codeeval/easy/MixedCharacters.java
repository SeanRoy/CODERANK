package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MixedCharacters
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
					StringBuilder left = new StringBuilder();
					StringBuilder right = new StringBuilder();
					
					String [] words = line.split(",");
					
					for( String word : words )
					{
						if ( Character.isLetter(word.charAt(0)))
						{
							left.append(word + " ");
						}
						else
						{
							right.append(word + " " );
						}
					}
					
					if ( left.length() > 0)
						left.deleteCharAt(left.length() - 1);
					
					if ( right.length() > 0 )
						right.deleteCharAt(right.length() - 1);
					
					if ( left.length() > 0 && right.length() > 0 )
					{
						left.append("|");
					}
					
					left.append(right.toString() );
					System.out.println(left.toString().replaceAll("\\s+", ","));
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
