package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ChangeCase
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
					
					for( char c : chars )
					{
						if ( Character.isLetter(c) )
						{
							if ( Character.isLowerCase(c) )
							{
								sb.append(Character.toUpperCase(c));
							}
							else
							{
								sb.append(Character.toLowerCase(c));
							}
						}
						else
						{
							sb.append(c);
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
