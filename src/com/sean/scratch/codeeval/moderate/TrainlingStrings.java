package com.sean.scratch.codeeval.moderate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TrainlingStrings
{
	public static int end( String left, String right)
	{
		for( int i = right.length() - 1, j = left.length() - 1 ; i >= 0; i--,j-- )
		{
			if ( right.charAt(i) != left.charAt(j) )
			{
				return 0;
			}
		}
		
		return 1;
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
					String [] strings = line.split(",");
					
					if ( strings.length == 2 )
					{
					
						String left = strings[0].trim();
						String right = strings[1].trim();
						
						if ( !left.equals("") && !right.equals("") && right.length() <= left.length() )
						{
							System.out.println(end(left, right));
						}
						else
						{
							System.out.println("0");
						}
					}
					else
					{
						System.out.println("0");
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
