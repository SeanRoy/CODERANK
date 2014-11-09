package com.sean.scratch.codeeval.easy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SplitTheNumber
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
					String [] pieces = line.split("\\s+");
					
					int i = 0;
					for( ; i < pieces[1].length(); i++ )
					{
						if ( pieces[1].charAt(i) == '-' || pieces[1].charAt(i) == '+' )
						{
							break;
						}
					}
					
					char operator = pieces[1].charAt(i);
					
					String left = pieces[0].substring(0, i);
					String right = pieces[0].substring(i, pieces[0].length());
					
					int x = 0;
					
					if ( operator == '-' )
					{
						x = Integer.parseInt(left) - Integer.parseInt(right);
					}
					else
					{
						x = Integer.parseInt(left) + Integer.parseInt(right);
					}
					
					System.out.println(x);
					
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
