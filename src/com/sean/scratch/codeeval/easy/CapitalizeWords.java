package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CapitalizeWords
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
					StringBuilder sb = new StringBuilder();
					
					char [] chars = line.toCharArray();
					
					if ( Character.isLetter(chars[0]) )
					{
						sb.append(Character.toUpperCase(chars[0]));
					}
					else
					{
						sb.append(chars[0]);
					}
					
					boolean spaceFound = false;
					
					for( int i = 1; i < chars.length; i++ )
					{
						char c = chars[i];
						
						if ( Character.isWhitespace(c) )
						{
							spaceFound = true;
						}
						else
						{			
							if ( Character.isLetter(c) && spaceFound )
							{
								c = Character.toUpperCase(c);
							}
							
							spaceFound = false;
						}
						
						sb.append(c);
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
